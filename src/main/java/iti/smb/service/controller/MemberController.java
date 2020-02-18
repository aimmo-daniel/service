package iti.smb.service.controller;

import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.MemberReq;
import iti.smb.service.model.network.response.MemberRes;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/member")
@RestController
public class MemberController implements CrudInterface<MemberReq, MemberRes, Long> {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 추가
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<MemberRes> create(@RequestBody MemberReq request) {
        System.out.println(request);
        return memberService.create(request);
    }

    // 멤버 목록
    @Override
    @GetMapping
    public Header<List<MemberRes>> list() {
        return memberService.list();
    }

    // 멤버 상세정보 조회
    @Override
    @GetMapping("/{id}")
    public Header<MemberRes> read(@PathVariable("id") Long id) {
        return memberService.read(id);
    }

    // 멤버 정보 수정
    @Override
    @PatchMapping
    public Header<MemberRes> update(@RequestBody MemberReq request) {
        return memberService.update(request);
    }

    // 멤버 삭제
    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return memberService.delete(id);
    }
}
