package com.raj.in.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secretkey}")
	private String secretkey;
	
	//7 . Who Issued the token
	public String tokenIssuer(String token)
	{
		return getClaims(token).getIssuer();
	}
	
	//6 . Validate username in token,database and expiration of token
	public boolean validateToken(String token,String username)
	{
		String tokenUsername=getUsernameFromToken(token);
		return (username.equals(tokenUsername) && !isTokenExpired(token));
	}
	
	//5. Check For Token Expiration Time
	public boolean isTokenExpired(String token)
	{
		Date tokenExpDT=getClaims(token).getExpiration();
		return tokenExpDT.after(new Date(System.currentTimeMillis()));
	}
	
	//4 . Read Subject/Username from token
	public String getUsernameFromToken(String token)
	{
		return getClaims(token).getSubject();
	}
	
	//3. Read Token Expiration
	public Date getTokenExp(String token)
	{
		return getClaims(token).getExpiration();
	}
	
	//2. Read Claims
	public Claims getClaims(String token)
	{
		Claims claims=Jwts.parser()
				          .setSigningKey(Base64.getEncoder().encode(secretkey.getBytes()))
				          .parseClaimsJws(token).getBody();
		return claims;
	}
	
	
	// 1 . Generate Token
	public String generateToken(String subject)
	{
		String token=Jwts.builder()
		    .setId("TS08HN9999")
		    .setIssuer("RAJ PRUDHVI")
		    .setIssuedAt(new Date(System.currentTimeMillis()))
		    .setSubject(subject)
		    .signWith(SignatureAlgorithm.HS256,Base64.getEncoder().encode(secretkey.getBytes()))
		    .setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(2)))
		    .compact()
		    ;
		return token;
	}
	
}
