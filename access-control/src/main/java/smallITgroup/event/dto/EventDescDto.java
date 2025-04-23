package smallITgroup.event.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter // Generates getters for all fields
@Setter
@RequiredArgsConstructor // Generates a constructor
public class EventDescDto {
    
    String doorDescription;          // The ID of the door where the event occurred
    Integer cardId;          // The ID of the card used during the event
    String cardHolderName;   // The name of the cardholder associated with the event
    String access;           // The type of access (e.g., granted, denied)
    LocalDateTime actionTime; // The timestamp when the event (access attempt) occurred
}
