package xrddev.practiceportal.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.service.user_service.CustomUserDetailsService;


@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   org.springframework.security.config.annotation.web.builders.HttpSecurity http,
                                                   DaoAuthenticationProvider authProvider) throws Exception {
        http
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/public/**", "/error/**").permitAll()
                        .requestMatchers("/student/**").hasRole(UserRole.STUDENT.name())
                        .requestMatchers("/professor/**").hasRole(UserRole.PROFESSOR.name())
                        .requestMatchers("/company/**").hasRole(UserRole.COMPANY.name())
                        .requestMatchers("/admin/**").hasRole(UserRole.PRACTICE_OFFICE.name())
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler())
                        .failureUrl("/?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/public/**")
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (req, res, auth) -> {
            var authorities = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            if (authorities.contains("ROLE_PROFESSOR")) {
                res.sendRedirect("/professor/dashboard");
            }
            else if (authorities.contains("ROLE_STUDENT")) {
                res.sendRedirect("/student/dashboard");
            }
            else if (authorities.contains("ROLE_COMPANY")) {
                res.sendRedirect("/company/dashboard");
            }
            else if (authorities.contains("ROLE_PRACTICE_OFFICE")) {
                res.sendRedirect("/practice-office/dashboard");
            }
            else {
                // fallback
                res.sendRedirect("/");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}