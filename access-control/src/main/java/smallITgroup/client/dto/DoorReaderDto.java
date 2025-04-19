package smallITgroup.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Lombok annotation that generates getters for all fields
@NoArgsConstructor // Lombok annotation that generates a no-argument constructor
public class DoorReaderDto {
	String doorId;       // Unique identifier for the door
	String description;  // Description or label for the door
	Boolean isActive;    // Indicates if the door is currently active
	Boolean isOpen;      // Indicates if the door is currently open
	Boolean alarm;       // Indicates if the alarm is triggered on this door
}