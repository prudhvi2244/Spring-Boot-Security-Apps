package com.example.demo;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Your Password:");
		String password=sc.next();
		
		
		BCryptPasswordEncoder bencoder=new BCryptPasswordEncoder();
		String encodedPassword=bencoder.encode(password);
		
		System.out.println("Encoded Password :"+encodedPassword);
		
	}
	
}
