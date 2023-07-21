<<<<<<< HEAD
package yoon.test.oAuthTest3.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import yoon.test.oAuthTest3.enums.Provider;
import yoon.test.oAuthTest3.enums.Role;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="oauth_member")
public class OAuthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime regdate;

    @Builder
    public OAuthMember(String email, String name, Provider provider, Role role){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.role = role;
    }

    public String getRoleKey(){
        return this.getRole().getKey();
    }
}
=======
package yoon.test.oAuthTest3.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import yoon.test.oAuthTest3.enums.Provider;
import yoon.test.oAuthTest3.enums.Role;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="oauth_member")
public class OAuthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime regdate;

    @Builder
    public OAuthMember(String email, String name, Provider provider, Role role){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.role = role;
    }

    public String getRoleKey(){
        return this.getRole().getKey();
    }
}
>>>>>>> YoonSeungKwon/master
