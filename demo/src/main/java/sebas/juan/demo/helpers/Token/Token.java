package sebas.juan.demo.helpers.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts;
public class Token {

    private static Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static String webToken(String username, String password) {
        long expirationMillis = 3600000; // 1 hora de expiración

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public static boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = ((JwtParser) ((JwtParserBuilder) Jwts.builder())
            .setSigningKey(secretKey))
            .parseClaimsJws(jwtToken);

            System.out.println("JWT verificado correctamente para el usuario: " + claimsJws.getBody().getSubject());
            return true;
        } catch (Exception e) {
            // Error al verificar el JWT
            System.out.println("Error al verificar el JWT: " + e.getMessage());
            return false;
        }
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
