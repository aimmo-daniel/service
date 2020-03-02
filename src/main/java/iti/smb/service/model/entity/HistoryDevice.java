package iti.smb.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"history", "device"})
public class HistoryDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // HistoryDevice N : 1 History
    @JsonIgnoreProperties(value = {"action", "category", "cause", "endDate", "historyDeviceList", "hospital",
            "receiveData", "receiveMember", "reception", "remarks", "status", "workMember"}, ignoreUnknown = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    // HistoryDevice N : 1 Device
    @JsonIgnoreProperties(value = {"historyDeviceList", "hospital", "product", "serialNumber"}, ignoreUnknown = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public static HistoryDevice of(History history, Device device) {
        HistoryDevice historyDevice = new HistoryDevice();
        historyDevice.setHistory(history);
        historyDevice.setDevice(device);
        return historyDevice;
    }

}
