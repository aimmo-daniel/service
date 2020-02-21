package iti.smb.service.service;

import iti.smb.service.exception.HospitalNotFoundException;
import iti.smb.service.exception.SerialNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Device;
import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.dto.ServiceHistoryDto;
import iti.smb.service.model.network.request.DeviceReq;
import iti.smb.service.model.network.response.DeviceRes;
import iti.smb.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService implements CrudInterface<DeviceReq, DeviceRes, String> {

    private final DeviceRepository deviceRepository;
    private final HospitalRepository hospitalRepository;
    private final ProductRepository productRepository;
    private final HistoryRepository historyRepository;
    private final HistoryDeviceRepository historyDeviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HospitalRepository hospitalRepository,
                         ProductRepository productRepository, HistoryRepository historyRepository,
                         HistoryDeviceRepository historyDeviceRepository) {
        this.deviceRepository = deviceRepository;
        this.hospitalRepository = hospitalRepository;
        this.productRepository = productRepository;
        this.historyRepository = historyRepository;
        this.historyDeviceRepository = historyDeviceRepository;
    }

    @Override
    public Header<DeviceRes> create(DeviceReq req) {
        Device device = Device.builder()
                .serialNumber(req.getSerialNumber())
                .hospital(hospitalRepository.getOne(req.getHospitalId()))
                .product(productRepository.getOne(req.getProductId()))
                .build();

        Device newDevice = deviceRepository.save(device);

        return Header.OK(response(newDevice));
    }

    @Override
    public Header<List<DeviceRes>> list() {
        List<Device> deviceList = deviceRepository.findByDeletedFalse();
        List<DeviceRes> deviceResList = new ArrayList<>();

        for(Device serial : deviceList) {
            deviceResList.add(response(serial));
        }

        return Header.OK(deviceResList);
    }

    @Override
    public Header<DeviceRes> read(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(serial -> Header.OK(response(serial)))
                .orElseThrow(SerialNotFoundException::new);
    }

    @Override
    public Header<DeviceRes> update(DeviceReq req) {

        return deviceRepository.findBySerialNumber(req.getSerialNumber())
                .map(device -> {
                    device.setHospital(hospitalRepository.getOne(req.getHospitalId()));

                    deviceRepository.save(device);
                    return Header.OK(response(device));
                })
                .orElseThrow(SerialNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return deviceRepository.findById(id)
                .map(device -> {
                    device.setDeleted(true);
                    deviceRepository.save(device);

                    return Header.DELETE();
                })
                .orElseThrow(HospitalNotFoundException::new);
    }

    private DeviceRes response(Device device) {
        List<HistoryDevice> historyDeviceList = historyDeviceRepository.findByDeviceId(device.getId());
        List<ServiceHistoryDto> serviceHistoryDtoList = new ArrayList<>();

        for (HistoryDevice history : historyDeviceList) {
            ServiceHistoryDto dto = new ServiceHistoryDto();
            if(!StringUtils.isEmpty(history.getHistory().getId())) dto.setHistoryId(history.getHistory().getId());
            if(!StringUtils.isEmpty(history.getHistory().getReceiveDate())) dto.setReceiveDate(history.getHistory().getReceiveDate());
            if(!StringUtils.isEmpty(history.getHistory().getEndDate())) dto.setEndDate(history.getHistory().getEndDate());
            if(!StringUtils.isEmpty(history.getHistory().getReceiveMember())) dto.setReceiveMember(history.getHistory().getReceiveMember().getName());
            if(!StringUtils.isEmpty(history.getHistory().getWorkMember())) dto.setWorkMember(history.getHistory().getWorkMember().getName());
            if(!StringUtils.isEmpty(history.getHistory().getCategory())) dto.setCategory(history.getHistory().getCategory().getName());
            if(!StringUtils.isEmpty(history.getHistory().getReception())) dto.setReception(history.getHistory().getReception());
            if(!StringUtils.isEmpty(history.getHistory().getAction())) dto.setAction(history.getHistory().getAction());
            if(!StringUtils.isEmpty(history.getHistory().getRemarks())) dto.setRemarks(history.getHistory().getRemarks());
            if(!StringUtils.isEmpty(history.getHistory().getStatus())) dto.setStatus(history.getHistory().getStatus());

            serviceHistoryDtoList.add(dto);
        }

        DeviceRes response = DeviceRes.builder()
                .id(device.getId())
                .serialNumber(device.getSerialNumber())
                .hospitalCode(device.getHospital().getCode())
                .hospital(device.getHospital().getName())
                .product(device.getProduct().getName())
                .serviceHistory(serviceHistoryDtoList)
                .build();

        return response;
    }

}
