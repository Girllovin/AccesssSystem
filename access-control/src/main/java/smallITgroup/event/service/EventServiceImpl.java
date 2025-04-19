package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.event.dto.EventDto;
import smallITgroup.event.dao.EventRepository;
import smallITgroup.event.dto.ResponseDto;
import smallITgroup.event.model.Event;

@Service // Marks this class as a service to be managed by Spring
@RequiredArgsConstructor // Generates a constructor with required (final) fields
public class EventServiceImpl implements EventService {
    
    final EventRepository eventRepository; // Repository for accessing event data
    final ModelMapper modelMapper;         // ModelMapper to convert between Event and EventDto

    @Override
    public Map<Long, EventDto> getNewEvents() {
        System.out.println("print from getNewEvents");
        
        // Fetch all events, filter only the new ones (where newEvents = true),
        // and map them to EventDto objects. Collect them into a Map with Long as key (Event ID).
        return eventRepository.findAll().stream()
                .filter(event -> event.getNewEvents()) // Filter only new events
                .collect(Collectors.toMap(Event::getId, event -> modelMapper.map(event, EventDto.class))); // Map Event to EventDto
    }

    @Override
    public Map<Long, EventDto> getDoorActivityById(String doorId) {
        System.out.println("print from getDoorActivityById");
        // This method is currently unimplemented; should return events by doorId in the future
        return null;
    }

    @Override
    public Map<Long, EventDto> getHistoryByDay(LocalDate date) {
        System.out.println("print from getHistoryByDay");
        // This method is currently unimplemented; should return events by date in the future
        return null;
    }

    @Override
    public ResponseDto saveNewEvent() {
        // Method stub, this will save a new event (yet to be implemented)
        return null;
    }
}
