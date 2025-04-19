package smallITgroup.building.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.building.dao.exeption.BuildingNotFoundExeption;
import smallITgroup.building.dto.BuildingDto;
import smallITgroup.building.model.Building;
import smallITgroup.door.dao.DoorRepository;
import smallITgroup.door.dto.DoorDto;
import smallITgroup.door.model.Door;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService{

	final BuildingRepository buildingRepository;

	@Autowired
    private DoorRepository doorRepository;
	
	@Override
	public Boolean createBuilding(BuildingDto buildingDto) {
		doorRepository.deleteAll();
		
		Building building = new Building(buildingDto.getId(), buildingDto.getBuildingName());
		
		building.setDoors(transferDoorsData(buildingDto));
		
		buildingRepository.save(building);
		
		System.out.println(building);
		
		return true;
	}
	
	public static Set<Door> transferDoorsData(BuildingDto buildingDto) {		
		 Set<Door> doorsList = buildingDto.getDoors().stream()
				.map(door -> new Door(door.getDoorId(), 
						door.getDescription(), 
						door.getIsActive(), 
						door.getIsOpen(), 
						door.getAlarm()))
				.collect(Collectors.toSet());
		 
		 return doorsList;
		
	}

	@Override
	public BuildingDto getBuildingById(Integer id) {
		Building building = buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundExeption());
		return new BuildingDto(building.getId(), building.getBuildingName(), building.getDoors());
	}
 
}
