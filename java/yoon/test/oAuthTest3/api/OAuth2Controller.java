package yoon.test.oAuthTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.oAuthTest3.security.jwt.JwtProvider;
import yoon.test.oAuthTest3.service.OAuth2CredentialService;
import yoon.test.oAuthTest3.vo.request.OAuthRequest;
import yoon.test.oAuthTest3.vo.response.OAuthResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth2")
public class OAuth2Controller {

    private final OAuth2CredentialService oAuth2CredentialService;
    private final JwtProvider jwtProvider;
    @PostMapping("/google/callback")
    public ResponseEntity<?> googleCallBack(@RequestBody OAuthRequest oAuthRequest){

        if("".equals(oAuthRequest.getEmail()) && "".equals(oAuthRequest.getName()) && "".equals(oAuthRequest.getProvider()))
            return ResponseEntity.badRequest().body("No Item");

        OAuthResponse result = oAuth2CredentialService.save(oAuthRequest);

        String token = jwtProvider.createAccessToken(oAuthRequest.getEmail(), oAuthRequest.getName());

        return ResponseEntity.ok(token);
    }


}
