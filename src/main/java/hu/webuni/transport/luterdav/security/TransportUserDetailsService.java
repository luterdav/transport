package hu.webuni.transport.luterdav.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TransportUserDetailsService implements UserDetailsService {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static List<UserObject> users = new ArrayList<>();

	public TransportUserDetailsService() {
		users.add(new UserObject("Peter", passwordEncoder().encode("1234"), "TRANSPORTMANAGER"));
		users.add(new UserObject("Joe", passwordEncoder().encode("1234"), "ADDRESSMANAGER"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserObject> user = users.stream()
				.filter(u -> u.name.equals(username))
				.findAny();
		
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		
		return User.withUsername(user.get().name)
                .password(user.get().password)
                .roles(user.get().role).build();

	}

	private static class UserObject {
		private String name;
		private String password;
		private String role;

		public UserObject(String name, String password, String role) {
			this.name = name;
			this.password = password;
			this.role = role;
		}
	}

}
