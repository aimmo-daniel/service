package iti.smb.service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import iti.smb.service.controller.dto.HistoryDto;
import iti.smb.service.exception.DateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "deleted = false") //deleted false 인것만 노출
@Table(name = "history")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class History {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id", nullable = false, updatable = false)
    private Long id;

    // 접수일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "receive_date")
    private LocalDate receiveDate;

    // 종료일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;

    // 접수자
    @JsonIgnoreProperties({"id", "deleted"})
    @ManyToOne
    @JoinColumn(name = "receive_member_id")
    private Member receiveMember;

    // 작업자
    @JsonIgnoreProperties({"id", "deleted"})
    @ManyToOne
    @JoinColumn(name = "work_member_id")
    private Member workMember;

    // 접수내용
    @Column(name = "reception")
    private String reception;

    // 고장원인
    @Column(name = "cause")
    private String cause;

    // 조치사항
    @Column(name = "action")
    private String action;

    // 특이사항
    @Column(name = "remarks")
    private String remarks;

    // FK 병원 (N:1 단방향)
    @JsonIgnoreProperties({"id", "name", "solution", "region", "link", "homepage"})
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // FK 대분류 (N:1 단방향)
    @JsonIgnoreProperties({"id"})
    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    // FK 중분류 (N:1 단방향)
    @JsonIgnoreProperties({"id", "mainCategory"})
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    // FK 소분류 (N:1 단방향)
    @JsonIgnoreProperties({"id", "subCategory"})
    @ManyToOne
    @JoinColumn(name = "third_category_id")
    private ThirdCategory thirdCategory;

    // 접수상태 (default = READY)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceStatus status = ServiceStatus.READY;

    // LAZY : 지연로딩, EAGER : 즉시로딩딩
    // 단말기 시리얼번호 목록
    @JsonIgnoreProperties({"id", "history"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "history")
    private List<HistorySerial> historySerialList;

    // 삭제 여부 (DB 에서는 지워지지 않음)
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    public void set(HistoryDto dto) {
        if(dto.getReceiveDate() != null) {
            this.setReceiveDate(dto.getReceiveDate());
        }
        if(dto.getEndDate() != null) {
            CheckLocalDate(dto.getReceiveDate(), dto.getEndDate());
            this.setEndDate(dto.getEndDate());
        }
        if(dto.getReceiveMemberId() != null) {
            this.setReceiveMember(Member.builder().id(dto.getReceiveMemberId()).build());
        }
        if(dto.getWorkMemberId() != null) {
            this.setWorkMember(Member.builder().id(dto.getWorkMemberId()).build());
        }
        if(!StringUtils.isEmpty(dto.getReception())) {
            this.setReception(dto.getReception());
        }
        if(!StringUtils.isEmpty(dto.getCause())) {
            this.setCause(dto.getCause());
        }
        if(!StringUtils.isEmpty(dto.getAction())) {
            this.setAction(dto.getAction());
        }
        if(!StringUtils.isEmpty(dto.getRemarks())) {
            this.setRemarks(dto.getRemarks());
        }
        if(dto.getHospitalId() != null) {
            this.setHospital(Hospital.builder().id(dto.getHospitalId()).build());
        }
        if(dto.getMainCategoryId() != null) {
            this.setMainCategory(MainCategory.builder().id(dto.getMainCategoryId()).build());
        }
        if(dto.getSubCategoryId() != null) {
            this.setSubCategory(SubCategory.builder().id(dto.getSubCategoryId()).build());
        }
        if(dto.getThirdCategoryId() != null) {
            this.setThirdCategory(ThirdCategory.builder().id(dto.getThirdCategoryId()).build());
        }
        if(dto.getHistorySerialList() != null) {
            this.setHistorySerialList(dto.getHistorySerialList());
        }
    }

    //날짜 체크
    public void CheckLocalDate(LocalDate start, LocalDate end) {
        if(!start.isBefore(end)) {
            throw new DateException();
        }
    }

}
