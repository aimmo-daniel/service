package iti.smb.service.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"history", "serial"})
public class HistorySerial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // HistorySerial N : 1 History
    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    // HistorySerial N : 1 Serial
    @ManyToOne
    @JoinColumn(name = "serial_id")
    private Serial serial;

}
