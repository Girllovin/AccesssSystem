package smallITgroup.client.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuildingDto {
	int id;
	String buildingName;
	Set<DoorReaderDto>  doors;
}
