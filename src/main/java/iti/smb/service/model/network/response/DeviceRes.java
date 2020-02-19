package iti.smb.service.model.network.response;

import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.entity.Hospital;
import iti.smb.service.model.entity.Product;
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
public class DeviceRes {

    private Long id;

    private String serialNumber;

    private Hospital hospital;

    private Product product;

    private List<HistoryDeviceDto> historyDeviceList;

}