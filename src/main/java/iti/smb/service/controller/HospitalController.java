package iti.smb.service.controller;

import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.HospitalReq;
import iti.smb.service.model.network.response.HospitalRes;
import iti.smb.service.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/hospital")
@RestController
public class HospitalController implements CrudInterface<HospitalReq, HospitalRes, String> {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // 신규 병원 추가
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<HospitalRes> create(@RequestBody HospitalReq request) {
        return hospitalService.create(request);
    }

    // 병원 목록 조회
    @Override
    @GetMapping
    public Header<List<HospitalRes>> list() {
        return hospitalService.list();
    }

    // 병원 상세정보 조회
    @Override
    @GetMapping("/{code}")
    public Header<HospitalRes> read(@PathVariable("code") String code) {
        return hospitalService.read(code);
    }

    // 병원 정보 수정
    @Override
    @PatchMapping
    public Header<HospitalRes> update(@RequestBody HospitalReq request) {
        return hospitalService.update(request);
    }

    // 병원 서비스 종료
    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return hospitalService.delete(id);
    }

}
