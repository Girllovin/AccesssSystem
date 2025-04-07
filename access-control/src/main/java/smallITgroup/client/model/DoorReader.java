package smallITgroup.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DoorReader {
	String doorId;
	String description;
	Boolean isActive;
	Boolean isOpen;
	Boolean alarm;
}
