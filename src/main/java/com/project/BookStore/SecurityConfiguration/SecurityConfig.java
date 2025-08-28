package com.project.BookStore.SecurityConfiguration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private UserDetailsService service;
	
	public SecurityConfig (UserDetailsService service) {
		this.service = service;
	}
	
// THIS BELOW MENTIONED METHOD WILL ALLOWED ANY TYPE OF USER TO DO UPDATE, DELETE OR ADD DATA (WHICH CAN BE RISKY)	
	
/*	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security.csrf(customizer -> customizer.disable())  //THIS LINE DISABLE SPRING SECURITY DEFAULT LOGIN FORM
		.authorizeHttpRequests(request -> request.anyRequest().authenticated())  //THIS LINE ALLOW SPRING TO AUTHENTICATE USER 
//		.formLogin(Customizer.withDefaults())    //THIS LINE IS ENABLE FORM LOGIN
		.httpBasic(Customizer.withDefaults())    //THIS LINE IS FOR ENABLE FORM LOGIN FOR REST CLIENTS LIKE POSTMAN
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return security.build();
	}
	*/
	

//THIS CAN ONLY BE USE IF NOT FETCHING USER FROM DATABASE INSEATED HARDCODE USERNAME AND PASSWORD	
	
/*	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.withDefaultPasswordEncoder()
								.username("root")
								.password("root")
								.roles("USER")
								.build();
		return new InMemoryUserDetailsManager(user1);
		
	}  
	*/
	
	
// THIS BELOW METHOD ALLOW ONLY ADMIN FOR POST, PUT AND DELETE OTHER USER CAN ONLY SEE DATA 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		
		security.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET, "/books/**").permitAll()       // THIS LINE ALLOW OTHER USERS TO SEE DATA ONLY 
				.requestMatchers("/user/register").permitAll()     // THIS LINE ALLOW USER TO SIGNUP BUT AS A USER NOT ADMIN
				.requestMatchers(HttpMethod.POST,"/books/**").hasAnyAuthority("ADMIN") 
				.requestMatchers(HttpMethod.PUT,"/books/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/books/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()) 
				.httpBasic(Customizer.withDefaults()) 	//THIS LINE IS FOR ENABLE FORM LOGIN FOR REST CLIENTS LIKE POSTMAN
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)); // THIS LINE MEANS CHECK USERNAME AND PASSWORD FIRST THEN CREATE SESSION AND USE IT AUTHECATION INSETEAD OF FIRING QUERY EVETIME
		
		return security.build();
		
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		authProvider.setUserDetailsService(service);
		return authProvider;
	}
	

}
