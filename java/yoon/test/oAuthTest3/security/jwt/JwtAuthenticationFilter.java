package yoon.test.oAuthTest3.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import yoon.test.oAuthTest3.domain.RefreshToken;
import yoon.test.oAuthTest3.service.MemberService;
import yoon.test.oAuthTest3.service.RefreshTokenService;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accToken = jwtProvider.resolveAccessToken(request);
        if(accToken != null && jwtProvider.validateToken(accToken)){
            Authentication authentication = jwtProvider.getAuthentication(accToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        long tokenIdx= jwtProvider.resolveRefreshToken(request);
        if(tokenIdx != -1 && jwtProvider.validateToken(refreshTokenService.findTokenByIdx(tokenIdx))){
            String email = jwtProvider.getId(accToken);
            if(refreshTokenService.checkToken(email, tokenIdx)){
                response.setHeader("Authorization", jwtProvider.createAccessToken(
                        email, jwtProvider.getName(accToken))
                );
            }
        }
        filterChain.doFilter(request, response);
    }
}
