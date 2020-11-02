package com.omnichannel.isp.monitor.state;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;

import lombok.extern.slf4j.Slf4j;

/**
 * This class represents connected state of Internet
 * @author JB25TV
 *
 */
@Slf4j
@Component
public class InternetConnectedState implements InternetState {
	

	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	/**
	 * This method performs actions for connected state of Internet
	 * 
	 * @return state of the internet connection
	 */
	@SuppressWarnings("static-access")
	public boolean doAction() {		
		
		log.debug("Internet is Connected");
		
		if(!internetStateCache.isPreviousStateConnected()) {
			internetStateCache.update(LocalDateTime.now());
			internetStateCache.setPreviousStateConnected(true);			
		}
		
		return internetStateCache.isPreviousStateConnected();
	}

}
