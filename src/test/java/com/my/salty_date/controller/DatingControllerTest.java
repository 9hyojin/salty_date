package com.my.salty_date.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.salty_date.dto.DatingRequest;
import com.my.salty_date.repository.DatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class DatingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    DatingRepository datingRepository;


    @BeforeEach
    public void mockMveSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        datingRepository.deleteAll();
    }

//    @DisplayName("글 추가 성공 테스트")
//    @Test
//    public void save() throws Exception{
//        //given
//        final String url = "/save/datingForm";
//        final String title = "title";
//        final String address = "address";
//        final String content = "content";
//        final DatingRequest datingRequest = new DatingRequest(title,address,content);
//
//        final String requestBody = objectMapper.writeValueAsString(datingRequest); //객체 Json으로 직렬화
//
//        //when  요청전송
//        ResultActions result = mockMvc.perform(post(url))
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(requestBody);
//    }










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