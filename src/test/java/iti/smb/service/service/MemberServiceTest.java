package iti.smb.service.service;

import iti.smb.service.domain.Member;
import iti.smb.service.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MemberServiceTest {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.memberService = new MemberService(memberRepository);
    }

    @Test
    void list() {
        List<Member> mockMembers = new ArrayList<>();

        mockMembers.add(Member.builder().id(1L).name("sangjin").build());
        mockMembers.add(Member.builder().id(2L).name("ryong").build());
        mockMembers.add(Member.builder().id(3L).name("jaehan").build());

        given(memberRepository.findAll()).willReturn(mockMembers);

        List<Member> members = memberService.getMembers();

        Member member = members.get(0);

        assertThat(member.getName()).isEqualTo("sangjin");
    }

    @Test
    void addMember() {
        Long id = 1L;
        String name = "sangjin";

        Member mockMember = Member.builder().id(id).name(name).build();

        given(memberRepository.save(any())).willReturn(mockMember);



        verify(memberRepository).save(any());
    }

    @Test
    void deleteMember() {
        Long id = 1L;

        memberService.deleteMember(id);

        verify(memberRepository).deleteById(any());
    }



}
