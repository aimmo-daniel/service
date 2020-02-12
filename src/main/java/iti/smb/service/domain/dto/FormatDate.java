package iti.smb.service.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Data
public class FormatDate {

    private Integer yearOfDate;
    private Integer monthOfDate;
    private Integer dayOfDate;

    private FormatDate(LocalDate localDate) {
        this.yearOfDate = localDate.getYear();
        this.monthOfDate = localDate.getMonthValue();
        this.dayOfDate = localDate.getDayOfMonth();
    }

    public static FormatDate of(LocalDate localDate) {
        return new FormatDate(localDate);
    }

}
