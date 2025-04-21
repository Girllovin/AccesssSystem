package smallITgroup.door.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.door.dto.CreateDoorRequest;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.service.DoorService;

@RequiredArgsConstructor // Generates a constructor with required (final) fields
@CrossOrigin(origins = "http://localhost:3000") // Allows cross-origin requests from frontend
@RestController // Marks this class as a REST controller

public class DoorController {

	final DoorService doorService; // Service handling door-related business logic
	
	@PostMapping("/door/new") // Handles POST requests to /door/new
	public boolean createDoor(@RequestBody CreateDoorRequest request) {
		// Calls service to create a door with building ID and door details from the request
		return doorService.createDoor(request.getBuildingId(), request.getDoor());
	}
	
	@DeleteMapping("/door/remove/{doorId}")
	public DoorDto removeDoor(@PathVariable String doorId) {	
		return doorService.removeDoor(doorId);
	}
	
	@PutMapping("/door/{doorId}/activate")
	public DoorDto activateDoor(@PathVariable String doorId, @RequestBody DoorDto doorDto) {	
		return doorService.activateDoor(doorDto);
	}
}

