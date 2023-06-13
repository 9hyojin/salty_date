package com.my.salty_date.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class DatingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("관리자 페이지 권한테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public  void adminPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/page"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("관리자페이지 일반유저 접근테스트")
    @WithMockUser(username = "user", roles = "USER")
    public void adminPageNotAccessTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/page"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}