package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.Map;

import smallITgroup.event.dto.ActivityDto;
import smallITgroup.event.dto.EventDto;
import smallITgroup.event.dto.ResponseDto;

public interface EventService {
    
    // Retrieves all new events (events marked as "new")
    Map<Long, EventDto> getNewEvents();
    
    // Retrieves events related to a specific door, identified by its doorId
    Map<Long, EventDto> getDoorActivityById(String doorId);
    
    // Retrieves historical events based on a specific date
    Map<Long, EventDto> getHistoryByDay(LocalDate date);
    
    // Saves a new event and returns a response indicating success or failure
    ResponseDto saveNewEvent(ActivityDto activityDto);

    // Retrieves all events
	Map<Long, EventDto> getAllEvents();
}
