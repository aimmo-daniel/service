package iti.smb.service.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    private MainCategory mainCategory;

    private SubCategory subCategory;

    private ThirdCategory thirdCategory;

    private ServiceStatus status;

}
