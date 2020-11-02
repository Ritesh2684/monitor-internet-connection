package com.omnichannel.isp.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.omnichannel.isp.monitor.service.InternetConnectionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Autowired
	InternetConnectionService internetConnectionService;
	
	@Scheduled(fixedDelayString = "${monitor.in.milliseconds}")
	public void monitorInternetConnection() {
		internetConnectionService.monitorInternetConnection();
		
	}
	
	@Scheduled(initialDelay = 60000 , fixedDelayString = "${publish.in.milliseconds}")
	public void publishConnectionDetails() {
		log.debug("Publishing Connection Details");
		internetConnectionService.publishConnectionDetails();
		
	}

}
