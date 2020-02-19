package iti.smb.service.model.network.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.enumclass.ServiceStatus;
import iti.smb.service.model.network.dto.HistoryDeviceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryReq {

    private Long id;

    private LocalDateTime receiveDate;

    private LocalDateTime endDate;

    private Long receiveMemberId;

    private Long workMemberId;

    private String reception;

    private String cause;

    private String action;

    private String remarks;

    private Long hospitalId;

    private Long categoryId;

    private ServiceStatus status = ServiceStatus.대기중;

    private List<HistoryDeviceDto> historyDeviceList;

    private Boolean deleted=false;

}
