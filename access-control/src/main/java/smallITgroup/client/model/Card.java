package smallITgroup.client.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Card {
	Integer cardId;
	@Setter
	Boolean isActive;
	@Setter
	LocalDate inactiveDate;
	
	public Card(Integer cardId, Boolean isActive, LocalDate inactiveDate) {
		super();
		this.cardId = cardId;
		this.isActive = isActive;
		this.inactiveDate = inactiveDate;
	}	
	
	
}
