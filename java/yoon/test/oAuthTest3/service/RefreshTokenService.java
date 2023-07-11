package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.Members;
import yoon.test.oAuthTest3.domain.RefreshToken;
import yoon.test.oAuthTest3.repository.MemberRepository;
import yoon.test.oAuthTest3.repository.RefreshTokenRepository;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    public String findTokenByIdx(long tokenIdx ){
        return refreshTokenRepository.findRefreshTokenByIdx(tokenIdx).getToken();
    }
    public long saveOrUpdateToken(String email, String token){
        Members member = memberRepository.findMembersByEmail(email);
        RefreshToken refToken = refreshTokenRepository.findByMember(member)
                .map(entity -> updateToken(entity, token))
                .orElse(RefreshToken.builder()
                        .member(member)
                        .token(token)
                        .build());

        refreshTokenRepository.save(refToken);

        return refToken.getTokenIdx();
    }

    public RefreshToken updateToken(RefreshToken refToken, String token){
        refToken.setToken(token);
        return refToken;
    }

    public boolean checkToken(String email, long tokenIdx){
        try {
            Members member = memberRepository.findMembersByEmail(email);
            RefreshToken token = refreshTokenRepository.findRefreshTokenByMember(member);
            if(refreshTokenRepository.findRefreshTokenByIdx(tokenIdx).getToken().equals(token.getToken()))
                return true;
        }catch (Exception ignored){
        }
        return false;
    }

}
