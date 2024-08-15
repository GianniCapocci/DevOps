package gr.hua.dev_ops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import gr.hua.dev_ops.service.impl.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    CustomUserDetailsServiceImpl customUserDetailsServiceImpl() {
        return new CustomUserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(htpSecurity -> htpSecurity.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/api/client/register").permitAll()
                        .requestMatchers("/api/broker/register").permitAll()
                        .requestMatchers("/api/admin/listings/**").hasRole("BROKER")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/broker/**").authenticated()
                        .requestMatchers("/api/bookmark/**").authenticated()
                        .requestMatchers("/api/client/**").permitAll()
                        .requestMatchers("/api/image/**").hasAnyRole("BROKER", "ADMIN")
                        .requestMatchers("/api/listing/**").authenticated()
                        .requestMatchers("/api/owner/**").hasAnyRole("BROKER", "ADMIN")
                        .requestMatchers("/api/review/**").authenticated()
                        .requestMatchers("/api/user/**").authenticated()
                        .requestMatchers("/listings/add").hasRole("BROKER")
                        .requestMatchers("/admin/update/listing/**").hasRole("BROKER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/broker/**").hasAnyRole("BROKER", "ADMIN")
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/success", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
