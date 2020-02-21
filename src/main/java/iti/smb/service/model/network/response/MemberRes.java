package iti.smb.service.model.network.response;

import iti.smb.service.model.enumclass.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRes {

    private Long id;

    private String name;

    private JobPosition jobPosition;

}
