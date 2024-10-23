package account.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("api/auth/signup").permitAll()
                .requestMatchers("api/auth/login").authenticated()
                .requestMatchers(HttpMethod.GET, "api/empl/payment").hasAnyRole("USER", "ACCOUNTANT")
                .requestMatchers(HttpMethod.POST, "api/empl/payments").hasRole("ACCOUNTANT")
                .requestMatchers(HttpMethod.PUT, "api/empl/payments").hasRole("ACCOUNTANT")
                .requestMatchers(HttpMethod.GET, "api/admin/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/admin/user").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "api/admin/user/role").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers().frameOptions().disable();

        return http.build();
    }
}
