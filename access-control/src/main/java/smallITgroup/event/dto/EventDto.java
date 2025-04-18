package smallITgroup.event.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventDto {
    Integer doorid;
    Integer cardId;
    String cardHolderName;
    String cardStatus;
    String access;
    LocalDateTime actionTime;
}
