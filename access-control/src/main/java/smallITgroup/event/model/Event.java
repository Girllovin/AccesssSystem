package smallITgroup.event.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // Generates getters for all fields
@RequiredArgsConstructor // Generates a constructor with required (final) fields
public class Event {

	@Id
	Long id;                    // Unique identifier for the event (used by MongoDB)
	String doorid;              // The ID of the door where the event occurred
	Integer cardId;             // The ID of the card used during the event
	String cardHolderName;      // The name of the cardholder associated with the event
	String cardStatus;          // The status of the card (e.g., active, suspended)
	String access;              // The type of access (e.g., granted, denied)
	LocalDateTime actionTime;   // Timestamp indicating when the event (access) occurred
	Boolean newEvents;          // Flag to indicate whether this event is a new event
}
