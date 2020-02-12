package iti.smb.service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class HospitalDto {

    private String code;

    private String name;

    private String region;

    private String homepage;

    private boolean solution;

    private boolean link;

    private boolean service;

}
