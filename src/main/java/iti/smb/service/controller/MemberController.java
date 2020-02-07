package iti.smb.service.controller;

import iti.smb.service.domain.Member;
import iti.smb.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/api/member")
@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 목록
    @GetMapping
    public List<Member> list() {
        return memberService.getMembers();
    }

    // 멤버 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestBody Member resource) throws URISyntaxException {
        memberService.addMember(resource.getName());
    }

    // 멤버 제외
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }

}
