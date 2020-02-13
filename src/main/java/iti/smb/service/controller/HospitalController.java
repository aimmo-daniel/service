package iti.smb.service.controller;

import iti.smb.service.controller.dto.HospitalDto;
import iti.smb.service.domain.Hospital;
import iti.smb.service.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/hospital")
@RestController
public class HospitalController {

    private HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // 병원목록 조회
    @GetMapping
    public List<Hospital> hospitalList() {
        return hospitalService.hospitalList();
    }

    // 병원 상세정보 조회
    @GetMapping("/{id}")
    public Hospital hospitalInfo(@PathVariable("id") Long id) {
        return hospitalService.hospitalInfo(id);
    }

    // 신규병원 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addHospitalInfo(@RequestBody HospitalDto hospitalDto) {
        hospitalService.addHospital(hospitalDto);
    }

    // 병원정보 수정
    @PatchMapping("/{id}")
    public void modifyHospitalInfo(@PathVariable("id") Long id, HospitalDto hospitalDto) {
        hospitalService.modifyHospital(id, hospitalDto);
    }

    // 병원 계약 종료
    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable("id") Long id) {
        hospitalService.delHoispital(id);
    }

}
