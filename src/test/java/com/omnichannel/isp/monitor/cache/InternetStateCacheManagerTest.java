package com.omnichannel.isp.monitor.cache;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.omnichannel.isp.monitor.model.InternetConnectionState;
import com.omnichannel.isp.monitor.model.InternetConnectionStatus;

public class InternetStateCacheManagerTest {
	
	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	@Test
	public void saveTest() {
		
		internetStateCache.save(createInternetConnectionState());
		Assert.assertEquals(true,internetStateCache.getInternetConnectionDetails().size()>0);
		
	}
	
	@Test
	public void updateTest() {
		
		internetStateCache.save(createInternetConnectionState());
		internetStateCache.update(LocalDateTime.now());		
		
		Assert.assertNotNull(internetStateCache.getInternetConnectionDetails().get(1).getEndDateTime());
		
	}
	
	
	private InternetConnectionState createInternetConnectionState() {
		InternetConnectionState internetConnectionState = new InternetConnectionState();
		internetConnectionState.setInternetConnectionStatus(InternetConnectionStatus.DISCONNECTED);
		internetConnectionState.setStartDateTime(LocalDateTime.now());
		return internetConnectionState;
	}

}
