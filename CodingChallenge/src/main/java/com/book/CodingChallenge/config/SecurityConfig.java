package com.book.CodingChallenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.book.CodingChallenge.enums.Role;
import com.book.CodingChallenge.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> auth
            		   .requestMatchers("/auth/token").permitAll() 
            		   .requestMatchers("/error").permitAll()
            		   .requestMatchers("/auth/signup").permitAll()  
            		   .requestMatchers("/retrieve-all").hasAnyRole(Role.USER.toString(),Role.ADMIN.toString())
            		   .requestMatchers("/retrieve-single-book-by-ISBN/{isbn}").hasAnyRole(Role.USER.toString(),Role.ADMIN.toString())
            		   .requestMatchers("/add-new-book").hasRole(Role.ADMIN.toString())
            		   .requestMatchers("/update-existing-book").hasRole(Role.ADMIN.toString())
            		   .requestMatchers("/delete-book-by-ISBN/{isbn}").hasRole(Role.ADMIN.toString())
                   
               )
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );

       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
	
	@Bean 
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userDetailsService);
       authProvider.setPasswordEncoder(getEncoder());
       return authProvider;
   }
	
}
