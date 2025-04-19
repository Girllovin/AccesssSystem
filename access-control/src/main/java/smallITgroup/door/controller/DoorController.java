package smallITgroup.door.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.door.dto.CreateDoorRequest;
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
}
