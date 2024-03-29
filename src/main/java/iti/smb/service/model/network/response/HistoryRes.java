package iti.smb.service.model.network.response;

import iti.smb.service.model.enumclass.ServiceStatus;
import iti.smb.service.model.network.dto.SerialDto;
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
public class HistoryRes {

    private Long id;

    private LocalDateTime receiveDate;

    private LocalDateTime endDate;

    private String receiveMember;

    private String workMember;

    private String reception;

    private String cause;

    private String action;

    private String remarks;

    private String hospitalCode;

    private String hospital;

    private String category;

    private ServiceStatus status;

    private List<SerialDto> serialList;

}
