package iti.smb.service.service;

import iti.smb.service.domain.Member;
import iti.smb.service.exception.MemberNotFoundException;
import iti.smb.service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 멤버 목록
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    // 멤버 추가
    @Transactional
    public void addMember(String name) {
        memberRepository.save(Member.builder().name(name).build());
    }

    // 멤버 제외
    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        member.setDeleted(true);

        memberRepository.save(member);
    }

}
