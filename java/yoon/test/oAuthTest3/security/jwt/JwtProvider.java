package yoon.test.oAuthTest3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.test.oAuthTest3.service.MemberDetailService;
import yoon.test.oAuthTest3.vo.response.MemberResponse;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final MemberDetailService memberDetailService;

    private final String SECRET_KEY = "yoonsg4321";
    private final long acc_exp = 1000l * 60 * 30;
    private final long ref_exp = 1000l * 60 * 60 * 6;

    public String createAccessToken(String email, String name){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +acc_exp));
        String token = Jwts.builder()
                .setHeaderParam("typ", "JSON")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .claim("email", email)
                .claim("name", name)
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

    public Authentication getAuthentication(String token){
        UserDetails userDetails = memberDetailService.loadUserByUsername(this.getId(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public String getId(String token){                                     //get Id From Token

        return (String)Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("email");
    }

    public String getName(String token){                                     //get Id From Token

        return (String)Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("name");
    }

    public boolean validateToken(String token){                             //Validate Check
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public long resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("X-Refresh-Token-Idx")) {
                cookie = c;
            }
        }
        if(cookie == null)
            return -1;

        return Long.parseLong(cookie.getValue());
    }


}
