package sebas.juan.demo.helpers.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class Token {

    private static Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    
    public static String webToken(String usuario, String password) {


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        String jwt = Jwts.builder()
                .claim("username", usuario)
                .claim("password", password)

                .setIssuedAt(now)
                .signWith(secretKey)
                .compact();

        return jwt;

    }
    public static boolean validateToken(String jwtToken) {
        boolean resultado = false; 

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();

            String username = claims.get("username", String.class);
            String passwordFromToken = claims.get("password", String.class);
            System.out.println(username);
            resultado = true;

        } catch (Exception e) {
            System.out.println("Error al validar el token: " + e.getMessage());
        }
        return resultado;
    }

    public static String getName(String jwtToken) {
        String resultado = null; 

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();

            String username = claims.get("username", String.class);
            resultado = username;

        } catch (Exception e) {
            System.out.println("Error al coger el nombre: " + e.getMessage());
        }
        return resultado;
    }


}
