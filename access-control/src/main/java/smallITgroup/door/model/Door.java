package smallITgroup.door.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "doorId")

public class Door {
	@Id
	String doorId;
	String description;
	Boolean isActive;
	Boolean isOpen;
	Boolean alarm;
	
	
}
