package p1.p2;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	
	
	
	
	//4. Check Token Expired or not
	
	public boolean isTokenExpired(String secretKey,String token)
	{
		return getClaims(secretKey, token).getExpiration().before(new Date(System.currentTimeMillis()))?true:false;
	}
	
	
	//3. Read Username
	public String getUsername(String secretKey,String token)
	{
		return getClaims(secretKey, token).getSubject();
	}
	
	// 2. Read or Parse Claims

	public Claims getClaims(String secretKey, String token) {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
				.parseClaimsJws(token).getBody();
		return claims;
	}

	// 1. Generate Token
	public String generateToken(String secretKey, String subject) {
		String token = Jwts.builder().setId("TS08HN9999").setIssuer("Prudhvi Raj")
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secretKey.getBytes())).compact();
		return token;
	}

}
