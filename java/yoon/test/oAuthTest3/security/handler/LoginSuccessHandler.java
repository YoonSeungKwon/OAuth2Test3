package yoon.test.oAuthTest3.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import yoon.test.oAuthTest3.domain.Members;
import yoon.test.oAuthTest3.security.jwt.JwtProvider;
import yoon.test.oAuthTest3.service.RefreshTokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Members member = (Members) authentication.getPrincipal();
        String accToken = jwtProvider.createAccessToken(member.getEmail(), member.getName());
        String refToken = jwtProvider.createRefreshToken();
        long tokenIdx = refreshTokenService.saveOrUpdateToken(member.getEmail(), refToken);
        response.setHeader("Authorization", accToken);
        Cookie cookie = new Cookie("X-Refresh-Token-Idx", String.valueOf(tokenIdx));
        response.addCookie(cookie);
        response.sendRedirect("/");
    }

}
