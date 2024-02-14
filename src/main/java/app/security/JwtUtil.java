package app.security;

import app.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtUtil {

    private final String ISSUER = "ingesis.uniquindio.edu.co";
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String usuario) {
        return buildToken(new HashMap<>(), usuario);
    }

    private String buildToken(Map<String, Object> extraClaims, String usuario) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(usuario)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public boolean isTokenValid(String token, String username) {
        final String usernameToken = extractUsername(token);
        final String issuer = extractIssuer(token);
        System.out.println(usernameToken);
        System.out.println(issuer);
        return (usernameToken.equals(username) && issuer.equals("ingesis.uniquindio.edu.co"));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    public String extractIssuer(String token) {
        return extractAllClaims(token).getIssuer();
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}