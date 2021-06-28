package p1.p2;

import io.jsonwebtoken.Claims;

public class JwtDemo1 {
	public static void main(String[] args) {

		String secretKey = "PRUDHVI";

		JwtUtil jutil=new JwtUtil();
		/*
		 * String token=jutil.generateToken(secretKey,"RAJEEV");
		 * System.out.println(token);
		 */
		
		
		  String generatedToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJUUzA4SE45OTk5IiwiaXNzIjoiUHJ1ZGh2aSBSYWoiLCJzdWIiOiJSQUpFRVYiLCJpYXQiOjE2MjQ4Nzk2ODcsImV4cCI6MTYyNDg3OTk4N30.zTZWJqAclZh_YZrou544OqYLFMjU0Hzz6XSZ7SzPits";
		  Claims claims=jutil.getClaims(secretKey,generatedToken);
		  
		  System.out.println("ID :"+claims.getId());
		  System.out.println("Subject :"+jutil.getUsername(secretKey, generatedToken));
		  System.out.println("Issuer :"+claims.getIssuer());
		  System.out.println("Issued At :"+claims.getIssuedAt());
		  System.out.println("Token Expiry Time :"+claims.getExpiration());
		  
		  System.out.println("Is Token Expired?"+jutil.isTokenExpired(secretKey, generatedToken));
		 
		

		
		
		
	}
}
