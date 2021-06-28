package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bencoder;

	// 1.Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		String query1="select uname,upwd,uenabled from users_tab where uname=?";
		String query2="select uname,urole from users_tab where uname=?";
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).authoritiesByUsernameQuery(query2)
				.passwordEncoder(bencoder);

	}

	// 2.Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/home").permitAll().antMatchers("/welcome").authenticated()
				.antMatchers("/employee").hasAuthority("EMPLOYEE").antMatchers("/student").hasAuthority("STUDENT")
				.antMatchers("/admin").hasAuthority("ADMIN").and().formLogin().defaultSuccessUrl("/welcome").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().exceptionHandling()
				.accessDeniedPage("/denied");
		;

	}

}
