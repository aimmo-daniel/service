package iti.smb.service.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "deleted = false") //deleted false 인것만 노출
@Table(name = "member")
public class Member {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long id;

    // 멤버 이름
    @Column(name = "member_name", nullable = false)
    private String name;

    // 삭제여부
    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

}
