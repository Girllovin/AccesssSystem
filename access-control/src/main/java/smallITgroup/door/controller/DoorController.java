package smallITgroup.door.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.service.DoorService;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class DoorController {
	
	final DoorService doorService;
	
	@PostMapping("/door/new")
	public boolean createDoor(@RequestBody DoorDto door) {
		return doorService.createDoor(door);
	}
}
