package smallITgroup.client.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import smallITgroup.client.dto.DoorReaderDto;

@Getter
@EqualsAndHashCode(of = "id")
public class Building {
	Integer id;
	String buildingName;
	Set<DoorReader> doors;
	
	public Building(int id, String buildingName) {
		this.id = id;
		this.buildingName = buildingName;
		Set<DoorReader> doors = new HashSet<DoorReader>();
	}

}
