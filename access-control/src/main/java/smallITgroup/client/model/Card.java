package smallITgroup.client.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString // Lombok annotation to generate a toString() method
@Getter    // Lombok annotation to generate getters for all fields
public class Card {
	Integer cardId;                 // Unique identifier for the card
	@Setter
	Boolean isActive;               // Indicates whether the card is currently active
	@Setter
	LocalDate inactiveDate;        // Date when the card was deactivated (if applicable)

	// Constructor to initialize the card with id, active status, and inactive date
	public Card(Integer cardId, Boolean isActive, LocalDate inactiveDate) {
		super();
		this.cardId = cardId;
		this.isActive = isActive;
		this.inactiveDate = inactiveDate;
	}	
	
}
