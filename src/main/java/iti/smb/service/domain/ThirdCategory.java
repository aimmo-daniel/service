package iti.smb.service.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "third_category")
public class ThirdCategory {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "third_category_id", nullable = false, updatable = false)
    private Long id;

    // 서드 카테고리명
    @Column(name = "third_category_name", nullable = false)
    private String name;

    // FK 서브 카테고리 (N:1 단방향)
    @JsonIgnoreProperties({"name", "mainCategory"})
    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

}
