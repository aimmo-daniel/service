package iti.smb.service.domain;

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
@Table(name = "main_category")
public class MainCategory {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "main_category_id", nullable = false, updatable = false)
    private Long id;

    // 메인 카테고리명
    @Column(name = "main_category_name", nullable = false)
    private String name;

}
