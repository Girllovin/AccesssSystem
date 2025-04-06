package smallITgroup.client.dao.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameNotCorrectExeption extends RuntimeException{

	private static final long serialVersionUID = 5080455837442128177L;
	
		
		
		

	}