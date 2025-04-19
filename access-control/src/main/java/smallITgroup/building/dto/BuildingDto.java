package smallITgroup.building.dto;

import java.util.Set; // To store the set of doors in the building

import lombok.AllArgsConstructor; // For generating a constructor with all fields
import lombok.Getter; // For generating getters for the fields
import lombok.NoArgsConstructor; // For generating a no-arguments constructor
import lombok.Setter; // For generating setters for the fields
import smallITgroup.door.model.Door; // Door class used to define doors in the building

@Getter // Automatically generates getter methods for all fields
@Setter // Automatically generates setter methods for all fields
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor // Generates a no-arguments constructor
public class BuildingDto {
	
	// Field to hold the unique ID of the building
	Integer id;

	// Field to hold the name of the building
	String buildingName;

	// A set of doors that belong to the building, each door is represented by the Door class
	Set<Door> doors;
}
