package smallITgroup.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

// Lombok annotations for generating getter methods and a toString method
@ToString
@Getter
public class CardDto {
    
    // Card ID for the unique identifier of the card
    Integer cardId;
    
    // Indicates whether the card is active (true or false)
    Boolean isActive;
    
    // Date and time when the card becomes inactive (if applicable)
    LocalDateTime inactiveDateTime;  
}
