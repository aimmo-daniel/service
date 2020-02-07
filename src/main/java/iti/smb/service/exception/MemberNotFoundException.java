package iti.smb.service.exception;

public class MemberNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 직원입니다.";

    public MemberNotFoundException(){
        super(MESSAGE);
    }

}