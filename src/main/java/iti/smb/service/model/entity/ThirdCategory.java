package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "third_category")
public class ThirdCategory {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "third_category_id", nullable = false, updatable = false)
    private Long id;

    // 서드 카테고리명
    @Column(name = "name", nullable = false)
    private String name;

    // ThirdCategory N : 1 SubCategory
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

}
