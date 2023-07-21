package yoon.test.oAuthTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.oAuthTest3.domain.Members;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findMembersByEmail(String email);

    Optional<Members> findByEmail(String email);

}
