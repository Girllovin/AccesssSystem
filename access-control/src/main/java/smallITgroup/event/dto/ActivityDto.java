package smallITgroup.event.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityDto {
	String doorId;         
    Integer cardId;         
    LocalDateTime actionTime; 
}
