package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "sub_category")
public class SubCategory {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id", nullable = false, updatable = false)
    private Long id;

    // 서브 카테고리명
    @Column(name = "name", nullable = false)
    private String name;

    // SubCategory N : 1 MainCategory
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

}
