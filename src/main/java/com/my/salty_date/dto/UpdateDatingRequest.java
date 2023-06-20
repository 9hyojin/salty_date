package com.my.salty_date.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateDatingRequest {
    private String datingTitle;
    private String datingAddress;
    private String datingContent;
}
