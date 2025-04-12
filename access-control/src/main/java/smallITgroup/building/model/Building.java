package smallITgroup.building.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smallITgroup.door.model.Door;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Building {
	@Id
	Integer id;
	String buildingName;
	Set<Door> doors;
	
	private static int idCounter = 0;
	
	public Building(int id, String buildingName) {
		this.id = 1;
		this.buildingName = buildingName;
		@SuppressWarnings("unused")
		Set<Door> doors = new HashSet<Door>();
	}
	
//	private synchronized int generateId() {
//        return ++idCounter;
//    }

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
