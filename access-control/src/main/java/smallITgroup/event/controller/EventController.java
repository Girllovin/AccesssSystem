package smallITgroup.event.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.event.dto.EventDto;
import smallITgroup.event.dto.ActivityDto;
import smallITgroup.event.dto.EventDateDto;
import smallITgroup.event.dto.ResponseDto;
import smallITgroup.event.service.EventService;

@RequiredArgsConstructor // Automatically generates a constructor with final fields
@RestController // Marks this class as a REST controller
public class EventController {

	final EventService eventService; // Injects the EventService to handle business logic

	// Endpoint to get all new activities related to doors
	@GetMapping("/door/activity")
	public Map<Long, EventDto> getNewActivity() {
		// Fetches all new events from the service
		return eventService.getNewEvents();
	}
	
	// Endpoint to get activities for a specific door by its ID
	@GetMapping("/door/{doorId}/activity") 
	public Map<Long, EventDto> getDoorActivityById(@PathVariable String doorId) {
		// Fetches events for the door identified by doorId
		return eventService.getDoorActivityById(doorId);
	}
   
	// Endpoint to get the history of events for a specific day
	@GetMapping("/door/history")
	public Map<Long, EventDto> getHistoryByDay(@RequestBody EventDateDto eventDateDto) {
		// Fetches historical events for the given date
		return eventService.getHistoryByDay(eventDateDto.getDate());
	}
	
	// Endpoint to create/save a new event/activity
	@PostMapping("/door/activity")
	public ResponseDto NewActivity(@RequestBody ActivityDto activityDto) {
		System.out.println("We are in controller");
		// Saves the new event/activity and returns a response DTO
		return eventService.saveNewEvent(activityDto);
	}
	
	// Endpoint to get all new activities related to doors
		@GetMapping("/door/activity/all")
		public Map<Long, EventDto> getAllActivity() {
			// Fetches all new events from the service
			return eventService.getAllEvents();
		}
}
