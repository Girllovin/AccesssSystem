package smallITgroup.door.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Generates getters for all fields
@Setter // Generates setters for all fields
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor  // Generates a no-argument constructor
public class DoorDto {

	String doorId;       // Unique identifier for the door
	String description;  // Description of the door (e.g. "Main entrance")
	Boolean isActive;    // Indicates if the door is active in the system
	Boolean isOpen;      // Indicates if the door is currently open
	Boolean alarm;       // Indicates if the alarm is triggered for this door
}
