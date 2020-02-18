package iti.smb.service.repository;

import iti.smb.service.model.entity.Hospital;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class HospitalRepositoryTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    public void list() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        log.info("hospitalList : ", hospitalList);
    }

    @Test
    public void get() {
        Optional<Hospital> hospital = hospitalRepository.findByCode("EPSH");

        assertThat(hospital).isNotNull();
        assertThat(hospital.get().getName()).isEqualTo("은평성모병원");
    }

    @Transactional
    @Test
    public void create() {
        Hospital hospital = Hospital.builder()
                .code("EPSH")
                .name("은평성모병원")
                .region("서울")
                .homepage("www.epsh.co.kr").build();

        Hospital newHospital = hospitalRepository.save(hospital);
        assertThat(newHospital.getCode()).isEqualTo("EPSH");
        assertThat(newHospital.getName()).isEqualTo("은평성모병원");
    }

    @Transactional
    @Test
    public void modify() {
        Hospital hospital = hospitalRepository.findByCode("EPSH").orElse(null);
        assertThat(hospital.getName()).isEqualTo("은평성모병원");

        hospital.setName("조성모병원");

        hospitalRepository.save(hospital);

        assertThat(hospital.getCode()).isEqualTo("EPSH");
        assertThat(hospital.getName()).isEqualTo("조성모병원");
    }

    @Transactional
    @Test
    public void EndService() {
        Optional<Hospital> hospital = hospitalRepository.findByCode("EPSH");
        assertThat(hospital.get().getName()).isEqualTo("은평성모병원");

        assertThat(hospital.isPresent()).isTrue();

        hospital.ifPresent(h -> {
            hospitalRepository.delete(h);
        });

        Optional<Hospital> deleteHospital = hospitalRepository.findByCode("EPSH");

        assertThat(deleteHospital.isPresent()).isFalse();
    }

}