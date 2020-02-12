package iti.smb.service.exception;

public class HospitalNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 병원 입니다.";

    public HospitalNotFoundException(){
        super(MESSAGE);
    }

}
