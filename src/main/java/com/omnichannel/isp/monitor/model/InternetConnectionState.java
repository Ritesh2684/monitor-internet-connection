package com.omnichannel.isp.monitor.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternetConnectionState {
	
	private InternetConnectionStatus internetConnectionStatus;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
}
