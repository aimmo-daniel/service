package iti.smb.service.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import iti.smb.service.model.enumclass.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //값이 null 인 것은 숨김
public class MemberRes {

    private Long id;

    private String name;

    private JobPosition jobPosition;

}
