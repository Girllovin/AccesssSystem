package smallITgroup.building.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dto.BuildingDto;
import smallITgroup.building.service.BuildingService;
import smallITgroup.client.service.ClientService;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BuildingController {
	
	final BuildingService buildingService;

	@PostMapping("/building/new")
	public boolean addCardHolder(@RequestBody BuildingDto building) {
		return buildingService.createBuilding(building);
	}
}
