package smallITgroup.building.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smallITgroup.door.model.Door;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {
	Integer id;
	String buildingName;
	Set<Door> doors;
}
