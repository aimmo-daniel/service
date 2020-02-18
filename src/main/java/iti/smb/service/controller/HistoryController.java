package iti.smb.service.controller;

import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.HistoryReq;
import iti.smb.service.model.network.response.HistoryRes;
import iti.smb.service.service.HistoryService;
import iti.smb.service.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/history")
@RestController
public class HistoryController implements CrudInterface<HistoryReq, HistoryRes, Long> {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService, SerialService serialService) {
        this.historyService = historyService;
    }

    // 신규 서비스 히스토리 추가
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Header<HistoryRes> create(@RequestBody HistoryReq request) {
        return historyService.create(request);
    }

    // 서비스 히스토리 목록 조회
    @Override
    @GetMapping
    public Header<List<HistoryRes>> list() {
        return historyService.list();
    }

    // 서비스 히스토리 상세 조회
    @Override
    @GetMapping("/{id}")
    public Header<HistoryRes> read(@PathVariable("id") Long id) {
        return historyService.read(id);
    }

    // 서비스 히스토리 수정
    @Override
    @PatchMapping
    public Header<HistoryRes> update(@RequestBody HistoryReq request) {
        return historyService.update(request);
    }

    // 서비스 히스토리 삭제
    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return historyService.delete(id);
    }

}
