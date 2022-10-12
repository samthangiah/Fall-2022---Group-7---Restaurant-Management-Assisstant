
package edu.sru.group7.restaurantmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import
org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import
org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.
EnableWebSecurity; import
org.springframework.security.config.annotation.web.configuration.
WebSecurityConfigurerAdapter; import
org.springframework.security.crypto.password.PasswordEncoder;

import edu.sru.group7.restaurantmanager.authentication.ApplicationUserService;

@SuppressWarnings("deprecation")

@Configuration

@EnableWebSecurity public class ApplicationSecurityConfig extends
WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;

	@Autowired public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService)
	{ this.passwordEncoder = passwordEncoder;
	this.applicationUserService = applicationUserService; }

	protected void configure(HttpSecurity http) throws Exception { 
		http
			.authorizeRequests() 
			.antMatchers("/", "/index", "/css/*", "/js/*", "/img/*", "/assets/*").permitAll()
			.antMatchers("/tempemployeelogin").hasRole(ApplicationUserRole.ADMIN.name())
			.anyRequest() 
			.authenticated() 
			.and() 
			.formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
		
	}


}

