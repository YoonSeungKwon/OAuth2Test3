package yoon.test.oAuthTest3.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import yoon.test.oAuthTest3.security.handler.LoginSuccessHandler;
import yoon.test.oAuthTest3.security.jwt.JwtAuthenticationFilter;
import yoon.test.oAuthTest3.security.jwt.JwtProvider;
import yoon.test.oAuthTest3.security.provider.MemberAuthenticationProvider;
import yoon.test.oAuthTest3.service.OAuth2CustomService;
import yoon.test.oAuthTest3.service.RefreshTokenService;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final LoginSuccessHandler successHandler;
    private final RefreshTokenService refreshTokenService;
    private final MemberAuthenticationProvider authenticationProvider;
    private final OAuth2CustomService oAuth2CustomService;

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
                .oauth2Login(login->login.userInfoEndpoint(user->user.userService(oAuth2CustomService)))

                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization"));
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET, POST, PUT, DElETE, PATCH"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;


    }

}
