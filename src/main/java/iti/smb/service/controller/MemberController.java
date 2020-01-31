package iti.smb.service.controller;

import iti.smb.service.domain.Member;
import iti.smb.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 목록
    @GetMapping("/member")
    public List<Member> list() {
        return memberService.getMembers();
    }

    // 멤버 추가
    @PostMapping("/member")
    public ResponseEntity<?> addMember(@RequestBody Member resource) throws URISyntaxException {
        memberService.addMember(resource.getName());
        String url = "/member";
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    // 멤버 제외
    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().body("{}");
    }

}
