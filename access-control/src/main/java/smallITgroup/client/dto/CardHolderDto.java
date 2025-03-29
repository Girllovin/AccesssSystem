package smallITgroup.client.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardHolderDto {
	String UUID;
	String firstName;
	String lastName;
	String companyString;
	Map<Integer, LocalDateTime> cards;
	String[] permissions;
}
