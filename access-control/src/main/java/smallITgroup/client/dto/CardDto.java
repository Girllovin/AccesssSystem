package smallITgroup.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CardDto {
	int cardId;
	Boolean isActive;
	LocalDateTime inactiveDateTime;	
}
