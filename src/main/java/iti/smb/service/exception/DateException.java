package iti.smb.service.exception;

public class DateException extends RuntimeException {

    private static final String MESSAGE = "시작일은 종료일보다 항상 먼저여야 합니다.";

    public DateException(){
        super(MESSAGE);
    }

}
