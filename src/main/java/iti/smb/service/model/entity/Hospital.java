package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "hospital")
public class Hospital {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false, updatable = false)
    private Long id;

    // 병원코드 Unique
    @Column(name = "code", length = 4, unique = true, nullable = false)
    private String code;

    // 병원명
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // 지역
    @Column(name = "region")
    private String region;

    // 솔루션
    @Column(name = "solution", columnDefinition = "boolean default false")
    private boolean solution;

    // 진료정보연동
    @Column(name = "link", columnDefinition = "boolean default false")
    private boolean link;

    // 홈페이지
    @Column(name = "homepage")
    private String homepage;

    // 서비스 종료 (true 면 목록에 표시하지 않음)
    @JsonIgnore
    @Column(name = "end_service", columnDefinition = "boolean default false")
    private boolean endService;

}
