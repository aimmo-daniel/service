package iti.smb.service.controller.dto;

import iti.smb.service.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class HistoryDto {

    private LocalDate receiveDate;

    private LocalDate endDate;

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

    private List<HistorySerial> historySerialList = new ArrayList<HistorySerial>();

}
