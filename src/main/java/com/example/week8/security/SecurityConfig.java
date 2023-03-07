package com.example.week8.security;


//import com.example.week5_spring.security.filter.AutenticationManager;

import com.example.week8.security.filter.LoginFailHandler;
import com.example.week8.security.filter.LoginFilter;
import com.example.week8.security.filter.LoginSucessHandler;
import com.example.week8.security.jwtfilter.AccessDeniedHandlerImpl;
import com.example.week8.security.jwtfilter.AuthenticationEntryPointImpl;
import com.example.week8.security.jwtfilter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;

    //TODO 정리하기
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        List<String> cors = new ArrayList<>();
        cors.add("http://localhost:3000");//서버에서 -> 프론트  //프론트 -> 서버
//        asd.add("http://localhost:8080");//서버에서 -> 프론트  //프론트 -> 서버
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(cors);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addExposedHeader("ImSulbinHeader");
        configuration.addExposedHeader("ROLE_GROUP");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //Spring Security Filter에서 무시해야 하는 RequestMatcher 인스턴스 설정 시 사용
        web
                .ignoring()
                .antMatchers("/h2-console/**")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //특정 HTTP 요청에 대한 웹 기반 보안 구성 시 사용

        http.cors().configurationSource(corsConfigurationSource());
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().disable()   ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.

        //TODO addFilterAt안되는 이유
        http.addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.authorizeHttpRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.GET,"/api/account/all").permitAll()
                .antMatchers(HttpMethod.GET,"/api/account/test").authenticated()
                .antMatchers(HttpMethod.GET,"/api/account/user").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/api/account/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/account/join").permitAll()
                .mvcMatchers(HttpMethod.POST,"/api/account/login").permitAll()
                .anyRequest().authenticated();
    }

    //로그인 필터
    @Bean
    public LoginFilter formLoginFilter() throws Exception {
        LoginFilter formLoginFilter = new LoginFilter();
        formLoginFilter.setAuthenticationManager(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/api/account/login");
        formLoginFilter.setAuthenticationSuccessHandler(new LoginSucessHandler());
        formLoginFilter.setAuthenticationFailureHandler(new LoginFailHandler());
        return formLoginFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //default: bcrypt
    }

}
