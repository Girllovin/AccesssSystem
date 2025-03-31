package smallITgroup.client.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EventDto {
    int doorid;
    String cardHolderName;
    String cardStatus;
    String access;
    LocalDateTime actionTime;
}
