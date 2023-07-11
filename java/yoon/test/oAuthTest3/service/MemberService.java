package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.Members;
import yoon.test.oAuthTest3.domain.RefreshToken;
import yoon.test.oAuthTest3.enums.Role;
import yoon.test.oAuthTest3.repository.MemberRepository;
import yoon.test.oAuthTest3.repository.RefreshTokenRepository;
import yoon.test.oAuthTest3.vo.request.MemberDto;
import yoon.test.oAuthTest3.vo.response.MemberResponse;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Members findMember(String email){
        return memberRepository.findMembersByEmail(email);
    }

    public MemberResponse memberResponse(Members member){
        return new MemberResponse(member.getEmail(), member.getName(), member.getRoleKey(), member.getRegdate());
    }

    public MemberResponse join(MemberDto dto){

        Members member = Members.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
        if(member == null)
            throw new UsernameNotFoundException(dto.getName());

        return memberResponse(memberRepository.save(member));
    }
    public void logout(Principal principal){
        Members member = (Members) principal;
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByMember(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
