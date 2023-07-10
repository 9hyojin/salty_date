package com.my.salty_date.config.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyResult {

    private boolean success;
    private String memEmail;

    


}
