package smallITgroup.door.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoorDto {
	String doorId;
	String description;
	Boolean isActive;
	Boolean isOpen;
	Boolean alarm;
}
