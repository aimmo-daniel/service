package iti.smb.service.controller;

import iti.smb.service.controller.dto.HistoryDto;
import iti.smb.service.domain.History;
import iti.smb.service.domain.Serial;
import iti.smb.service.service.HistoryService;
import iti.smb.service.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<History> list() {
        return historyService.list();
    }

    // 서비스 상세 보기
    @GetMapping("/{id}")
    public History detail(@PathVariable("id") Long id) {
        return historyService.detail(id);
    }

    // 서비스 히스토리 등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addHistory(@RequestBody HistoryDto historyDto) {
        historyService.addHistory(historyDto);
    }

    // 서비스 히스토리 수정
    @PatchMapping("/{id}")
    public void modifyHistory(@PathVariable Long id, @RequestBody HistoryDto historyDto) {
        historyService.modifyHistory(id, historyDto);
    }

    // 서비스 히스토리 삭제
    @DeleteMapping("/{id}")
    public void delHistory(@PathVariable("id") Long id) {
        historyService.delHistory(id);
    }

    // 시리얼넘버 추가
    @PostMapping("/{id}/serial")
    public void addSerial(@PathVariable("id") Long service_code, @RequestBody List<Serial> serials) {
        serialService.addSerial(service_code, serials);
    }

    // 시리얼넘버 수정
    @PatchMapping("/{id}/serial")
    public void modifySerial(@PathVariable("id") Long service_code, @RequestBody Serial resource) {
        serialService.modifySerial(service_code, resource.getId(), resource.getSerialNumber());
    }

    // 시리얼넘버 삭제
    @DeleteMapping("/{id}/serial/{serialId}")
    public void delSerial(@PathVariable("id") Long service_code, @PathVariable("serialId") Long serialId) {
        serialService.delSerial(service_code, serialId);
    }


}
