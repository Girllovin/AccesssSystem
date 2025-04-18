package smallITgroup.event.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.event.dto.EventDto;
import smallITgroup.event.dto.EventDateDto;
import smallITgroup.event.dto.ResponseDto;
import smallITgroup.event.service.EventService;

@RequiredArgsConstructor
@RestController
public class EventController { 
	final EventService eventService;

	@GetMapping("/door/activity")
	public Map<Long, EventDto> getNewActivity() {
		return eventService.getNewEvents();
	}
    @GetMapping("/door/{doorId}/activity") 
	public Map<Long, EventDto> getDoorActivityById (@PathVariable String doorId) {
	   return eventService.getDoorActivityById(doorId);		
	}
   
	@GetMapping("/door/history")
	public Map<Long, EventDto> getHistoryByDay(@RequestBody EventDateDto eventDateDto) {
		return eventService.getHistoryByDay(eventDateDto.getDate()); 
	}
	
	@PostMapping("/door/activity")
	public ResponseDto NewActivity() {
		return eventService.saveNewEvent();
	}
}
