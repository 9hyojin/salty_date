package com.my.salty_date.config;



import com.my.salty_date.service.MemberService;
import com.my.salty_date.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers("/css/**","/js/**","/img/**","/fonts/**");
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .requestMatchers("/dating/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/members/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .failureUrl("/members/login/error")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .build();
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


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}


//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

//        http
//                .formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                .logoutSuccessUrl("/");



//    기존방식
//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception{
//        webSecurity.ignoring().requestMatchers("/css/**","/js/**","/img/**");
//    }
//    @Configuration
//    public class SecurityConfiguration {
//        @Bean
//        public WebSecurityCustomizer webSecurityCustomizer() {
//            // antMatchers 부분도 deprecated 되어 requestMatchers로 대체
//            return (web) -> web.ignoring().requestMatchers("/css/**", "/fonts/**");
//        }
//    }



//    기존방식
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
//    @Configuration
//    public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//        }
//    }

//

//





//    //인증은 UI가 아니라 jwt를 사용하여 토큰으로 인증이라서 disable처리
//            .httpBasic().disable()
//    //Rest API를 이용한 서버라면 세션과 다르게 stateless
//    //인증정보를 보관하지 않기 때문에 굳이 불필요한 csrf 코드를 작성할 필요가 없다.
//            .csrf().disable()
//            .cors().and()
//    //authorizeHttpRequests() 여기서부터 요청에 대해 authorize를 하는 것. 기존에 authorizeRequests() 였는데 deprecated 되었음.
//            .authorizeHttpRequests((authorize) -> {
//               try {
//                    authorize
//                            //requestMatchers()로 어디로 들어오는 요청인지, 뒤에 어떻게 처리할 건지 정함.
//                            //antMatchers가 deprecated 되어 requestMatchers()로 바뀌었다.
//                            .requestMatchers("/", "/dating/**", "/members/**", "/css/**", "/fonts/**").permitAll()
////                                .requestMatchers("/save/**", "/comment/**").hasRole("USER")
//                            .requestMatchers("/admin/**").hasRole("ADMIN")
//                            // anyRequest() 나머지 모든 요청에 대해서는 authenticated() → 인증이 필요하게 했다.
//                            // anyRequest() 말고 requestMatchers(HttpMethod.POST, “/api/v1/**")와 같이 특정 메소드에 대한 End Point 접근을 막을 수도 있다.
//                            .anyRequest().authenticated()
//                            .and()
//                            //세션 설정을 하는 것으로 sessionCreationPolicy(SessionCreationPolicy.STATELESS)를 하면 세션을 사용하지 않는다.
//                            // JWT로 구현할 것이기 때문에 굳이 세션이 필요가 없다.
//                            .sessionManagement()
//                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                                .and()
////                                .addFilterBefore(new JwtFilter(memberService,secretKey), UsernamePasswordAuthenticationFilter.class);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//    });

//

