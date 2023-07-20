package yoon.test.oAuthTest3.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @OneToOne
    @JoinColumn(name="token_owner")
    private Members member;

    @Column(nullable = false, length = 250)
    private String token;

    @Builder
    public RefreshToken(Members member, String token){
        this.member = member;
        this.token = token;
    }

    public long getTokenIdx(){
        return this.getIdx();
    }
}
