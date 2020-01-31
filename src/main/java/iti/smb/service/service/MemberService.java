package iti.smb.service.service;

import iti.smb.service.domain.Member;
import iti.smb.service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Member addMember(String name) {
        return memberRepository.save(Member.builder().name(name).build());
    }

    // 멤버 제외
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
