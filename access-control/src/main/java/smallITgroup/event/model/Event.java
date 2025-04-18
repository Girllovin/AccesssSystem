package smallITgroup.event.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class Event {
	@Id
	Long id;
	String doorid;
	Integer cardId;
	String cardHolderName;
	String cardStatus;
	String access;
	LocalDateTime actionTime;
	Boolean newEvents;
	
}
