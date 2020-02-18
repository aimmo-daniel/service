package iti.smb.service.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalRes {

    private Long id;

    private String code;

    private String name;

    private String region;

    private Boolean solution;

    private Boolean link;

    private String homepage;

}
