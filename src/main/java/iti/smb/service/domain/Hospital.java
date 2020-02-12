package iti.smb.service.domain;

import iti.smb.service.controller.dto.HospitalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Where(clause = "isService = true") //deleted false 인것만 노출
@Table(name = "hospital")
public class Hospital {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hospital_id", nullable = false, updatable = false)
    private Long id;

    // 병원코드 Unique
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    // 병원명
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // 솔루션
    @Column(name = "solution", columnDefinition = "boolean default false")
    private boolean solution;

    // 지역
    @Column(name = "region")
    private String region;

    // 진료정보연동
    @Column(name = "link", columnDefinition = "boolean default false")
    private boolean link;

    // 홈페이지
    @Column(name = "homepage")
    private String homepage;

    // 서비스 현황(false 면 목록에 표시하지않음)
    @Column(name = "isService", columnDefinition = "boolean default true")
    private boolean isService;

    public void set(HospitalDto dto) {
        if(!StringUtils.isEmpty(dto.getCode())) {
            this.setCode(dto.getCode());
        }
        if(!StringUtils.isEmpty(dto.getName())) {
            this.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getRegion())) {
            this.setRegion(dto.getRegion());
        }
        if(!StringUtils.isEmpty(dto.getHomepage())) {
            this.setHomepage(dto.getHomepage());
        }
        if(dto.isSolution()) {
            this.setSolution(true);
        }else {
            this.setSolution(false);
        }
        if(dto.isLink()) {
            this.setLink(true);
        }else {
            this.setSolution(false);
        }
        if(dto.isService()) {
            this.setService(true);
        }else {
            this.setService(false);
        }
    }

}
