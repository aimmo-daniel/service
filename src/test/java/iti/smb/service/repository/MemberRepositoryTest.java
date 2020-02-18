package iti.smb.service.repository;

import iti.smb.service.model.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void list() {
        List<Member> memberList = memberRepository.findAll();
        log.info("memberList", memberList);
    }

    @Transactional
    @Test
    public void create() {
        Member member = new Member();
        member.setName("예상진");

        Member newMember = memberRepository.save(member);
        assertThat(newMember).isNotNull();
        assertThat(newMember.getName()).isEqualTo("예상진");
    }

    @Transactional
    @Test
    public void modify() {
        Member member = memberRepository.findById(1L).orElse(null);
        member.setName("최룡");

        memberRepository.save(member);

        assertThat(member.getName()).isEqualTo("최룡");
    }

    @Transactional
    @Test
    public void delete() {
        Optional<Member> member = memberRepository.findById(1L);

        assertThat(member.isPresent()).isTrue();

        member.ifPresent(m -> {
            memberRepository.delete(m);
        });

        Optional<Member> deleteMember = memberRepository.findById(1L);

        assertThat(deleteMember.isPresent()).isFalse();

    }

}