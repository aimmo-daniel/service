package iti.smb.service.service;

import iti.smb.service.exception.HospitalNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Hospital;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.HospitalReq;
import iti.smb.service.model.network.response.HospitalRes;
import iti.smb.service.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService implements CrudInterface<HospitalReq, HospitalRes, String> {

    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Header<HospitalRes> create(HospitalReq req) {
        Hospital hospital = Hospital.builder()
                .code(req.getCode())
                .name(req.getName())
                .region(req.getRegion())
                .solution(req.getSolution())
                .link(req.getLink())
                .homepage(req.getHomepage())
                .build();

        Hospital newHospital = hospitalRepository.save(hospital);

        return Header.OK(response(newHospital));
    }

    @Override
    public Header<List<HospitalRes>> list() {
        List<Hospital> hospitalList = hospitalRepository.findByEndServiceFalse();
        List<HospitalRes> hospitalResList = new ArrayList<>();

        for (Hospital hospital : hospitalList) {
            hospitalResList.add(response(hospital));
        }

        return Header.OK(hospitalResList);
    }

    @Override
    public Header<HospitalRes> read(String code) {
        return hospitalRepository.findByCode(code)
                .map(hospital -> Header.OK(response(hospital)))
                .orElseThrow(HospitalNotFoundException::new);
    }

    @Override
    public Header<HospitalRes> update(HospitalReq req) {

        return hospitalRepository.findByCode(req.getCode())
                .map(hospital -> {
                    if(!StringUtils.isEmpty(req.getName())) hospital.setName(req.getName());
                    if(!StringUtils.isEmpty(req.getRegion())) hospital.setRegion(req.getRegion());
                    if(!StringUtils.isEmpty(req.getHomepage())) hospital.setHomepage(req.getHomepage());
                    if(req.getSolution() != null) hospital.setSolution(req.getSolution());
                    if(req.getLink() != null) hospital.setLink(req.getLink());

                    return hospital;
                })
                .map(newHospital -> hospitalRepository.save(newHospital))
                .map(updateHospital -> Header.OK(response(updateHospital)))
                .orElseThrow(HospitalNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return hospitalRepository.findById(id)
                .map(hospital -> {
                    hospital.setEndService(true);
                    hospitalRepository.save(hospital);

                    return Header.OK();
                })
                .orElseThrow(HospitalNotFoundException::new);
    }

    private HospitalRes response(Hospital hospital) {
        HospitalRes response = HospitalRes.builder()
                .id(hospital.getId())
                .code(hospital.getCode())
                .name(hospital.getName())
                .region(hospital.getRegion())
                .homepage(hospital.getHomepage())
                .solution(hospital.isSolution())
                .link(hospital.isLink())
                .build();

        return response;
    }
}
