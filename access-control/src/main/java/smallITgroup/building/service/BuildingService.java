package smallITgroup.building.service;

import smallITgroup.building.dto.BuildingDto; // Importing the BuildingDto class to work with building data

public interface BuildingService {

    // Method to create a new building, returns true if successful, otherwise false
    Boolean createBuilding(BuildingDto buildingDto);

    // Method to fetch a building by its ID, returns the BuildingDto for the given ID
    BuildingDto getBuildingById(Integer id);
}
