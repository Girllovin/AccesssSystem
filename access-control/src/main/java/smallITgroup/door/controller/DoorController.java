package smallITgroup.door.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.dto.CardHolderDto;
import smallITgroup.door.dto.CreateDoorRequest;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.service.DoorService;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class DoorController {
	
	final DoorService doorService;
	
	@PostMapping("/door/new")
	public boolean createDoor(@RequestBody CreateDoorRequest request) {
		return doorService.createDoor(request.getBuildingId(), request.getDoor());
	}
	
	@DeleteMapping("/door/remove/{doorId}")
	public DoorDto removeDoor(@PathVariable String doorId) {	
		return doorService.removeDoor(doorId);
	}
}
