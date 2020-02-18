package iti.smb.service.model.network.response;

import iti.smb.service.model.entity.*;
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

    private MainCategory mainCategory;

    private SubCategory subCategory;

    private ThirdCategory thirdCategory;

    private ServiceStatus status;

}
