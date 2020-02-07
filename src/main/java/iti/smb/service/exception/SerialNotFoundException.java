package iti.smb.service.exception;

public class SerialNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 시리얼번호 입니다.";

    public SerialNotFoundException(){
        super(MESSAGE);
    }

}
