package com.raj.in.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.raj.in.model.User;
import com.raj.in.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private UserRepository urepo;

	@Autowired
	private PasswordEncoder bencoder;

	@Override
	public Integer saveUser(User user) {

		user.setPassword(bencoder.encode(user.getPassword()));
		Integer id = urepo.save(user).getId();
		return id;
	}
	
	// Fetching User object by passing username as an argument
	@Override
	public Optional<User> findByUsername(String username) {
			return urepo.findByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt=findByUsername(username);
		if(opt.isEmpty())
		{
			throw new  UsernameNotFoundException("User Not Exists!");
		}
		else
		{
			User user=opt.get();
			return new org.springframework.security.core.userdetails.User(username,
					user.getPassword(),
					user.getRoles().stream()
					.map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
					
					);
		}
		
	}
	

}
