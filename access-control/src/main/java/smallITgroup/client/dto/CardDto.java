package smallITgroup.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CardDto {
	Integer cardId;
	Boolean isActive;
	LocalDateTime inactiveDateTime;	
}
