package smallITgroup.client.model;

import lombok.AllArgsConstructor; // Lombok annotation to generate a constructor with all fields
import lombok.Getter;             // Lombok annotation to generate getters for all fields
import lombok.NoArgsConstructor;  // Lombok annotation to generate a no-argument constructor
import lombok.Setter;             // Lombok annotation to generate setters for all fields

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoorReader {
	String doorId;       // Unique identifier for the door
	String description;  // Description or name of the door
	Boolean isActive;    // Status indicating if the door is active
	Boolean isOpen;      // Status indicating if the door is currently open
	Boolean alarm;       // Status indicating if the door is in an alarm state
}
