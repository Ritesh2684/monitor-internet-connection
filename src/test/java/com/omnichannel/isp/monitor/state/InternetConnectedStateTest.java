package com.omnichannel.isp.monitor.state;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;
import com.omnichannel.isp.monitor.model.InternetConnectionState;
import com.omnichannel.isp.monitor.model.InternetConnectionStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetConnectedStateTest {
	
	@Autowired
	InternetConnectedState internetConnectedState;	
	
	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	@SuppressWarnings("static-access")
	@Test
	public void doActionTest() {
		
		
		internetStateCache.save(createInternetConnectionState());
		internetStateCache.setPreviousStateConnected(false);

		Assert.assertEquals(true, internetConnectedState.doAction());
		
	}
	
	private InternetConnectionState createInternetConnectionState() {
		InternetConnectionState internetConnectionState = new InternetConnectionState();
		internetConnectionState.setInternetConnectionStatus(InternetConnectionStatus.DISCONNECTED);
		internetConnectionState.setStartDateTime(LocalDateTime.now());
		return internetConnectionState;
	}
	

}
