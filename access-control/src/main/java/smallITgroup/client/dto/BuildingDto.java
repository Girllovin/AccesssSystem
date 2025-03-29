package smallITgroup.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuildingDto {
	int id;
	String buildingName;
	DoorReaderDto[] doors;
}
