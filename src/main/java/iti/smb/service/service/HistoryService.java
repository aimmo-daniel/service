package iti.smb.service.service;

import iti.smb.service.controller.dto.HistoryDto;
import iti.smb.service.domain.History;
import iti.smb.service.exception.HistoryNotFoundException;
import iti.smb.service.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> list() {
        return historyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public History detail(Long id) {
        return historyRepository.findById(id).orElseThrow(HistoryNotFoundException::new);
    }

    @Transactional
    public void addHistory(HistoryDto historyDto) {
        History history = new History();
        history.set(historyDto);

        historyRepository.save(history);
    }

    @Transactional
    public void modifyHistory(Long id, HistoryDto historyDto) {
        History history = historyRepository.findById(id).orElseThrow(HistoryNotFoundException::new);
        history.set(historyDto);

        historyRepository.save(history);
    }

    @Transactional
    public void delHistory(Long id) {
        History history = historyRepository.findById(id).orElseThrow(HistoryNotFoundException::new);
        history.setDeleted(true);

        historyRepository.save(history);
    }


}
