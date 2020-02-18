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
    private SerialRepository serialRepository;
    private MainCategoryRepository mainCategoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ThirdCategoryRepository thirdCategoryRepository;
    private HospitalRepository hospitalRepository;
    private HistoryRepository historyRepository;

    @Autowired
    HistoryRepositoryTest(MemberRepository memberRepository, SerialRepository serialRepository,
                          MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository,
                          ThirdCategoryRepository thirdCategoryRepository, HospitalRepository hospitalRepository, HistoryRepository historyRepository) {
        this.memberRepository = memberRepository;
        this.serialRepository = serialRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.thirdCategoryRepository = thirdCategoryRepository;
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
        // 대분류
        MainCategory mainCategory = mainCategoryRepository.findById(2L).orElse(null);
        // 중분류
        SubCategory subCategory = subCategoryRepository.findById(2L).orElse(null);


        History history = History.builder()
                .receiveDate(LocalDateTime.now())
                .receiveMember(member)
                .workMember(member2)
                .reception("접수내용2")
                .cause("고장원인2")
                .action("조치사항2")
                .remarks("특이사항2")
                .hospital(hospital)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .status(ServiceStatus.완료)
                //.historySerialList()
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