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
public class Member {

    // 시퀀스
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 멤버 이름
    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @ColumnDefault("0") // 삭제여부 0 = false
    private boolean deleted;

}
