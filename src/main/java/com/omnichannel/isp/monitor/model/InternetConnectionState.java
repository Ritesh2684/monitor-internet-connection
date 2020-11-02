package com.omnichannel.isp.monitor.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InternetConnectionState {
	
	private InternetConnectionStatus internetConnectionStatus;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
}
