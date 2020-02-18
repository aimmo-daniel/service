package iti.smb.service.service;

import iti.smb.service.exception.HistoryNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.History;
import iti.smb.service.model.entity.MainCategory;
import iti.smb.service.model.entity.SubCategory;
import iti.smb.service.model.entity.ThirdCategory;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.HistoryReq;
import iti.smb.service.model.network.response.HistoryRes;
import iti.smb.service.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HistoryService implements CrudInterface<HistoryReq, HistoryRes, Long> {

    private final HistoryRepository historyRepository;
    private final HospitalRepository hospitalRepository;
    private final MemberRepository memberRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ThirdCategoryRepository thirdCategoryRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, HospitalRepository hospitalRepository,
                          MemberRepository memberRepository, MainCategoryRepository mainCategoryRepository,
                          SubCategoryRepository subCategoryRepository, ThirdCategoryRepository thirdCategoryRepository) {
        this.historyRepository = historyRepository;
        this.hospitalRepository = hospitalRepository;
        this.memberRepository = memberRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.thirdCategoryRepository = thirdCategoryRepository;
    }


    @Override
    public Header<HistoryRes> create(HistoryReq req) {
        History history = History.builder()
                .receiveDate(req.getReceiveDate())
                .endDate(req.getEndDate())
                .reception(req.getReception())
                .cause(req.getCause())
                .action(req.getAction())
                .remarks(req.getRemarks())
                .status(req.getStatus())
                .hospital(hospitalRepository.getOne(req.getHospitalId()))
                .build();

        if(!StringUtils.isEmpty(req.getReceiveMemberId())) history.setReceiveMember(memberRepository.getOne(req.getReceiveMemberId()));
        if(!StringUtils.isEmpty(req.getWorkMemberId())) history.setWorkMember(memberRepository.getOne(req.getWorkMemberId()));
        //if(!StringUtils.isEmpty(req.getMainCategoryId())) history.setMainCategory(mainCategoryRepository.getOne(req.getMainCategoryId()));
        if(!StringUtils.isEmpty(req.getSubCategoryId())) history.setSubCategory(subCategoryRepository.getOne(req.getSubCategoryId()));
        if(!StringUtils.isEmpty(req.getThirdCategoryId())) history.setThirdCategory(thirdCategoryRepository.getOne(req.getThirdCategoryId()));

        History newHistory = historyRepository.save(history);

        // TODO : Insert는 되는데 response() 메소드 실행시 오류남

        return Header.OK(response(newHistory));
    }

    @Override
    public Header<List<HistoryRes>> list() {
        List<History> historyList = historyRepository.findByDeletedFalse();

        List<HistoryRes> historyResList = new ArrayList<>();

        for (History history : historyList) {
            historyResList.add(response(history));
        }

        return Header.OK(historyResList);
    }

    @Override
    public Header<HistoryRes> read(Long id) {
        return historyRepository.findById(id)
                .map(history -> Header.OK(response(history)))
                .orElseThrow(HistoryNotFoundException::new);
    }

    @Override
    public Header<HistoryRes> update(HistoryReq req) {
        return historyRepository.findById(req.getId())
                .map(history -> {
                    if(!StringUtils.isEmpty(req.getReceiveDate())) history.setReceiveDate(req.getReceiveDate());
                    if(!StringUtils.isEmpty(req.getEndDate())) history.setEndDate(req.getEndDate());
                    if(!StringUtils.isEmpty(req.getReceiveMemberId())) history.setReceiveMember(memberRepository.getOne(req.getReceiveMemberId()));
                    if(!StringUtils.isEmpty(req.getWorkMemberId())) history.setWorkMember(memberRepository.getOne(req.getWorkMemberId()));
                    if(!StringUtils.isEmpty(req.getReception())) history.setReception(req.getReception());
                    if(!StringUtils.isEmpty(req.getCause())) history.setCause(req.getCause());
                    if(!StringUtils.isEmpty(req.getAction())) history.setAction(req.getAction());
                    if(!StringUtils.isEmpty(req.getRemarks())) history.setRemarks(req.getRemarks());
                    if(!StringUtils.isEmpty(req.getHospitalId())) history.setHospital(hospitalRepository.getOne(req.getHospitalId()));
                    if(!StringUtils.isEmpty(req.getMainCategoryId())) history.setMainCategory(mainCategoryRepository.getOne(req.getMainCategoryId()));
                    if(!StringUtils.isEmpty(req.getSubCategoryId())) history.setSubCategory(subCategoryRepository.getOne(req.getSubCategoryId()));
                    if(!StringUtils.isEmpty(req.getThirdCategoryId())) history.setThirdCategory(thirdCategoryRepository.getOne(req.getThirdCategoryId()));
                    if(!StringUtils.isEmpty(req.getStatus())) history.setStatus(req.getStatus());

                    return history;
                })
                .map(newHistory -> historyRepository.save(newHistory))
                .map(updateHistory -> Header.OK(response(updateHistory)))
                .orElseThrow(HistoryNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return historyRepository.findById(id)
                .map(history -> {
                    history.setDeleted(true);
                    historyRepository.save(history);

                    return Header.OK();
                })
                .orElseThrow(HistoryNotFoundException::new);
    }

    private HistoryRes response(History history) {
        HistoryRes response = HistoryRes.builder()
                .id(history.getId())
                .receiveDate(history.getReceiveDate())
                .endDate(history.getEndDate())
                .receiveMember(history.getReceiveMember())
                .workMember(history.getWorkMember())
                .reception(history.getReception())
                .cause(history.getCause())
                .action(history.getAction())
                .remarks(history.getRemarks())
                .hospital(history.getHospital())
                .mainCategory(history.getMainCategory())
                .subCategory(history.getSubCategory())
                .thirdCategory(history.getThirdCategory())
                .status(history.getStatus())
                .build();

        return response;
    }

}

