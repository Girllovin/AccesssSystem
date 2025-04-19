package smallITgroup.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDto {
	Boolean open;
	Integer openDelay;
	Boolean close;
	Integer closeDelay;
	Boolean alarm;
	String access;
}
