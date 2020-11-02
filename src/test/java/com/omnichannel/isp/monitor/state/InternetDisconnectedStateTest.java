package com.omnichannel.isp.monitor.state;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetDisconnectedStateTest {
	
	@Autowired
	InternetDisconnectedState internetDisconnectedState;	
	
	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	@SuppressWarnings("static-access")
	@Test
	public void doActionTest() {
		
		internetStateCache.setPreviousStateConnected(true);
		Assert.assertEquals(false, internetDisconnectedState.doAction());
		Assert.assertEquals(true,internetStateCache.getInternetConnectionDetails().size()>0);
		
	}


}
