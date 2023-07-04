package com.my.salty_date.config;



import com.my.salty_date.service.MemberService;
import com.my.salty_date.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final UserDetailService userDetailService;
    private final CustomAuthDetails customAuthDetails;
    private final DataSource dataSource;


//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/css/**","/js/**","/img/**","/fonts/**");
//    }


    @Bean
    SessionRegistry sessionRegistry(){
        SessionRegistryImpl registry = new SessionRegistryImpl();
        return  registry;
    }


    @Bean
    PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        try {
            repository.removeUserTokens("1");
        }catch (Exception e){
            repository.setCreateTableOnStartup(true);
        }
        return repository;
    }

    @Bean
    PersistentTokenBasedRememberMeServices rememberMeServices(){
        PersistentTokenBasedRememberMeServices service = new PersistentTokenBasedRememberMeServices(
                "hello",userDetailService,tokenRepository());
        service.setAlwaysRemember(true);  //테스트시 로그인기억하기 매번 누르기 번거로울때 사용
        return service;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/dating/**").authenticated()
                .requestMatchers("/admin/**","/session/**","/sessions/**").hasRole("ADMIN")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .requestMatchers("/css/**","/js/**","/img/**","/fonts/**").permitAll()
                .anyRequest().permitAll()
                .and()

                .formLogin()
                .loginPage("/members/login").permitAll()
                .defaultSuccessUrl("/", false)
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .authenticationDetailsSource(customAuthDetails)
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()

                .rememberMe(
                        r->r
                                .rememberMeServices(rememberMeServices())
                )

                .sessionManagement(
                        s->s
                                .maximumSessions(1)  //동시접속 불가. 맥시멈 세션 1개
                                .maxSessionsPreventsLogin(false)  // 나중에 들어온 세션을 유지, 기존세션 만료처리
                                .expiredUrl("/")
                )

//                .exceptionHandling()      //admin페이지 user 접근금지
//                .accessDeniedPage("")
//                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}






