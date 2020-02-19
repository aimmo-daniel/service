package iti.smb.service.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 제품 입니다.";

    public ProductNotFoundException(){
        super(MESSAGE);
    }

}
