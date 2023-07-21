package yoon.test.oAuthTest3.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OAuthResponse {

    private String email;

    private String name;

    private String role;

    private LocalDateTime regdate;

}
