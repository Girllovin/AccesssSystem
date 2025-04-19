package smallITgroup.event.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter // Generates getters for all fields
@RequiredArgsConstructor // Generates a constructor with required (final) fields
@AllArgsConstructor
public class Event {

	@Id
	Long id;                    // Unique identifier for the event (used by MongoDB)
	String doorid;              // The ID of the door where the event occurred
	Integer cardId;             // The ID of the card used during the event
	String cardHolderName;      // The name of the cardholder associated with the event
	String access;              // The type of access (e.g., granted, denied)
	LocalDateTime actionTime;   // Timestamp indicating when the event (access) occurred
	Boolean newEvents;          // Flag to indicate whether this event is a new event
	
	public Object setNewEvents(boolean flag) {
		this.newEvents = flag;
		return null;
	}
}
