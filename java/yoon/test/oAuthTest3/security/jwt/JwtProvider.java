package yoon.test.oAuthTest3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yoon.test.oAuthTest3.service.MemberDetailService;
import yoon.test.oAuthTest3.vo.response.MemberResponse;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final MemberDetailService memberDetailService;

    private final String SECRET_KEY = "yoonsg4321";
    private final long acc_exp = 1000l * 60 * 30;
    private final long ref_exp = 1000l * 60 * 60 * 24 * 7 * 2;

    public String createAccessToken(MemberResponse memberResponse){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +acc_exp));
        String token = Jwts.builder()
                .setHeaderParam("typ", "JSON")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .claim("email", memberResponse.getEmail())
                .claim("name", memberResponse.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }

    public String createRefreshToken(){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +ref_exp));
        String token = Jwts.builder()
                .setHeaderParam("typ", "JSON")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }

}
