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

@Service // Marks this class as a service to be managed by Spring
@RequiredArgsConstructor // Generates a constructor with required (final) fields
public class DoorServiceImpl implements DoorService {
	
	final DoorRepository doorRepository; // Door repository for saving and retrieving door data
	final BuildingRepository buildingRepository; // Building repository for accessing building data

	@Override
	public Boolean createDoor(Integer buildingId, DoorDto doorDto) {
		//  Create a new Door object from the DoorDto received
		Door door = new Door(doorDto.getDescription());

		//  Retrieve the building by its ID or throw an exception if not found
		Building building = buildingRepository.findById(buildingId)
				.orElseThrow(() -> new BuildingNotFoundExeption()); // Throws exception if building not found
		
		//  Ensure the doors set exists for the building
	    if (building.getDoors() == null) {
	        building.setDoors(new HashSet<>()); // Initialize if it's null
	    }

	    //  Add the new door to the building's doors set
	    building.getDoors().add(door);

	    //  Save the updated building back to the repository
	    buildingRepository.save(building);
		
		// Save the new door to the door repository
		doorRepository.save(door);
		
		System.out.println(door); // Debug output (could be replaced by logging)
		
		// Return true to indicate that the door was successfully created
		return true;
	}
}
