package iti.smb.service.domain;

import iti.smb.service.controller.dto.HistoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "deleted = false") //deleted false 인것만 노출
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date addDate;

    @Temporal(TemporalType.DATE)
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

    @ColumnDefault("0")
    private int status;

    @OneToMany
    @JoinColumn(name = "service_code")
    private List<Serial> serialList;

    @ColumnDefault("0") // 삭제여부 0 = false
    private boolean deleted;

    public void set(HistoryDto historyDto) {
        if(historyDto.getAddDate() != null) {
            this.setAddDate(historyDto.getAddDate());
        }
        if(historyDto.getEndDate() != null) {
            this.setEndDate(historyDto.getEndDate());
        }
        if(historyDto.getReceiveMember() != null){
            this.setReceiveMember(historyDto.getReceiveMember());
        }
        if(historyDto.getWorkMember() != null){
            this.setWorkMember(historyDto.getWorkMember());
        }
        if(!StringUtils.isEmpty(historyDto.getReception())){
            this.setReception(historyDto.getReception());
        }
        if(!StringUtils.isEmpty(historyDto.getCause())){
            this.setCause(historyDto.getCause());
        }
        if(!StringUtils.isEmpty(historyDto.getAction())){
            this.setAction(historyDto.getAction());
        }
        if(!StringUtils.isEmpty(historyDto.getRemarks())){
            this.setRemarks(historyDto.getRemarks());
        }
        if(!StringUtils.isEmpty(historyDto.getHospital_code())){
            this.setHospital_code(historyDto.getHospital_code());
        }
        if(historyDto.getMainCategoryId() != null){
            this.setMainCategoryId(historyDto.getMainCategoryId());
        }
        if(historyDto.getSubCategoryId() != null){
            this.setSubCategoryId(historyDto.getSubCategoryId());
        }
        if(historyDto.getThirdCategoryId() != null){
            this.setThirdCategoryId(historyDto.getThirdCategoryId());
        }
        if(historyDto.getStatus() != null) {
            this.setStatus(historyDto.getStatus());
        }
    }

}
