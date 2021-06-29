package com.raj.in.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.in.model.User;
import com.raj.in.service.IUserService;
import com.raj.in.util.JwtUtil;
import com.raj.in.util.UserRequest;
import com.raj.in.util.UserResponse;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private IUserService uservice;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtUtil jutil;
	
	/*
	 * http://localhost:8080/users/saveUser
	  
	   {	
	     "name":"Prudhvi",
	     "username":"prudhvi2244",
	     "password":"prudhvi",
	     "roles":["ADMIN"]
	   }
	 
	 */
	//Client Registration returns ID
	@PostMapping(value = "/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody User user)
	{
		Integer id=uservice.saveUser(user);
		String msg="User Registration Success With ID:"+id;
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	/*
	 * http://localhost:8080/users/login
	 	
	 	{	
	       "username":"prudhvi2244",
		   "password":"prudhvi"
		}
	 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest urequest)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(urequest.getUsername(),urequest.getPassword()));
		String token=jutil.generateToken(urequest.getUsername());
		String issuer="Token Issued By :"+jutil.tokenIssuer(token);
		return new ResponseEntity<UserResponse>(new UserResponse(token,issuer),HttpStatus.OK);
	}
	
	@PostMapping(value = "/welcome")
	public  ResponseEntity<String> welcome()
	{
		return new ResponseEntity<String>("Welcome User:",HttpStatus.OK);
	}
	

}
