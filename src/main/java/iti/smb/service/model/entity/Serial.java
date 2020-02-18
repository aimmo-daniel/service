package iti.smb.service.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "serial")
@ToString(exclude = {"historySerialList"})
public class Serial {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_id", nullable = false, updatable = false)
    private Long id;

    // 시리얼넘버
    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    // Serial N : 1 Hospital
    // 병원 정보
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // Serial 1 : N HistorySerial
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serial")
    private List<HistorySerial> historySerialList;

}