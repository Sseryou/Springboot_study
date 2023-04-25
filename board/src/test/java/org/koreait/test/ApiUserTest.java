package org.koreait.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.users.JoinForm;
import org.koreait.models.user.UserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//@TestPropertySource(locations="application-test.properties")
public class ApiUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserSaveService service;

    private JoinForm joinForm;

    @BeforeEach
    void init() {
        joinForm = new JoinForm();
        joinForm.setUserId("user11");
        joinForm.setUserPw("12345678");
        joinForm.setUserNm("사용자05");
        joinForm.setUserPwRe("12345678");
        joinForm.setAgree(true);
    }

    @Test
    @DisplayName("회원가입 성공시 200 응답 코드")
    void joinSuccessTest() throws Exception {

        String params = String.format("{ \"userId\":\"%s\", \"userPw\":\"%s\", \"userPwRe\":\"%s\", \"userNm\":\"%s\", \"agree\":\"true\" }", joinForm.getUserId()
                ,joinForm.getUserPw()
                ,joinForm.getUserPwRe()
                ,joinForm.getUserNm());


        mockMvc.perform(post("/api/user/account")
                        .content(params)
                        .contentType("application/json"))
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("필수 요청 항목 검증 - 응답 코드 400")
    void joinDataValidation() {

    }
}
