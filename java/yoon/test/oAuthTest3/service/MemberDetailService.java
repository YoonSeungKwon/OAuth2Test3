package yoon.test.oAuthTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest3.domain.Members;
import yoon.test.oAuthTest3.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    public final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Members members = memberRepository.findMembersByEmail(username);
        if(members == null)
            throw new UsernameNotFoundException(username);
        return members;
    }

}
