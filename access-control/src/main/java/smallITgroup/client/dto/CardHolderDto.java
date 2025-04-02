package smallITgroup.client.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardHolderDto {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	java.util.UUID uuid;
	String firstName;
	String lastName;
	String companyString;
	Map<Integer, LocalDate> cards;
	HashSet<String> permissions;
}
