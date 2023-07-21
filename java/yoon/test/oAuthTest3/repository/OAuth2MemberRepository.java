<<<<<<< HEAD
package yoon.test.oAuthTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.oAuthTest3.domain.OAuthMember;

import java.util.Optional;

@Repository
public interface OAuth2MemberRepository extends JpaRepository<OAuthMember, Long> {

    OAuthMember findOAuthMemberByEmail(String email);

    Optional<OAuthMember> findByEmail(String email);
}
=======
package yoon.test.oAuthTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.oAuthTest3.domain.OAuthMember;

import java.util.Optional;

@Repository
public interface OAuth2MemberRepository extends JpaRepository<OAuthMember, Long> {

    OAuthMember findOAuthMemberByEmail(String email);

    Optional<OAuthMember> findByEmail(String email);
}
>>>>>>> YoonSeungKwon/master
