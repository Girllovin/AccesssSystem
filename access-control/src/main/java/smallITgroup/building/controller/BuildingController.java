package smallITgroup.building.controller;


import smallITgroup.door.service.DoorSyncService;

import org.springframework.web.bind.annotation.CrossOrigin; // For handling cross-origin requests
import org.springframework.web.bind.annotation.GetMapping; // To handle GET requests
import org.springframework.web.bind.annotation.PathVariable; // For path variables in URLs
import org.springframework.web.bind.annotation.PostMapping; // To handle POST requests
import org.springframework.web.bind.annotation.RequestBody; // For extracting request body in POST requests
import org.springframework.web.bind.annotation.RestController; // Marks the class as a REST controller

import lombok.RequiredArgsConstructor; // For constructor injection
import smallITgroup.building.dto.BuildingDto; // DTO for building data
import smallITgroup.building.service.BuildingService; // Service for handling building-related operations

@RequiredArgsConstructor // Automatically generates a constructor for required fields (buildingService)
@CrossOrigin(origins = "http://localhost:3000") // Allows cross-origin requests from the specified URL (React app)
@RestController // Marks this class as a REST controller
public class BuildingController {
	
	// Building service injected through constructor
	final BuildingService buildingService;
	final DoorSyncService syncService;

	// POST endpoint to create a new building
	// The body of the request should contain a BuildingDto object to be passed to the service
	@PostMapping("/building/new")
	public boolean addCardHolder(@RequestBody BuildingDto building) {
		// Calls the service method to create a new building
		return buildingService.createBuilding(building);
  }

	// GET endpoint to retrieve a building by its ID
	// Path variable 'id' is used to get the building's ID from the URL
	@GetMapping("/building/{id}")
	public BuildingDto getBuildingById(@PathVariable Integer id) {
		// Calls the service method to get building details by ID

    syncService.syncDoorsToBuilding(1);
		return buildingService.getBuildingById(id);
	}	
}
