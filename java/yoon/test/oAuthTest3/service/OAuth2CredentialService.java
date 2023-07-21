package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.OAuthMember;
import yoon.test.oAuthTest3.enums.Provider;
import yoon.test.oAuthTest3.enums.Role;
import yoon.test.oAuthTest3.repository.OAuth2MemberRepository;
import yoon.test.oAuthTest3.vo.request.OAuthRequest;
import yoon.test.oAuthTest3.vo.response.OAuthResponse;

@Service
@RequiredArgsConstructor
public class OAuth2CredentialService {

    private final OAuth2MemberRepository oAuth2MemberRepository;

    private OAuthResponse toResponse(OAuthMember oAuthMember){
        return new OAuthResponse(oAuthMember.getEmail(), oAuthMember.getName(), oAuthMember.getRoleKey(), oAuthMember.getRegdate());
    }
    public OAuthResponse save(OAuthRequest oAuthRequest){
        String provider = oAuthRequest.getProvider();
        Provider prov;
        if(provider.equals("Google"))
            prov = Provider.Google;
        else
            prov = Provider.Naver;

        OAuthMember member = OAuthMember.builder()
                .email(oAuthRequest.getEmail())
                .name(oAuthRequest.getName())
                .provider(prov)
                .role(Role.USER)
                .build();

        return toResponse(oAuth2MemberRepository.save(member));
    }

}
