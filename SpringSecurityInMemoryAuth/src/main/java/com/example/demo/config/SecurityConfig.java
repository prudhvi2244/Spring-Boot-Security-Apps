package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 1.Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("prudhvi").password("{noop}prudhvi").authorities("USER");
		auth.inMemoryAuthentication().withUser("employee").password("{noop}employee").authorities("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").authorities("ADMIN");
		auth.inMemoryAuthentication().withUser("student").password("{noop}student").authorities("STUDENT");

	}

	// 2.Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers("/welcome").authenticated()
			.antMatchers("/employee").hasAnyAuthority("EMPLOYEE")
			.antMatchers("/student").hasAnyAuthority("STUDENT")
			.antMatchers("/admin").hasAnyAuthority("ADMIN")
			.and()
			.formLogin()
			.defaultSuccessUrl("/welcome",true)
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			.exceptionHandling()
			.accessDeniedPage("/denied")
			
			;

	}

}
