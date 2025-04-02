package smallITgroup.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CardDto {
	int cardId;
	Boolean isActive;
	LocalDateTime inactiveDateTime;	
}
