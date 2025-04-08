package smallITgroup.building.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smallITgroup.client.dto.DoorReaderDto;
import smallITgroup.client.model.DoorReader;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Building {
	@Id
	Integer id;
	String buildingName;
	Set<DoorReader> doors;
	
	private static int idCounter = 0;
	
	public Building(int id, String buildingName) {
		this.id = generateId();
		this.buildingName = buildingName;
		@SuppressWarnings("unused")
		Set<DoorReader> doors = new HashSet<DoorReader>();
	}
	
	private synchronized int generateId() {
        return ++idCounter;
    }

    public int getId() {
        return id;
    }

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Building.idCounter = idCounter;
	}

}
