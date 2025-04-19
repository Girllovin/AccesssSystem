package smallITgroup.door.model;

import java.util.Random;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Generates getters for all fields
@Setter // Generates setters for all fields
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
@EqualsAndHashCode(of = "doorId") // Overrides equals() and hashCode() using doorId

public class Door {

	@Id
	String doorId;         // Unique ID for the door (used as the MongoDB document ID)
	String description;    // Description of the door (e.g., "Back Entrance")
	Boolean isActive;      // Whether the door is active in the system
	Boolean isOpen;        // Whether the door is currently open
	Boolean alarm;         // Whether the alarm is triggered for this door

	// Constructor that sets description and generates default values
	public Door(String description) {
		this.doorId = generateID(); // Auto-generates a unique ID
		this.description = description;
		isActive = null;
		isOpen = null;
		alarm = null;
	}

	// Generates a random ID in format: Letter + 3-digit number (e.g., B123)
	private synchronized String generateID() {
		Random random = new Random();

		char letter = (char) ('A' + random.nextInt(26)); // Random capital letter

		int number = random.nextInt(1000); // Random number from 0 to 999
		String formattedNumber = String.format("%03d", number); // Zero-padded to 3 digits
		
		System.out.println(letter + formattedNumber); // Debug output

		return letter + formattedNumber; // Return formatted ID
	}
}
