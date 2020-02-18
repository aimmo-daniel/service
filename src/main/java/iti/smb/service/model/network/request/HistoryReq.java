package iti.smb.service.model.network.request;

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

    private Long mainCategoryId;

    private Long subCategoryId;

    private Long thirdCategoryId;

    private ServiceStatus status;

    private Long deleted;

}
