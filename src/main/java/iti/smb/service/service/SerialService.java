package iti.smb.service.service;

import iti.smb.service.model.entity.History;
import iti.smb.service.model.entity.Serial;
import iti.smb.service.exception.HistoryNotFoundException;
import iti.smb.service.exception.SerialNotFoundException;
import iti.smb.service.repository.HistoryRepository;
import iti.smb.service.repository.SerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerialService {

    private SerialRepository serialRepository;
    private HistoryRepository historyRepository;

    @Autowired
    public SerialService(SerialRepository serialRepository, HistoryRepository historyRepository) {
        this.serialRepository = serialRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void addSerial(Long service_code, List<Serial> serials) {
        History history = historyRepository.findById(service_code).orElseThrow(HistoryNotFoundException::new);

        serialRepository.saveAll(serials);
    }

    @Transactional
    public void modifySerial(Long service_code, Long serialId, String serialNumber) {
        Serial serial = serialRepository.findById(serialId).orElseThrow(SerialNotFoundException::new);

     /*   if(serial.getHistory().getId() == service_code) {
            serial.setSerialNumber(serialNumber);
        } else {
            throw new RuntimeException();
        }*/

        serialRepository.save(serial);
    }

    @Transactional
    public void delSerial(Long service_code, Long serialId) {
        Serial serial = serialRepository.findById(serialId).orElseThrow(SerialNotFoundException::new);

       /* if(serial.getHistory().getId() == service_code) {
            serial.setDeleted(true);
        } else {
            throw new RuntimeException();
        }*/

        serialRepository.save(serial);
    }
}
