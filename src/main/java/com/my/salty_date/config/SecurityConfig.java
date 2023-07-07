package com.my.salty_date.config;



import com.my.salty_date.config.jwt.TokenProvider;
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

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity    //(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final UserDetailService userDetailService;
    private final DataSource dataSource;
    private final TokenProvider tokenProvider;
//    private final Bcrypt bcrypt;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        JwtLoginFilter loginFilter = new JwtLoginFilter(authentication -> authentication);
//        JwtCheckFilter checkFilter = new JwtCheckFilter(authentication -> authentication, memberService);


        return http

                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAt(checkFilter, BasicAuthenticationFilter.class)

//                .addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests()
                .antMatchers("/dating/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().permitAll()
                .and()

                .formLogin()
                .loginPage("/members/login").permitAll()
                .defaultSuccessUrl("/", false)
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()

                .build();
    }








    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


