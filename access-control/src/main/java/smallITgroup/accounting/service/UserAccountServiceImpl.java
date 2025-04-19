package smallITgroup.accounting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.accounting.dao.UserAccountRepository;
import smallITgroup.accounting.dto.UserDto;
import smallITgroup.accounting.dto.UserInfoDto;
import smallITgroup.accounting.dto.UserRegisterDto;
import smallITgroup.accounting.dto.exeptions.*;
import smallITgroup.accounting.model.UserAccount;
enum Roles {
    ADMIN, MODERATOR, USER
}

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
	final UserAccountRepository userAccountRepository;
	final ModelMapper modelMapper;
	final EmailService emailService;
	final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userAccountRepository.existsById(userRegisterDto.getEmail().trim())) {
			throw new UserExistsException();
		}		
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = passwordEncoder.encode(userRegisterDto.getPassword());
		userAccount.setPassword(password);
		userAccount.addRole(Roles.USER.toString());
		userAccountRepository.save(userAccount);
		userAccountRepository.findAll().forEach(e -> System.out.println(e));
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto removeUser(String email) {
		UserAccount userAccount = userAccountRepository.findById(email).orElseThrow(UserNotFoundException::new);
		userAccountRepository.delete(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserInfoDto changePassword(String email, String newPassword) {
		UserAccount userAccount = userAccountRepository.findById(email).orElseThrow(UserNotFoundException::new);
//		userAccount.setPassword(newPassword);
		userAccount.setPassword(passwordEncoder.encode(newPassword));
		userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserInfoDto.class);
	}

	@Override
	public List<UserInfoDto> getAllUsers() {
		List<UserInfoDto> result = new ArrayList<>();
		userAccountRepository.findAll().stream()
				.map(u -> modelMapper.map(u, UserInfoDto.class))
				.forEach(u -> result.add(u)); 
				return result;
	}
	
	@Override
	public List<String> getUsersEmail() {
		  return userAccountRepository.findAll().stream()
		            .map(UserAccount::getEmail)
		            .collect(Collectors.toList());
	}

	@Override
	public void recoveryPassword(String email) {	
		// TODO generating random password with SecureRandom from Spring Security
		String passwordDefault = "We_are_the_champions";
		String passwordNew = passwordEncoder.encode(passwordDefault);
		UserAccount userAccount = userAccountRepository.findById(email.trim()).orElse(null);
		
		if (userAccount == null) {
			throw new smallITgroup.accounting.dto.exeptions.UserNotFoundException();
		}
		userAccount.setPassword(passwordNew);
		userAccountRepository.save(userAccount);
		System.out.println("User " + email + " exists") ;
		emailService.sendEmail(email, "New password for AccessControl app", "Your new password is " + passwordDefault);
		
	}

	@Override
	public UserDto changeRolesList(String email, String role, boolean isAddRole) {
		UserAccount userAccount = userAccountRepository.findById(email.trim()).orElse(null);
		if (userAccount == null) {
			throw new UserNotFoundException();
		}
		if (isAddRole) {
			userAccount.addRole(role);
		}else {
			userAccount.removeRole(role);
		}
		userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

}
