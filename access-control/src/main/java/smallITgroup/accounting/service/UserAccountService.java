package smallITgroup.accounting.service;


import java.util.List;

import smallITgroup.accounting.dto.UserDto;
import smallITgroup.accounting.dto.UserRegisterDto;
import smallITgroup.accounting.dto.UserInfoDto;


public interface UserAccountService {

	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);

	UserDto removeUser(String email);

	UserDto changeRolesList(String email, String role, boolean isAddRole);

	UserInfoDto changePassword(String email, String newPassword);
	
	List<UserInfoDto> getAllUsers();
	
	void recoveryPassword(String email);

}
