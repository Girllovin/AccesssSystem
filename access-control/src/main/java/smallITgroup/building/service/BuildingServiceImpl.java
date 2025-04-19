package smallITgroup.building.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.building.dao.BuildingRepository; // Importing the Building repository to interact with the database
import smallITgroup.building.dao.exeption.BuildingNotFoundExeption; // Importing the exception for when building is not found
import smallITgroup.building.dto.BuildingDto; // Importing the BuildingDto for building data transfer
import smallITgroup.building.model.Building; // Importing the Building entity class
import smallITgroup.door.model.Door; // Importing the Door model to manage door information

@Service
@RequiredArgsConstructor // Lombok annotation for constructor-based dependency injection
public class BuildingServiceImpl implements BuildingService {

    final BuildingRepository buildingRepository; // Injecting BuildingRepository to interact with database

    @Override
    public Boolean createBuilding(BuildingDto buildingDto) {
        // Creating a new Building object from the provided DTO
        Building building = new Building(buildingDto.getId(), buildingDto.getBuildingName());
        
        // Transferring door data from DTO and setting it to the building
        building.setDoors(transferDoorsData(buildingDto));
        
        // Saving the new building to the repository (database)
        buildingRepository.save(building);
        
        System.out.println(building); // Printing the building to console for debugging
        
        return true; // Returning true to indicate the building creation was successful
    }
    
    // Helper method to transfer door data from BuildingDto to Set<Door> for the Building entity
    public static Set<Door> transferDoorsData(BuildingDto buildingDto) {        
         Set<Door> doorsList = buildingDto.getDoors().stream()
             .map(door -> new Door(door.getDoorId(), 
                     door.getDescription(), 
                     door.getIsActive(), 
                     door.getIsOpen(), 
                     door.getAlarm())) // Mapping each DoorDto to a Door entity
             .collect(Collectors.toSet()); // Collecting the doors into a Set
         
         return doorsList; // Returning the list of doors
    }

    @Override
    public BuildingDto getBuildingById(Integer id) {
        // Fetching the building by its ID from the repository
        Building building = buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundExeption());
        
        // Returning the BuildingDto with the building data
        return new BuildingDto(building.getId(), building.getBuildingName(), building.getDoors());
    }

}
