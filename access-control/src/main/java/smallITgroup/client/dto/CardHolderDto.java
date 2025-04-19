package smallITgroup.client.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString // Lombok annotation to generate a toString() method
@Getter    // Lombok annotation to generate getters for all fields
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class CardHolderDto {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	java.util.UUID uuid; // Unique identifier for the cardholder

	String firstName;    // First name of the cardholder
	String lastName;     // Last name of the cardholder
	String company;      // Company the cardholder belongs to

	@NotNull
	Map<Integer, LocalDate> cards; // Map of card IDs to their inactive dates

	HashSet<String> permissions; // Set of permissions associated with the cardholder
}
