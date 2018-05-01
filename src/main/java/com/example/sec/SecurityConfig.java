package com.example.sec;


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
	private DataSource datasource;
	
	


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select login as principal, mot_pass as credentiels, 1 from utilisateur where login=?")
		.authoritiesByUsernameQuery("select login as principal, role from utilisateur where login=?");
		

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {/*
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/index","/demande","/enfant").hasRole("SALARIER");
		http.authorizeRequests().antMatchers("/index","/salarie").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");*/
		
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/index").permitAll();
		
		http.exceptionHandling().accessDeniedPage("/403");

	}

	

}
