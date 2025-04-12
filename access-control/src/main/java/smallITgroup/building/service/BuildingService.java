package smallITgroup.building.service;

import smallITgroup.building.dto.BuildingDto;

public interface BuildingService {
	
	Boolean createBuilding(BuildingDto buildingDto);
	
	BuildingDto getBuildingById(Integer id);

}
