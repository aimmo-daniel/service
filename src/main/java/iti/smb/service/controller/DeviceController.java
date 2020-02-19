package iti.smb.service.controller;

import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.request.DeviceReq;
import iti.smb.service.model.network.response.DeviceRes;
import iti.smb.service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/device")
@RestController
public class DeviceController implements CrudInterface<DeviceReq, DeviceRes, String> {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // 시리얼 정보 생성
    @Override
    @PostMapping
    public Header<DeviceRes> create(@RequestBody DeviceReq req) {
        return deviceService.create(req);
    }

    //
    @Override
    @GetMapping
    public Header<List<DeviceRes>> list() {
        return deviceService.list();
    }

    @Override
    @GetMapping("/{serialNumber}")
    public Header<DeviceRes> read(@PathVariable("serialNumber") String serialNumber) {
        return deviceService.read(serialNumber);
    }

    @Override
    @PatchMapping
    public Header<DeviceRes> update(@RequestBody DeviceReq req) {
        return deviceService.update(req);
    }

    @Override
    @DeleteMapping("/{id}")
    public Header delete(@PathVariable("id") Long id) {
        return deviceService.delete(id);
    }

}
