package account;

import account.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        if (userRepository.existsUserByEmail((user.getEmail()))) {
            throw new RuntimeException("Email is already in use.");
        }
        return userRepository.save(user);
    }
}

class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        //return List.of(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
        //return List.of(new SimpleGrantedAuthority(user.getAuthority()));
        // GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        // GrantedAuthority interface in Spring Security is used to represent roles and authorities
        return List.of(new SimpleGrantedAuthority(user.getAuthority()));
        /*@Override
        public Collection<? extends GrantedAuthority> getAuthorities() {  if users role is more than one in entity class use collections and this here
            return user.getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }*/
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

@Service
class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                // .findUserByUsername(username)
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return new CustomUserDetails(user);
    }
}