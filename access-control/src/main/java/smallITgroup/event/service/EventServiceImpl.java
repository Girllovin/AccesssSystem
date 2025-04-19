package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

@Service // Marks this class as a service to be managed by Spring
@RequiredArgsConstructor // Generates a constructor with required (final) fields
public class EventServiceImpl implements EventService {
    
    final EventRepository eventRepository;    // Repository for accessing event data
    final DoorRepository doorRepository;      // Repository for accessing door data
    final ModelMapper modelMapper;            // ModelMapper to convert between Event and EventDto
    final ClientServiceImpl clientServiceImpl;// ClientServis for operations with card holders 
    static private long eventCounter = 0;
    
    @Override
    public Map<Long, EventDto> getNewEvents() {
        // Retrieve all events where the 'newEvents' flag is true
        List<Event> newEvents = eventRepository.findByNewEventsTrue();

        // Convert the list of new Event objects into a Map of EventDto,
        // while setting the 'newEvents' flag to false for each one
        Map<Long, EventDto> result = newEvents.stream()
            .peek(event -> event.setNewEvents(false)) // Mark each event as no longer new
            .collect(Collectors.toMap(
                Event::getId,                               // Use Event ID as the key
                event -> modelMapper.map(event, EventDto.class), // Map Event to EventDto
                (existing, replacement) -> existing         // In case of key collision, keep the first
            ));

        // Save the updated events back to the database with 'newEvents' set to false
        eventRepository.saveAll(newEvents);

        // Return the result map of EventDto objects
        return result;
    }


    
    @Override
    public Map<Long, EventDto> getDoorActivityById(String doorId) {
        // Retrieve all events where the door id parameter is equal
        List<Event> newEvents = eventRepository.findByDoorid(doorId);

        // Convert the list of Event objects at this door into a Map of EventDto,
        // while setting the 'newEvents' flag to false for each one
        Map<Long, EventDto> result = newEvents.stream()
            .peek(event -> event.setNewEvents(false)) // Mark each event as no longer new
            .collect(Collectors.toMap(
                Event::getId,                               // Use Event ID as the key
                event -> modelMapper.map(event, EventDto.class), // Map Event to EventDto
                (existing, replacement) -> existing         // In case of key collision, keep the first
            ));

        // Return the result map of EventDto objects
        return result;
    }

    @Override
    public Map<Long, EventDto> getHistoryByDay(LocalDate date) {
        System.out.println("print from getHistoryByDay");
        // This method is currently unimplemented; should return events by date in the future
        return null;
    }

    @Override
    public ResponseDto saveNewEvent(ActivityDto activityDto) { 
    	eventCounter++;
		Integer currentCard = activityDto.getCardId();
		System.out.println("currentCard " + currentCard);
		CardHolderDto currentClient = clientServiceImpl.getCardHolderByCard(currentCard);
		System.out.println("currentClient " + currentClient);
		Door currentDoor = doorRepository.findById(activityDto.getDoorId())
				.orElseThrow(() -> new DoorNotFoundExeption());
		System.out.println("currentdoor " + currentDoor);
		HashSet<String> permissions = currentClient.getPermissions();		
		LocalDate expiredDate = currentClient.getCards().get(currentCard);
		boolean accessGranted = LocalDate.now().isBefore(expiredDate)
				&& permissions.contains(currentDoor.getDescription());
		System.out.println("Description " + currentDoor.getDescription());
		System.out.println("expiredDate " + expiredDate);
		if (accessGranted) {
			Event newEvent = new Event(eventCounter,activityDto.getDoorId(),currentCard,
					currentClient.getFirstName() + " " + currentClient.getLastName(),"GRANTED",activityDto.getActionTime(),true);
			eventRepository.save(newEvent);
			return new ResponseDto(true, 5, false, 0, false,"GRANTED" );
		} else {
			
			Event newEvent = new Event(eventCounter,activityDto.getDoorId(),currentCard,
					currentClient.getFirstName() + " " + currentClient.getLastName(),"DENIED",activityDto.getActionTime(),true);
			eventRepository.save(newEvent);
			return new ResponseDto(false, 0, false, 0, false,"DENIED" );
		}
		
    }



	@Override
	public Map<Long, EventDto> getAllEvents() {
		// Retrieve all events where the 'newEvents' flag is true
        List<Event> newEvents = eventRepository.findAll();

        // Convert the list of new Event objects into a Map of EventDto,
        Map<Long, EventDto> result = newEvents.stream()
            .peek(event -> event.setNewEvents(false)) // Mark each event as no longer new
            .collect(Collectors.toMap(
                Event::getId,                               // Use Event ID as the key
                event -> modelMapper.map(event, EventDto.class), // Map Event to EventDto
                (existing, replacement) -> existing         // In case of key collision, keep the first
            ));

        // Save the updated events back to the database with 'newEvents' set to false
        eventRepository.saveAll(newEvents);

        // Return the result map of EventDto objects
        return result;
    }
		
}