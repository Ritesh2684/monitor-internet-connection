package com.omnichannel.isp.monitor.exception;

public class InternetMonitorServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String COLON = ":";

	public InternetMonitorServiceException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public InternetMonitorServiceException(String errorCode, String errorMessage) {
		super(errorCode + COLON + errorMessage);
	}

}