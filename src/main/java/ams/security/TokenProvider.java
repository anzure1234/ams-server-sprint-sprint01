package ams.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    @Value("${app.security.secret-key}")
    private String secretKey;

    public String generateToken(Authentication authentication) {
        // ROLE_ADMIN
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        LocalDateTime expiredTime = LocalDateTime.now().plusHours(1);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", roles)
                .setExpiration(Date.from(expiredTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            List<GrantedAuthority> authorityList = Arrays.stream(claims.get("authorities").toString().split(","))
                                                        .map(SimpleGrantedAuthority::new)
                                                        .collect(Collectors.toList());
            User principal = new User(claims.getSubject(), "", authorityList);
            return new UsernamePasswordAuthenticationToken(principal, null, authorityList);
        } catch (Exception e) {
            return null;
        }
    }

}
