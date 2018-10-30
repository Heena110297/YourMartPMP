package com.nagarro.YourMartPMPAdminPanel.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource myDataSource ;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(myDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	
		http.authorizeRequests().antMatchers("/").hasRole("ADMIN").and()
		    .formLogin()
		      .loginPage("/showMyLoginForm")
				.loginProcessingUrl("/authenticateTheUser")
				  .permitAll()
				    .and()
				      .logout().permitAll()
				      .and().exceptionHandling()
				      .accessDeniedPage("/access-denied");
	}
	
	

}
