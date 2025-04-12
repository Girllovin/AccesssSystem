package smallITgroup.door.service;

import smallITgroup.door.dto.DoorDto;

public interface DoorService {

	Boolean createDoor(Integer buildingId, DoorDto doorDto);

}
