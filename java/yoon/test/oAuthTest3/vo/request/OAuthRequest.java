package yoon.test.oAuthTest3.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OAuthRequest {

    private final String email;

    private final String name;

    private final String provider;

}
