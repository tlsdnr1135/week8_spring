package com.example.week8.controller;

import com.example.week8.entity.Adv;
import com.example.week8.enums.AccountRoleEnum;
import com.example.week8.repository.AdRepository;
import com.example.week8.repository.AdvRepository;
import com.example.week8.service.AdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AdControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AdService adService;

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private AdvRepository advRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
        advRepository.saveAndFlush(Adv.builder()
                .name("adv1")
                .password("1")
                .role(AccountRoleEnum.valueOf("ROLE_ADV"))
                .adIngActYn(1)
                .balance(1000L)
                .eventMoneyBalance(0L)
                .dayLimitBudget(0L)
                .build());
    }

//    @PostConstruct
////    @BeforeTransaction
//    public void createAdv(){
//        advRepository.saveAndFlush(Adv.builder()
//                .name("adv1")
//                .password("1")
//                .role(AccountRoleEnum.valueOf("ROLE_ADV"))
//                .adIngActYn(1)
//                .balance(1000L)
//                .eventMoneyBalance(0L)
//                .dayLimitBudget(0L)
//                .build());
//    }

    @Test
//    @WithMockUser(username = "adv",roles = "ADV")
    @WithUserDetails(value = "adv1", userDetailsServiceBeanName = "userDetailsService")
    @DisplayName("전체 조회 테스트")
    void findAll() throws Exception{
        String str = "$.[?(@.response == '%s')]";

        mockMvc.perform(get("/api/ad/find"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(str,"OK").exists());
        System.out.println("rr");
    }
}
