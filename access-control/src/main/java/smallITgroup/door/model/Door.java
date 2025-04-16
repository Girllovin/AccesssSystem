package smallITgroup.door.model;

import java.util.Random;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "doorId")

public class Door {
	@Id
	String doorId;
	String description;
	Boolean isActive;
	Boolean isOpen;
	Boolean alarm;
	
	public Door(String description) {
		this.doorId = generateID();
		this.description = description;
		isActive = null;
		isOpen = null;
		alarm = null;
	}
	
    private synchronized String generateID() {
        Random random = new Random();

        char letter = (char) ('A' + random.nextInt(26));

        int number = random.nextInt(1000);
        String formattedNumber = String.format("%03d", number);
        
        System.out.println(letter + formattedNumber);

        return letter + formattedNumber;
    }	
}
