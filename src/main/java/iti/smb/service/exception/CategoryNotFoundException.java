package iti.smb.service.exception;

public class CategoryNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 카테고리입니다.";

    public CategoryNotFoundException(){
        super(MESSAGE);
    }

}
