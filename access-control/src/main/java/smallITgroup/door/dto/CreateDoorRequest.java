package smallITgroup.door.dto;

import lombok.Data;

@Data // Generates getters, setters, toString, equals, and hashCode methods
public class CreateDoorRequest {

    private Integer buildingId; // ID of the building where the door will be created
    private DoorDto door;       // Door details to be created
}
