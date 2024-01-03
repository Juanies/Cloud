package sebas.juan.demo.helpers.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

public class Token {
    public static void webToken(String usuario, String password) {

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        try {
            String jwt = Jwts.builder()
                .claim("username", usuario)
                .claim("password", password)

                .setIssuedAt(now)
                .signWith(key)
                .compact();
        } catch (Exception e) {
            System.out.println("Error al verificar el JWT: " + e.getMessage());
        }
    }


    public static void validateToken(){

    }
}
