package smallITgroup.door.service;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.building.dao.exeption.BuildingNotFoundExeption;
import smallITgroup.building.model.Building;
import smallITgroup.client.dao.exeptions.CardHolderNotFoundExeption;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dao.exeption.DoorNotFoundExeption;
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
		
	    if (building.getDoors() == null) {
	        building.setDoors(new HashSet<>());
	    }

	    building.getDoors().add(door);

	    buildingRepository.save(building);
		
		doorRepository.save(door);
		
		System.out.println(door);
		
		return true;
	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DoorDto removeDoor(String doorId) {
		Door door = doorRepository.findById(doorId).orElseThrow(() -> new DoorNotFoundExeption());
		doorRepository.delete(door);
		return modelMapper.map(door, DoorDto.class);	
	}
}
