package iti.smb.service.repository;

import iti.smb.service.model.entity.*;
import iti.smb.service.model.enumclass.ServiceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HistoryRepositoryTest {

    private MemberRepository memberRepository;
    private DeviceRepository serialRepository;
    private HospitalRepository hospitalRepository;
    private HistoryRepository historyRepository;

    @Autowired
    HistoryRepositoryTest(MemberRepository memberRepository, DeviceRepository serialRepository,
                          HospitalRepository hospitalRepository, HistoryRepository historyRepository) {
        this.memberRepository = memberRepository;
        this.serialRepository = serialRepository;
        this.hospitalRepository = hospitalRepository;
        this.historyRepository = historyRepository;
    }

    @Test
    public void list() {

    }

    @Test
    public void get() {
        History history = historyRepository.findById(1L).orElse(null);
        assertThat(history).isNotNull();
    }

    @Transactional
    @Test
    public void create() {
        // 접수자
        Member member = memberRepository.findById(1L).orElse(null);
        // 작업자
        Member member2 = memberRepository.findById(1L).orElse(null);
        // 병원
        Hospital hospital = hospitalRepository.findByCode("EPSH").orElse(null);


        History history = History.builder()
                .receiveDate(LocalDateTime.now())
                .receiveMember(member)
                .workMember(member2)
                .reception("접수내용2")
                .cause("고장원인2")
                .action("조치사항2")
                .remarks("특이사항2")
                .hospital(hospital)
                .status(ServiceStatus.완료)
                //.historyDeviceList()
                .build();

        History newHistory = historyRepository.save(history);
        assertThat(newHistory).isNotNull();
        System.out.println(newHistory);
    }

    @Test
    public void modify() {

    }

    @Test
    public void delete() {

    }

}