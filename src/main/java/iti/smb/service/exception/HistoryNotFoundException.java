package iti.smb.service.exception;

public class HistoryNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 히스토리 입니다.";

    public HistoryNotFoundException(){
        super(MESSAGE);
    }

}
