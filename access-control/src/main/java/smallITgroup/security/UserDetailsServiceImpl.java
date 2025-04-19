package smallITgroup.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.accounting.dao.UserAccountRepository;
import smallITgroup.accounting.model.UserAccount;

@Service // Marks this class as a service component in Spring's context
@RequiredArgsConstructor // Automatically generates a constructor with the required (final) fields
public class UserDetailsServiceImpl implements UserDetailsService {
	
	final UserAccountRepository userAccountRepository; // Repository for user accounts, used to fetch user data

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetch user account from the repository by username (if it exists)
		UserAccount userAccount = userAccountRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username)); // Throws exception if user not found

		// Map the roles from the user account to the format needed by Spring Security (e.g., "ROLE_USER")
		String[] roles = userAccount.getRoles()
									.stream()
									.map(r -> "ROLE_" + r) // Prefix "ROLE_" to each role
									.toArray(String[]::new); // Convert roles to an array of strings

		// Return a Spring Security User object containing username, password, and authorities (roles)
		return new User(username, userAccount.getPassword(), AuthorityUtils.createAuthorityList(roles));
	}

}
