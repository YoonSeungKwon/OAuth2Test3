package yoon.test.oAuthTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.oAuthTest3.domain.Members;
import yoon.test.oAuthTest3.domain.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findRefreshTokenByIdx(long idx);
    RefreshToken findRefreshTokenByMember(Members members);

    Optional<RefreshToken> findByMember(Members members);
}