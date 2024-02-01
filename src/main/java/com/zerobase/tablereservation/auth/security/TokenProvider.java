package com.zerobase.tablereservation.auth.security;

import com.zerobase.tablereservation.auth.service.AuthService;
import com.zerobase.tablereservation.auth.type.UserType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final AuthService authService;

    @Value("${spring.jwt.token-valid-time}")
    private long tokenValidTime;

    @Value("${spring.jwt.secret}")
    private String secretKey;


    /**
     * 토큰 생성(발급)
     * @param userEmail
     * @param userType
     * @return
     */
    public String createToken(String userEmail, UserType userType){
        SecretKey key = getSecretKey();

        Claims claims = Jwts.claims().setSubject(userEmail).setId(generateToken());
        claims.put("roles", userType);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // 토큰 생성 시간
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, key) // 사용할 암호화 알고리즘, 비밀키
                .compact();
    }
    public Authentication getAuthentication(String token){
        UserDetails userDetails = authService.loadUserByUsername(this.getUserName(token));

        return new UsernamePasswordAuthenticationToken(
                userDetails,"",userDetails.getAuthorities());
    }

    public String getUserName(String token){
        return parseClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        }catch (ExpiredJwtException e){
            throw new JwtException("토큰 인증 시간이 만료 되었습니다.");
        }catch (UnsupportedJwtException e){
            throw new JwtException("지원하지 않는 토큰입니다.");
        }catch (IllegalArgumentException e){
            throw new JwtException("잘못된 토큰 입니다.");
        }catch (MalformedJwtException e){
            throw new JwtException("토큰 유형이 잘못되었습니다.");
        }catch (JwtException e){
            throw new JwtException(e.getMessage());
        }
    }

    private Claims parseClaims(String token){
        SecretKey key = getSecretKey();

        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }


    private SecretKey getSecretKey(){
       return new SecretKeySpec(Base64.getDecoder()
                .decode(this.secretKey), "HmacSHA256");
    }




    private String generateToken(){
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(token.getBytes());

            return Base64.getEncoder().encodeToString(bytes);

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }


}
