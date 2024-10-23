package account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@acme.com")
    @Column(unique = true)
    private String email;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonIgnore
    private String authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        //return List.of(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
        //return List.of(new SimpleGrantedAuthority(user.getAuthority()));

        // GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        // GrantedAuthority interface in Spring Security is used to represent roles and authorities

        /*@Override
        public Collection<? extends GrantedAuthority> getAuthorities() {  if users role is more than one in entity class use collections and this here
            return user.getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }*/


        // return List.of(new SimpleGrantedAuthority(user.getAuthority()));
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
