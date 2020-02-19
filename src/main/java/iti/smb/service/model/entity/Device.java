package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "device")
@ToString(exclude = {"historyDeviceList"})
public class Device {

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", nullable = false, updatable = false)
    private Long id;

    // 시리얼넘버
    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    // Device N : 1 Hospital
    // 병원 정보
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // Device N : 1 Product
    // 제품 정보
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Device 1 : N HistoryDevice
    @JsonIgnoreProperties(value = {"history", "device"}, ignoreUnknown = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    private List<HistoryDevice> historyDeviceList = new ArrayList<>();

    public void addHistoryDevice(HistoryDevice historyDevice) {
        this.historyDeviceList.add(historyDevice);
    }

}