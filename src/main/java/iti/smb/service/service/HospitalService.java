package iti.smb.service.service;

import iti.smb.service.controller.dto.HospitalDto;
import iti.smb.service.domain.Hospital;
import iti.smb.service.exception.HospitalNotFoundException;
import iti.smb.service.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> hospitalList() {
        return hospitalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hospital hospitalInfo(Long id) {
        return hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
    }

    @Transactional
    public void addHospital(HospitalDto hospitalDto) {
        Hospital hospital = new Hospital();
        hospital.set(hospitalDto);

        hospitalRepository.save(hospital);
    }

    @Transactional
    public void modifyHospital(Long id, HospitalDto hospitalDto) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
        hospital.set(hospitalDto);

        hospitalRepository.save(hospital);
    }

    @Transactional
    public void delHoispital(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
        hospital.setService(false);

        hospitalRepository.save(hospital);
    }
}
