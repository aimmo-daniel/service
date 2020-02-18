package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iti.smb.service.model.enumclass.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "member")
public class Member {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long id;

    // 멤버 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 직급
    @Enumerated(EnumType.STRING)
    @Column(name = "job_position", nullable = false)
    private JobPosition jobPosition;

    // 삭제여부
    @JsonIgnore
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

}
