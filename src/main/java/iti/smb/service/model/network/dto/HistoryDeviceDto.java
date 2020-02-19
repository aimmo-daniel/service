package iti.smb.service.model.network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDeviceDto {

    private Long historyId;

    private Long deviceId;

}
