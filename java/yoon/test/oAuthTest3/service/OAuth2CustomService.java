<<<<<<< HEAD
package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.OAuthMember;
import yoon.test.oAuthTest3.enums.Provider;
import yoon.test.oAuthTest3.enums.Role;
import yoon.test.oAuthTest3.repository.OAuth2MemberRepository;
import yoon.test.oAuthTest3.vo.response.OAuth2Attribute;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2CustomService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuth2MemberRepository oAuth2MemberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User user = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String attributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute attribute = of(registrationId, attributeName, user.getAttributes());

        OAuthMember oAuthMember = saveOAuth(attribute);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(oAuthMember.getRoleKey())), attribute.getAttributes(), attribute.getAttributeKey()
        );
    }

    public static OAuth2Attribute of(String registrationId, String attributeName, Map<String, Object> attributes){
           if("naver".equals(registrationId));
           else if ("kakao".equals(registrationId));
           return ofGoogle(attributeName, attributes);
    }

    public static OAuth2Attribute ofGoogle(String attributeName, Map<String, Object> attributes){
        return OAuth2Attribute.builder()
                .email(String.valueOf(attributes.get("email")))
                .name(String.valueOf(attributes.get("name")))
                .provider(Provider.Google)
                .attributeKey(attributeName)
                .attributes(attributes)
                .build();
    }

    public OAuthMember saveOAuth(OAuth2Attribute attribute){

        OAuthMember oAuthMember = oAuth2MemberRepository.findByEmail(attribute.getEmail())
                .map(entity -> update(entity, attribute.getName()))
                .orElse(toEntity(attribute));

        return oAuth2MemberRepository.save(oAuthMember);
    }

    public OAuthMember update(OAuthMember oAuthMember, String name){
        oAuthMember.setName(name);
        return oAuthMember;
    }

    public OAuthMember toEntity(OAuth2Attribute attribute){
        return OAuthMember.builder()
                .email(attribute.getEmail())
                .name(attribute.getName())
                .provider(attribute.getProvider())
                .role(Role.USER)
                .build();
    }

}
=======
package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.OAuthMember;
import yoon.test.oAuthTest3.enums.Provider;
import yoon.test.oAuthTest3.enums.Role;
import yoon.test.oAuthTest3.repository.OAuth2MemberRepository;
import yoon.test.oAuthTest3.vo.response.OAuth2Attribute;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2CustomService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuth2MemberRepository oAuth2MemberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User user = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String attributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute attribute = of(registrationId, attributeName, user.getAttributes());

        OAuthMember oAuthMember = saveOAuth(attribute);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(oAuthMember.getRoleKey())), attribute.getAttributes(), attribute.getAttributeKey()
        );
    }

    public static OAuth2Attribute of(String registrationId, String attributeName, Map<String, Object> attributes){
           if("naver".equals(registrationId));
           else if ("kakao".equals(registrationId));
           return ofGoogle(attributeName, attributes);
    }

    public static OAuth2Attribute ofGoogle(String attributeName, Map<String, Object> attributes){
        return OAuth2Attribute.builder()
                .email(String.valueOf(attributes.get("email")))
                .name(String.valueOf(attributes.get("name")))
                .provider(Provider.Google)
                .attributeKey(attributeName)
                .attributes(attributes)
                .build();
    }

    public OAuthMember saveOAuth(OAuth2Attribute attribute){

        OAuthMember oAuthMember = oAuth2MemberRepository.findByEmail(attribute.getEmail())
                .map(entity -> update(entity, attribute.getName()))
                .orElse(toEntity(attribute));

        return oAuth2MemberRepository.save(oAuthMember);
    }

    public OAuthMember update(OAuthMember oAuthMember, String name){
        oAuthMember.setName(name);
        return oAuthMember;
    }

    public OAuthMember toEntity(OAuth2Attribute attribute){
        return OAuthMember.builder()
                .email(attribute.getEmail())
                .name(attribute.getName())
                .provider(attribute.getProvider())
                .role(Role.USER)
                .build();
    }

}
>>>>>>> YoonSeungKwon/master
