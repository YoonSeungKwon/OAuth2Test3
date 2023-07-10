package yoon.test.oAuthTest3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import yoon.test.oAuthTest3.config.handler.LoginSuccessHandler;
import yoon.test.oAuthTest3.config.provider.MemberAuthenticationProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginSuccessHandler successHandler;
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
                    auth.anyRequest().permitAll();
                })

                //LoginForm
                .formLogin(form->{
                    form.loginPage("/login.html");
                    form.loginProcessingUrl("/auth/member/login");
                    form.usernameParameter("email");
                    form.passwordParameter("password");
                    form.successHandler(successHandler);
                })

                //Authentication Provider
                .authenticationProvider(authenticationProvider)

                //Session
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //OAuth2
                //.oauth2Login(login->login.userInfoEndpoint(user->user.userService(null)))

                .build();
    }

}
