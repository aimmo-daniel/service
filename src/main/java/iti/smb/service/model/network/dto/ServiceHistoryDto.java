package iti.smb.service.model.network.dto;

import iti.smb.service.model.entity.Category;
import iti.smb.service.model.entity.Member;
import iti.smb.service.model.enumclass.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceHistoryDto {

    private Long historyId;

    private LocalDateTime receiveDate;

    private LocalDateTime endDate;

    private String receiveMember;

    private String workMember;

    private String category;

    private String reception;

    private String cause;

    private String action;

    private String remarks;

    private ServiceStatus status;

}
