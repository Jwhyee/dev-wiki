package com.dev.wiki.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    private MockMvc mockMvc;

    @Test
    public void printLoginPageTest() {
        assertThat(memberController.showLoginPage()).isEqualTo("login-page");
    }

    @Test
    public void mockLoginTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/member/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("login-page"));
    }

}