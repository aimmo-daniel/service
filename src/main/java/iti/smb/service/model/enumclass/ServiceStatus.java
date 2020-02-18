package iti.smb.service.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceStatus {
    대기중, 처리중, 완료
}