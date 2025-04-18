package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.Map;

import smallITgroup.client.dto.EventDto;

public interface EventService {
    Map<Long, EventDto> getNewEvents(); 
    Map<Long, EventDto> getDoorActivityById(String doorId);
    Map<Long, EventDto> getHistoryByDay (LocalDate date);
    
}


