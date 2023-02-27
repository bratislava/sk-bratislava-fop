package sk.bratislava.fop.dto;

import sk.bratislava.fop.constants.Errors;

public class ErrorDto {
    public ErrorDto(Exception ex) {
        message = ex.getMessage();
        errorName = Errors.EXCEPTION;
    }

    private String message;
    private String errorName;

    public String getMessage() {
        return message;
    }

    public String getErrorName() {
        return errorName;
    }
}
