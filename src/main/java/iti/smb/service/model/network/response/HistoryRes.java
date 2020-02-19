package iti.smb.service.model.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import iti.smb.service.model.entity.Category;
import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.entity.Hospital;
import iti.smb.service.model.entity.Member;
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
@JsonInclude(JsonInclude.Include.NON_NULL) //값이 null 인 것은 숨김
public class HistoryRes {

    private Long id;

    private LocalDateTime receiveDate;

    private LocalDateTime endDate;

    private Member receiveMember;

    private Member workMember;

    private String reception;

    private String cause;

    private String action;

    private String remarks;

    private Hospital hospital;

    private Category category;

    private ServiceStatus status;

    private List<HistoryDeviceDto> historyDeviceList;

}
