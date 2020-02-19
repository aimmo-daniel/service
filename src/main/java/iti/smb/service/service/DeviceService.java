package iti.smb.service.service;

import iti.smb.service.exception.SerialNotFoundException;
import iti.smb.service.interfaces.CrudInterface;
import iti.smb.service.model.entity.Device;
import iti.smb.service.model.entity.HistoryDevice;
import iti.smb.service.model.network.Header;
import iti.smb.service.model.network.dto.HistoryDeviceDto;
import iti.smb.service.model.network.request.DeviceReq;
import iti.smb.service.model.network.response.DeviceRes;
import iti.smb.service.repository.DeviceRepository;
import iti.smb.service.repository.HistoryRepository;
import iti.smb.service.repository.HospitalRepository;
import iti.smb.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService implements CrudInterface<DeviceReq, DeviceRes, String> {

    private final DeviceRepository deviceRepository;
    private final HospitalRepository hospitalRepository;
    private final ProductRepository productRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HospitalRepository hospitalRepository,
                         ProductRepository productRepository, HistoryRepository historyRepository) {
        this.deviceRepository = deviceRepository;
        this.hospitalRepository = hospitalRepository;
        this.productRepository = productRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public Header<DeviceRes> create(DeviceReq req) {
        // HistoryDevice 목록 객체 생성
        List<HistoryDevice> historyDeviceList = new ArrayList<>();

        // 요청으로 넘어온 HistoryDevice 목록이 있으면
        if(req.getHistoryDeviceList() != null) {
            // HistoryDeviceDto 목록 객체에 저장
            List<HistoryDeviceDto> historyDeviceDtoList = req.getHistoryDeviceList();

            // HistoryDeviceDto 의 historyId, deviceId로 엔티티를 조회하여 HistoryDevice 목록 객체에 저장
            for (int i = 0; i < historyDeviceDtoList.size(); i++) {
                HistoryDevice historyDevice = new HistoryDevice();
                historyDevice.setHistory(historyRepository.getOne(historyDeviceDtoList.get(i).getHistoryId()));
                historyDevice.setDevice(deviceRepository.getOne(historyDeviceDtoList.get(i).getDeviceId()));
                historyDeviceList.add(historyDevice);
            }
        }

        Device device = Device.builder()
                .serialNumber(req.getSerialNumber())
                .hospital(hospitalRepository.getOne(req.getHospitalId()))
                .product(productRepository.getOne(req.getProductId()))
                .build();

        // HistoryDevice 목록 객체에 값이 있으면
        if(historyDeviceList != null) {
            device.setHistoryDeviceList(historyDeviceList);
        }

        System.out.println("---------------------------");
        System.out.println(device);
        System.out.println("---------------------------");

        Device newDevice = deviceRepository.save(device);

        return Header.OK(response(newDevice));
    }

    @Override
    public Header<List<DeviceRes>> list() {
        List<Device> deviceList = deviceRepository.findAll();
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
        List<HistoryDeviceDto> historyDeviceDtoList = req.getHistoryDeviceList();
        List<HistoryDevice> historyDeviceList = new ArrayList<>();

        for(int i=0; i<historyDeviceDtoList.size(); i++) {
            HistoryDevice historyDevice = new HistoryDevice();
            historyDevice.setHistory(historyRepository.getOne(historyDeviceDtoList.get(i).getHistoryId()));
            historyDevice.setDevice(deviceRepository.getOne(historyDeviceDtoList.get(i).getDeviceId()));
            historyDeviceList.add(historyDevice);
        }

        return deviceRepository.findBySerialNumber(req.getSerialNumber())
                .map(device -> {
                    device.setHospital(hospitalRepository.getOne(req.getHospitalId()));
                    device.setHistoryDeviceList(historyDeviceList);

                    deviceRepository.save(device);
                    return Header.OK(response(device));
                })
                .orElseThrow(SerialNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private DeviceRes response(Device device) {
        List<HistoryDevice> historyDeviceList = device.getHistoryDeviceList();
        List<HistoryDeviceDto> historyDeviceDtoList = new ArrayList<>();

        for(int i=0; i<historyDeviceList.size(); i++) {
            HistoryDeviceDto historyDeviceDto = new HistoryDeviceDto();
            historyDeviceDto.setHistoryId(historyDeviceList.get(i).getHistory().getId());
            historyDeviceDto.setDeviceId(historyDeviceList.get(i).getDevice().getId());
            historyDeviceDtoList.add(historyDeviceDto);
        }

        DeviceRes response = DeviceRes.builder()
                .id(device.getId())
                .serialNumber(device.getSerialNumber())
                .hospital(device.getHospital())
                .product(device.getProduct())
                .historyDeviceList(historyDeviceDtoList)
                .build();

        return response;
    }

}
