package iti.smb.service.service;

import iti.smb.service.exception.MemberNotFoundException;
import iti.smb.service.model.entity.Member;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.MemberReq;
import iti.smb.service.model.network.response.MemberRes;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService implements CrudInterface<MemberReq, MemberRes, Long> {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Header<MemberRes> create(MemberReq req) {
        Member member = Member.builder()
                .name(req.getName())
                .jobPosition(req.getJobPosition())
                .build();

        Member newMember = memberRepository.save(member);

        return Header.OK(response(newMember));
    }

    @Override
    public Header<List<MemberRes>> list() {
        List<Member> memberList = memberRepository.findByDeletedFalse();
        List<MemberRes> memberResList = new ArrayList<>();

        for (Member member : memberList) {
            memberResList.add(response(member));
        }

        return Header.OK(memberResList);
    }

    @Override
    public Header<MemberRes> read(Long id) {
        return memberRepository.findById(id)
                .map(member -> Header.OK(response(member)))
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Header<MemberRes> update(MemberReq req) {
        return memberRepository.findById(req.getId())
                .map(member -> {
                    if(!StringUtils.isEmpty(req.getName())) member.setName(req.getName());
                    if(!StringUtils.isEmpty(req.getJobPosition())) member.setJobPosition(req.getJobPosition());

                    memberRepository.save(member);
                    return Header.OK(response(member));
                })
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setDeleted(true);
                    memberRepository.save(member);
                    return Header.DELETE();
                })
                .orElseThrow(MemberNotFoundException::new);
    }

    private MemberRes response(Member member) {
        MemberRes response = MemberRes.builder()
                .id(member.getId())
                .name(member.getName())
                .jobPosition(member.getJobPosition())
                .build();

        return response;
    }
}
