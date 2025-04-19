package smallITgroup.door.service;

import smallITgroup.door.dto.DoorDto;

public interface DoorService {

	// Creates a new door for the specified building
	// Returns true if the operation was successful
	Boolean createDoor(Integer buildingId, DoorDto doorDto);
	DoorDto removeDoor (String doorId);


}
