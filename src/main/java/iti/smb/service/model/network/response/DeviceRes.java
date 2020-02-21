package iti.smb.service.model.network.response;

import iti.smb.service.model.network.dto.ServiceHistoryDto;
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

    private String hospitalCode;

    private String hospital;

    private String product;

    private List<ServiceHistoryDto> serviceHistory;

}