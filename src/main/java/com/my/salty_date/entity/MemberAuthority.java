package com.my.salty_date.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member_authority")
@IdClass(MemberAuthority.class)
public class MemberAuthority implements GrantedAuthority {
    @Id
    @Column(name="mem_idx")
    private Long memIdx;

    @Id
    private String authority;
}
