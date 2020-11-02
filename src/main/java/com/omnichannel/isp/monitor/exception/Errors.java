package com.omnichannel.isp.monitor.exception;

public enum Errors {
	
    IO_EXCEPTION("IMS-001", "IOEXception while generating Connection Details file"),
    FILE_NOT_FOUND("IMS-002", "FileNotFound while generating Connection Details file");


    private final String errorCode;
    private final String errorMessage;

    Errors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }
    
}
