package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.event.dto.ActivityDto;
import smallITgroup.event.dto.EventDto;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.client.service.ClientServiceImpl;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dao.exeptions.DoorNotFoundExeption;
import smallITgroup.door.model.Door;
import smallITgroup.event.dao.EventRepository;
import smallITgroup.event.dto.ResponseDto;
import smallITgroup.event.model.Event;
import smallITgroup.utills.OpenDoorService;

@Service // Marks this class as a service to be managed by Spring
@RequiredArgsConstructor // Generates a constructor with required (final) fields
public class EventServiceImpl implements EventService {

    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    final EventRepository eventRepository;    // Repository for accessing event data
    final DoorRepository doorRepository;      // Repository for accessing door data
    final ModelMapper modelMapper;            // ModelMapper to convert between Event and EventDto
    final ClientServiceImpl clientServiceImpl;// ClientService for operations with card holders 
    final OpenDoorService openDoorService;


    @Override
    public Map<String, EventDto> getNewEvents() {
        log.info("Retrieving new events with flag 'newEvents=true'");
        List<Event> newEvents = eventRepository.findByNewEventsTrue();

        // Convert the list of new Event objects into a Map of EventDto,
        // while setting the 'newEvents' flag to false for each one
        Map<String, EventDto> result = newEvents.stream()
            .peek(event -> event.setNewEvents(false)) // Mark each event as no longer new
            .collect(Collectors.toMap(
                Event::getId,                               // Use Event ID as the key
                event -> modelMapper.map(event, EventDto.class), // Map Event to EventDto
                (existing, replacement) -> existing         // In case of key collision, keep the first
            ));

        // Save the updated events back to the database with 'newEvents' set to false
        eventRepository.saveAll(newEvents);

        log.info("Retrieved and updated {} new events", result.size());
        return result;
    }

    @Override
    public Map<String, EventDto> getDoorActivityById(String doorId) {
        log.info("Retrieving events for door with ID: {}", doorId);

        // Retrieve all events where the door id parameter is equal
        List<Event> newEvents = eventRepository.findByDoorid(doorId);

        // Convert the list of Event objects at this door into a Map of EventDto,
        // while setting the 'newEvents' flag to false for each one
        Map<String, EventDto> result = newEvents.stream()
            .peek(event -> event.setNewEvents(false)) // Mark each event as no longer new
            .collect(Collectors.toMap(
                Event::getId,                               // Use Event ID as the key
                event -> modelMapper.map(event, EventDto.class), // Map Event to EventDto
                (existing, replacement) -> existing         // In case of key collision, keep the first
            ));

        log.info("Found {} events for door ID: {}", result.size(), doorId);
        // Return the result map of EventDto objects
        return result;
    }

    @Override
    public Map<String, EventDto> getHistoryByDay(LocalDate date) {
        log.warn("getHistoryByDay is not implemented yet. Requested date: {}", date);
        // This method is currently unimplemented; should return events by date in the future
        return null;
    }

    @Override
    public ResponseDto saveNewEvent(ActivityDto activityDto) { 
     
        log.info("Saving new event.");

        Integer currentCard = activityDto.getCardId();
        log.debug("Card ID received: {}", currentCard);

        CardHolderDto currentClient = clientServiceImpl.getCardHolderByCard(currentCard);
        log.debug("Card holder found: {} {}", currentClient.getFirstName(), currentClient.getLastName());

        Door currentDoor = doorRepository.findById(activityDto.getDoorId())
                .orElseThrow(() -> {
                    log.error("Door not found: {}", activityDto.getDoorId());
                    return new DoorNotFoundExeption();
                });

        log.debug("Door found: {}", currentDoor.getDescription());

     // Get permissions assigned to the current cardholder
        HashSet<String> permissions = currentClient.getPermissions();        

        // Get the expiration date of the card from the cardholder's data
        LocalDate expiredDate = currentClient.getCards().get(currentCard);

        // Log the expiration date for debugging purposes
        log.debug("Card expiration date: {}", expiredDate);

        // Determine whether access is granted:
        // - current date must be before the card's expiration date
        // - the door's description must be included in the user's permissions
        boolean accessGranted = LocalDate.now().isBefore(expiredDate)
                && permissions.contains(currentDoor.getDescription());

        // Log whether access is granted or denied, including the cardholder name and door info
        log.info("Access {} for card holder {} to door '{}'", 
                accessGranted ? "GRANTED" : "DENIED",
                currentClient.getFirstName(), 
                currentDoor.getDescription());

        // Create a new Event object to record the access attempt
        Event newEvent = new Event(
        	null,
            activityDto.getDoorId(),
            currentCard,
            currentClient.getFirstName() + " " + currentClient.getLastName(),
            accessGranted ? "GRANTED" : "DENIED",
            activityDto.getActionTime(),
            true // Mark event as new
        );

        // Save the new event in the database
        eventRepository.save(newEvent);

        // Log the saved event for audit purposes
        log.info("Event saved: {}", newEvent);

        // Return a response to indicate the result of the access attempt
        ResponseDto resultResponse = new ResponseDto(
            accessGranted,                // Whether access was granted
            accessGranted ? 5 : 0,        // Sample parameter (e.g. access level or delay)
            false,                        // Additional status flag (unused here)
            0,                            // Additional numeric info (unused here)
            false,                        // Additional status flag (unused here)
            accessGranted ? "GRANTED" : "DENIED" // Result message
        );
        if (accessGranted) {
        	openDoorService.openDoorTemporarily(activityDto.getDoorId(), resultResponse.getOpenDelay());
        }
        return resultResponse;
    }

    @Override
    public Map<String, EventDto> getAllEvents() {
        log.info("Retrieving all events");

        // Fetch all events from the database
        List<Event> allEvents = eventRepository.findAll();

        // Collect unique door IDs used in events
        Set<String> doorIds = allEvents.stream()
            .map(Event::getDoorid)
            .collect(Collectors.toSet());

        // Fetch all doors in one batch to avoid repeated DB calls
        Map<String, String> doorIdToDescriptionMap = doorRepository.findAllById(doorIds).stream()
            .collect(Collectors.toMap(
                Door::getDoorId,
                Door::getDescription
            ));

        // Replace doorId with its description in each event and convert to DTO
        Map<String, EventDto> result = allEvents.stream()
            .peek(event -> {
                String description = doorIdToDescriptionMap.get(event.getDoorid());
                if (description != null) {
                    event.setDoorid(description);
                }
            })
            .collect(Collectors.toMap(
                Event::getId,
                event -> modelMapper.map(event, EventDto.class)
            ));

        log.info("Total events retrieved and processed: {}", result.size());
        return result;
    }

}
