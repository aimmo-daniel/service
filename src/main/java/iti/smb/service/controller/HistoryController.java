package iti.smb.service.controller;

import iti.smb.service.controller.dto.HistoryDto;
import iti.smb.service.domain.History;
import iti.smb.service.service.HistoryService;
import iti.smb.service.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/history")
@RestController
public class HistoryController {

    private HistoryService historyService;
    private SerialService serialService;

    @Autowired
    public HistoryController(HistoryService historyService, SerialService serialService) {
        this.historyService = historyService;
        this.serialService = serialService;
    }

    // 서비스 목록 조회
    @GetMapping
    public List<History> historyList() {
        return historyService.list();
    }

    // 서비스 상세 보기
    @GetMapping("/{id}")
    public History historyInfo(@PathVariable Long id) {
        return historyService.detail(id);
    }

    // 서비스 히스토리 등록
    @PostMapping
    public void addHistory(@RequestBody HistoryDto historyDto) {
        historyService.addHistory(historyDto);
    }

    // 서비스 히스토리 수정
    @PatchMapping("/{id}")
    public void modifyHistory(@PathVariable Long id, HistoryDto historyDto) {
        historyService.modifyHistory(id, historyDto);
    }

    // 서비스 히스토리 삭제
    @DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.delHistory(id);
    }

}
