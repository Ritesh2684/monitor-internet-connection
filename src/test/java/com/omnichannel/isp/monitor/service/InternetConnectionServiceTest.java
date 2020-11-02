package com.omnichannel.isp.monitor.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetConnectionServiceTest {
	
	@Autowired
	InternetConnectionService internetConnectionService;
	
	@Test
	public void isInternetConnectedTest() {
		
		String url = "https://8.8.8.8";
		Assert.assertEquals(true, internetConnectionService.isInternetConnected(url));	
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void isInternetConnectedExceptionTest() {
		
		String url = "https://";
		internetConnectionService.isInternetConnected(url);
		
	}
	
	

}
