package smallITgroup.door.dto;

import lombok.Data;

@Data
public class CreateDoorRequest {
    private Integer buildingId;
    private DoorDto door;
}