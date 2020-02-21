package iti.smb.service.model.network.request;

import iti.smb.service.model.enumclass.ServiceStatus;
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

    // 단말기 시리얼넘버 목록
    private List<String> serialNumberList;

    private Boolean deleted=false;

}
