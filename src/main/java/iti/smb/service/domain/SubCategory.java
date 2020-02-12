package iti.smb.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "sub_category")
public class SubCategory {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sub_category_id", nullable = false, updatable = false)
    private Long id;

    // 서브 카테고리명
    @Column(name = "sub_category_name", nullable = false)
    private String name;

    // FK 메인 카테고리 (N:1 단방향)
    @JsonIgnoreProperties({"main_category_name"})
    @ManyToOne
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

}
