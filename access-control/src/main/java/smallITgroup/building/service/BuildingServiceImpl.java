package smallITgroup.building.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository;
import smallITgroup.building.dto.BuildingDto;
import smallITgroup.building.model.Building;
import smallITgroup.client.model.DoorReader;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService{

	final BuildingRepository buildingRepository;
	
	@Override
	public Boolean createBuilding(BuildingDto buildingDto) {
		Building building = new Building(buildingDto.getId(), buildingDto.getBuildingName());
		
		building.setDoors(transferDoorsData(buildingDto));
		
		buildingRepository.save(building);
		
		System.out.println(building);
		
		return true;
	}
	
	private Set<DoorReader> transferDoorsData(BuildingDto buildingDto) {		
		 Set<DoorReader> doorsList = buildingDto.getDoors().stream()
				.map(door -> new DoorReader(door.getDoorId(), 
						door.getDescription(), 
						door.getIsActive(), 
						door.getIsOpen(), 
						door.getAlarm()))
				.collect(Collectors.toSet());
		 
		 return doorsList;
		
	}
 
}
