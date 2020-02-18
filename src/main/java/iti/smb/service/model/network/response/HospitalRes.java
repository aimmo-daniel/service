package iti.smb.service.model.network.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //값이 null 인 것은 숨김
public class HospitalRes {

    private Long id;

    private String code;

    private String name;

    private String region;

    private Boolean solution;

    private Boolean link;

    private String homepage;

}
