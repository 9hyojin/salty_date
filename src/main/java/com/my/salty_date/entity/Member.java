package com.my.salty_date.entity;

import com.my.salty_date.constant.Role;
import com.my.salty_date.dto.MemberRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memIdx", updatable = false)
    private Long memIdx;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(String email, String password,String name, String auth){
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = Role.USER;

    }


    @Override //권한반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    public static Member createMember(MemberRequest memberFormDto, PasswordEncoder passwordEncoder){
//        Member member = new Member();
//        member.setName(memberFormDto.getName());
//        member.setEmail(memberFormDto.getEmail());
//        String password = passwordEncoder.encode(memberFormDto.getPassword());
//        member.setPassword(password);
//        member.setRole(Role.USER);
//
//        return member;
//    }

//    public void updateRefreshToken(String refreshToken){
//        this.refreshToken = refreshToken;
//    }
//    public void destroyRefreshToken(){
//        this.refreshToken = null;
//    }

}
