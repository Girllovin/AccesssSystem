package smallITgroup.door.service;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.building.dao.exeption.BuildingNotFoundExeption;
import smallITgroup.building.model.Building;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.model.Door;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService{
	
	final DoorRepository doorRepository;
	final BuildingRepository buildingRepository;

	@Override
	public Boolean createDoor(Integer buildingId, DoorDto doorDto) {
		Door door = new Door(doorDto.getDescription());

		Building building = buildingRepository.findById(buildingId).orElseThrow(() -> new BuildingNotFoundExeption());
		
		// 3. Make sure doors set exists
	    if (building.getDoors() == null) {
	        building.setDoors(new HashSet<>());
	    }

	    // 4. Add the new door to the set
	    building.getDoors().add(door);

	    // 5. Save the updated building
	    buildingRepository.save(building);
		
		doorRepository.save(door);
		
		System.out.println(door);
		
		return true;
	}

}
