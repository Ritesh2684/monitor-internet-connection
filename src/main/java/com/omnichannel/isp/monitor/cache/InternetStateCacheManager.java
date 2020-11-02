package com.omnichannel.isp.monitor.cache;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.omnichannel.isp.monitor.model.InternetConnectionState;

/**
 * This class caches the Interent state for next reference
 * @author Ritesh Agarwal
 *
 */
@Component
public class InternetStateCacheManager {
	
	private static InternetStateCacheManager internetStateCache;
	
	private static List<InternetConnectionState> internetStateStore;
	
	// This method provides the previous state of internet connection
	private static boolean isPreviousStateConnected = true;
	
	/**
	 * This method creates the singleton object of Cache
	 * @return internetStateManager {@link InternetStateCacheManager}
	 */
	public static InternetStateCacheManager getCache() {
		if(null == internetStateCache) {
			internetStateCache = new InternetStateCacheManager();
			internetStateStore = new ArrayList<>();
		}
		
		return internetStateCache;
	}
	
	/**
	 * This method saves the instance of internetConnectionState {@link InternetConnectionState}
	 * @param internetConnectionState {@link InternetConnectionState}
	 */
	public void save(InternetConnectionState internetConnectionState) {
		internetStateStore.add(internetConnectionState);		
	}
	
	/**
	 * This method update the end time when internet is connected again
	 * @param endDateTime end time of unavailability
	 */
	public void update(LocalDateTime endDateTime) {
		if(null != internetStateStore && !internetStateStore.isEmpty()) {
			internetStateStore.get(internetStateStore.size() - 1).setEndDateTime(endDateTime);
		}		
	}	
	
	/**
	 * This method provides the store with all the unavailability instances 
	 * @return list of unavailable instances
	 */
	public List<InternetConnectionState> getInternetConnectionDetails() {
		return internetStateStore;
	}

	
	public static boolean isPreviousStateConnected() {
		return isPreviousStateConnected;
	}


	public static void setPreviousStateConnected(boolean isPreviousStateConnected) {
		InternetStateCacheManager.isPreviousStateConnected = isPreviousStateConnected;
	}
	

}

