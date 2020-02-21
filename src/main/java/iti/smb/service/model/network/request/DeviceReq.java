package iti.smb.service.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceReq {

    private Long id;

    private String serialNumber;

    private Long hospitalId;

    private Long productId;

}
