package sebas.juan.demo.helpers.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

public class Token {

    private static Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static String webToken(String username, String password) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(secretKey)
                .compact();
    }




    public static boolean valid(String token){
        boolean res = false;
        try {
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(secretKey)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getSubject(String token) {
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payloadJson = new String(decoder.decode(chunks[1]));

        int start = payloadJson.indexOf("\"sub\":\"") + "\"sub\":\"".length();
        int end = payloadJson.indexOf("\"", start);
        String sub = payloadJson.substring(start, end);

        return sub;
    }

    // Clase para representar el contenido del payload JSON
    private static class Payload {
        String sub; // Campo "sub"
    }
}
