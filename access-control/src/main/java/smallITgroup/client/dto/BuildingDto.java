package smallITgroup.client.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuildingDto {
	Integer id;
	String buildingName;
	Set<DoorReaderDto>  doors;
}
