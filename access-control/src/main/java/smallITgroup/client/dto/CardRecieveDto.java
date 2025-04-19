package smallITgroup.client.dto;

import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Lombok annotation to generate getters for all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class CardRecieveDto {

	// A map where the key is the card ID (Integer) and the value is the card's inactive date
	Map<Integer, LocalDate> dto;
}
