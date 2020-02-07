package iti.smb.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Serial {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serialNumber;

    private Long service_code;

    @ColumnDefault("0") // 삭제여부 0 = false
    private boolean deleted;

}