package iti.smb.service.controller;

import iti.smb.service.domain.Member;
import iti.smb.service.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @Test
    void list() throws Exception {
        List<Member> members = new ArrayList<>();
        members.add(Member.builder().id(1L).name("sangjin").build());

        given(memberService.getMembers()).willReturn(members);

        mvc.perform(get(("/member")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addMember() throws Exception {
        Member mockMember = Member.builder().id(1L).name("sangjin").build();

        mvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"sangjin\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/member"));

        verify(memberService).addMember(eq("sangjin"));
    }

    @Test
    void deleteMember() throws Exception {
        mvc.perform(delete("/member/1"))
                .andExpect(status().isOk());

        verify(memberService).deleteMember(1L);
    }


}