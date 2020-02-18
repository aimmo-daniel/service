package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import iti.smb.service.model.enumclass.ServiceStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "history")
@ToString(exclude = {"historySerialList"})
public class History {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false, updatable = false)
    private Long id;

    // 접수일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "receive_date")
    private LocalDateTime receiveDate;

    // 종료일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    // History N : 1 Member
    // 접수자
    @ManyToOne
    @JoinColumn(name = "receive_member_id")
    private Member receiveMember;

    // History N : 1 Member
    // 작업자
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

    // History N : 1 Hospital
    // 병원
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // History N : 1 MainCategory
    // 대분류
    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    // History N : 1 SubCategory
    // 중분류
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    // History N : 1 ThirdCategory
    // 소분류
    @ManyToOne
    @JoinColumn(name = "third_category_id")
    private ThirdCategory thirdCategory;

    // 진행상태 (default = READY)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "varchar(255) default '대기중'")
    private ServiceStatus status = ServiceStatus.대기중;

    // History 1 : N HistorySerial
    // 단말기 시리얼번호 목록
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "history")
    private List<HistorySerial> historySerialList;

    // 삭제 여부 (DB 에서는 지워지지 않음)
    @JsonIgnore
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

}
