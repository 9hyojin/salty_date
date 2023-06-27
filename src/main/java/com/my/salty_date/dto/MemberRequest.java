package com.my.salty_date.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class MemberRequest {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;

    @NotEmpty(message = "")
    @Length(min = 8, message = "비밀번호 8자리 이상 입력해주세요")
    private String password;

    @Builder
    public MemberRequest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

}


