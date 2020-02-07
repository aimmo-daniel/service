package iti.smb.service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class HistoryDto {

    private Date addDate;

    private Date endDate;

    private Long receiveMember;

    private Long workMember;

    private String reception;

    private String cause;

    private String action;

    private String remarks;

    private String hospital_code;

    private Long mainCategoryId;

    private Long subCategoryId;

    private Long thirdCategoryId;

    private Integer status;

}
