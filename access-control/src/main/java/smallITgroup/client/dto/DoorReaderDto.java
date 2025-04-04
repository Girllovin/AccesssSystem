package smallITgroup.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DoorReaderDto {
	String doorId;
	String description;
	Boolean isActive;
	Boolean isOpen;
	Boolean alarm;
}
