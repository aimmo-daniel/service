package iti.smb.service.model.network.request;

import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.network.dto.HistoryDeviceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceReq {

    private Long id;

    private String serialNumber;

    private Long hospitalId;

    private Long productId;

    private List<HistoryDeviceDto> historyDeviceList;

}
