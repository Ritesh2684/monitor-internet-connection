package com.omnichannel.isp.monitor.state;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;
import com.omnichannel.isp.monitor.model.InternetConnectionState;
import com.omnichannel.isp.monitor.model.InternetConnectionStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * This class represents disconnected state of Internet
 * @author JB25TV
 *
 */

@Slf4j
@Component
public class InternetDisconnectedState implements InternetState {
	
	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	/**
	 * This method performs actions for disconnected state of Internet
	 * 
	 * @return state of the internet connection
	 */
	@SuppressWarnings("static-access")
	public boolean doAction() {		
		
		log.debug("Internet is Disconnected");
		
		if(internetStateCache.isPreviousStateConnected()) {
			
			InternetConnectionState internetConnectionState = createInternetConnectionState();
			internetStateCache.save(internetConnectionState);
			internetStateCache.setPreviousStateConnected(false);
		}
		
		return internetStateCache.isPreviousStateConnected();
		
	}
	
	/**
	 * This method creates the object of Internet Connection
	 * @return internetConnectionState Object
	 */
	private InternetConnectionState createInternetConnectionState() {
		InternetConnectionState internetConnectionState = new InternetConnectionState();
		internetConnectionState.setInternetConnectionStatus(InternetConnectionStatus.DISCONNECTED);
		internetConnectionState.setStartDateTime(LocalDateTime.now());
		return internetConnectionState;
	}

}
