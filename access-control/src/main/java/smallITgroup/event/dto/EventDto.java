package smallITgroup.event.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // Generates getters for all fields
@RequiredArgsConstructor // Generates a constructor
public class EventDto {
    
    Integer doorid;          // The ID of the door where the event occurred
    Integer cardId;          // The ID of the card used during the event
    String cardHolderName;   // The name of the cardholder associated with the event
    String cardStatus;       // The status of the card (e.g., active, suspended)
    String access;           // The type of access (e.g., granted, denied)
    LocalDateTime actionTime; // The timestamp when the event (access attempt) occurred
}
