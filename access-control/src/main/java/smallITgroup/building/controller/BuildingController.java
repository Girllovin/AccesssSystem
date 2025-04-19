package smallITgroup.building.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dto.BuildingDto;
import smallITgroup.building.service.BuildingService;
import smallITgroup.door.service.DoorSyncService;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BuildingController {
	
	final BuildingService buildingService;

	@PostMapping("/building/new")
	public boolean addCardHolder(@RequestBody BuildingDto building) {
		return buildingService.createBuilding(building);
	}
	
    @Autowired
    private DoorSyncService syncService;
    
	@GetMapping("/building/{id}")
	public BuildingDto getBuildingById(@PathVariable Integer id) {
		syncService.syncDoorsToBuilding(1);
		return buildingService.getBuildingById(id);
	}	
}
