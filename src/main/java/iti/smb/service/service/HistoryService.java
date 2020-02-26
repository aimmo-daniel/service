package iti.smb.service.service;

import iti.smb.service.exception.HistoryNotFoundException;
import iti.smb.service.exception.SerialNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Device;
import iti.smb.service.model.entity.History;
import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.dto.SerialDto;
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
    private final CategoryRepository categoryRepository;
    private final HistoryDeviceRepository historyDeviceRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, HospitalRepository hospitalRepository,
                          MemberRepository memberRepository, CategoryRepository categoryRepository,
                          HistoryDeviceRepository historyDeviceRepository, DeviceRepository deviceRepository) {
        this.historyRepository = historyRepository;
        this.hospitalRepository = hospitalRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.historyDeviceRepository = historyDeviceRepository;
        this.deviceRepository = deviceRepository;
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
                .receiveMember(memberRepository.getOne(req.getReceiveMemberId()))
                .hospital(hospitalRepository.getOne(req.getHospitalId()))
                .build();

        if (!StringUtils.isEmpty(req.getWorkMemberId()))
            history.setWorkMember(memberRepository.getOne(req.getWorkMemberId()));
        if (!StringUtils.isEmpty(req.getCategoryId()))
            history.setCategory(categoryRepository.getOne(req.getCategoryId()));

        History newHistory = historyRepository.save(history);

        // HistoryDevice 테이블에 저장
        List<String> serialNumberList = req.getSerialNumberList();
        if(serialNumberList != null) {
            for (String serial : serialNumberList) {
                Device device = deviceRepository.findBySerialNumber(serial).orElseThrow(SerialNotFoundException::new);

                HistoryDevice historyDevice = HistoryDevice.builder()
                        .device(device)
                        .history(newHistory)
                        .build();

                historyDeviceRepository.save(historyDevice);
            }
        }

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
                    if (!StringUtils.isEmpty(req.getReceiveDate())) history.setReceiveDate(req.getReceiveDate());
                    if (!StringUtils.isEmpty(req.getEndDate())) history.setEndDate(req.getEndDate());
                    if (!StringUtils.isEmpty(req.getReceiveMemberId())) history.setReceiveMember(memberRepository.getOne(req.getReceiveMemberId()));
                    if (!StringUtils.isEmpty(req.getWorkMemberId())) history.setWorkMember(memberRepository.getOne(req.getWorkMemberId()));
                    if (!StringUtils.isEmpty(req.getReception())) history.setReception(req.getReception());
                    if (!StringUtils.isEmpty(req.getCause())) history.setCause(req.getCause());
                    if (!StringUtils.isEmpty(req.getAction())) history.setAction(req.getAction());
                    if (!StringUtils.isEmpty(req.getRemarks())) history.setRemarks(req.getRemarks());
                    if (!StringUtils.isEmpty(req.getHospitalId())) history.setHospital(hospitalRepository.getOne(req.getHospitalId()));
                    if (!StringUtils.isEmpty(req.getCategoryId())) history.setCategory(categoryRepository.getOne(req.getCategoryId()));
                    if (!StringUtils.isEmpty(req.getStatus())) history.setStatus(req.getStatus());

                    List<String> serialNumberList = req.getSerialNumberList();
                    // 시리얼넘버 목록이 있으면
                    if(serialNumberList != null) {
                        // HistoryId로 HistoryDevice 목록 조회
                        List<HistoryDevice> historyDeviceList = historyDeviceRepository.findByHistoryId(history.getId());
                        // HistoryDevice 데이터 삭제
                        for(HistoryDevice historyDevice : historyDeviceList) {
                            historyDeviceRepository.delete(historyDevice);
                        }
                        // 새로 받은 요청값의 시리얼넘버 목록 추가
                        for (String serial : serialNumberList) {
                            Device device = deviceRepository.findBySerialNumber(serial).orElseThrow(SerialNotFoundException::new);

                            HistoryDevice historyDevice = HistoryDevice.builder()
                                    .device(device)
                                    .history(history)
                                    .build();

                            historyDeviceRepository.save(historyDevice);
                        }
                    }

                    historyRepository.save(history);
                    return Header.OK(response(history));
                })
                .orElseThrow(HistoryNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return historyRepository.findById(id)
                .map(history -> {
                    history.setDeleted(true);
                    historyRepository.save(history);

                    return Header.DELETE();
                })
                .orElseThrow(HistoryNotFoundException::new);
    }

    private HistoryRes response(History history) {

        List<HistoryDevice> historyDeviceList = historyDeviceRepository.findByHistoryId(history.getId());
        List<SerialDto> serialDtoList = new ArrayList<>();

        for (HistoryDevice device : historyDeviceList) {
            SerialDto serialDto = new SerialDto();
            if (!StringUtils.isEmpty(device.getDevice())) {
                serialDto.setId(device.getDevice().getId());
                serialDto.setProduct(device.getDevice().getProduct().getName());
                serialDto.setSerialNumber(device.getDevice().getSerialNumber());
            }

            serialDtoList.add(serialDto);
        }

        HistoryRes response = HistoryRes.builder()
                .id(history.getId())
                .receiveDate(history.getReceiveDate())
                .endDate(history.getEndDate())
                .reception(history.getReception())
                .cause(history.getCause())
                .action(history.getAction())
                .remarks(history.getRemarks())
                .status(history.getStatus())
                .serialList(serialDtoList)
                .build();

        if (!StringUtils.isEmpty(history.getReceiveMember())) response.setReceiveMember(history.getReceiveMember().getName());
        if (!StringUtils.isEmpty(history.getWorkMember())) response.setWorkMember(history.getWorkMember().getName());
        if (!StringUtils.isEmpty(history.getHospital())) {
            response.setHospitalCode(history.getHospital().getCode());
            response.setHospital(history.getHospital().getName());
        }
        if (!StringUtils.isEmpty(history.getCategory())) response.setCategory(history.getCategory().getName());

        return response;
    }

}

