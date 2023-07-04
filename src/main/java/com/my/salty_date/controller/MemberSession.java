package com.my.salty_date.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class MemberSession {

    private String name;
    private List<SessionInfo> sessions;

    public int getCount(){
        return sessions.size();
    }

}
