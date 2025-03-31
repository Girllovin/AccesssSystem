package smallITgroup.client.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Card {
	Integer cardId;
	@Setter
	Boolean isActive;
	@Setter
	LocalDateTime inactiveDate;
	
	public Card(Integer cardId, Boolean isActive, LocalDateTime inactiveDate) {
		super();
		this.cardId = cardId;
		this.isActive = isActive;
		this.inactiveDate = inactiveDate;
	}	
	
	
}
