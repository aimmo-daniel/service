package iti.smb.service.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "serial")
public class Serial {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serial_id", nullable = false, updatable = false)
    private Long id;

    // 시리얼넘버
    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    // 병원 정보
    @JsonIgnoreProperties({"id", "name", "solution", "region", "link", "homepage"})
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serial")
    private List<HistorySerial> historySerialList;

}