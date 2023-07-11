package yoon.test.oAuthTest3.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yoon.test.oAuthTest3.security.handler.LoginSuccessHandler;
import yoon.test.oAuthTest3.security.jwt.JwtAuthenticationFilter;
import yoon.test.oAuthTest3.security.jwt.JwtProvider;
import yoon.test.oAuthTest3.security.provider.MemberAuthenticationProvider;
import yoon.test.oAuthTest3.service.RefreshTokenService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final LoginSuccessHandler successHandler;
    private final RefreshTokenService refreshTokenService;
    private final MemberAuthenticationProvider authenticationProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                //Auth
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/member/*", "/").permitAll();
                    auth.requestMatchers("/user/*").hasRole("USER");
                })

                //LoginForm
                .formLogin(form->{
                    form.loginPage("/api/member/login");
                    form.loginProcessingUrl("/auth/member/login");
                    form.usernameParameter("email");
                    form.passwordParameter("password");
                    form.successHandler(successHandler);
                })

                //Authentication Provider
                .authenticationProvider(authenticationProvider)

                //JWT Filter
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, refreshTokenService), UsernamePasswordAuthenticationFilter.class)

                //Session
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //OAuth2
                //.oauth2Login(login->login.userInfoEndpoint(user->user.userService(null)))

                .build();
    }

}
