package com.omnichannel.isp.monitor.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;
import com.omnichannel.isp.monitor.model.InternetConnectionState;
import com.omnichannel.isp.monitor.state.InternetConnectedState;
import com.omnichannel.isp.monitor.state.InternetDisconnectedState;
import com.omnichannel.isp.monitor.state.InternetStateContext;

import lombok.extern.slf4j.Slf4j;

/**
 * This service checks Internet connection and publish data in Excel
 * @author JB25TV
 *
 */

@Slf4j
@Service
public class InternetConnectionService {
	
	
	@Autowired
	InternetStateContext internetStateContext;
	
	@Autowired
	InternetConnectedState internetConnectedState;
	
	@Autowired
	InternetDisconnectedState internetDisconnectedState;
	
	@Autowired
	ExcelService excelService;
	
	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	
	/**
	 * This method is responsible for monitoring Internet Connection
	 */
	public void monitorInternetConnection() {
		
		String url = "https://8.8.8.8";
		
		if(isInternetConnected(url)) {
			internetStateContext.setInternetState(internetConnectedState);			
		}else {
			internetStateContext.setInternetState(internetDisconnectedState);
		}
		
		internetStateContext.doAction();
		
	}
	
	/**
	 * This method triggers the url to verify if internet connection is working
	 * 
	 * @param url url which which test needs to be conducted
	 * @return
	 */
	public boolean isInternetConnected(String url) {
		
		try {
			
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			conn.connect();
			log.debug("Internet connection established");
			return true;

		} catch (final IllegalArgumentException e) {
			log.error("Malformed URL");
		    throw e;
		} catch (final IOException e) {
		    log.info("Service " + url + " unavailable, oh no!");
		    return false;
		}

	}

	/**
	 * This method publish internet connection failures in Excel
	 */

	public void publishConnectionDetails() {
 
		List<InternetConnectionState> internetConnectionState = internetStateCache.getInternetConnectionDetails();
		internetConnectionState.forEach(state -> {
			System.out.println(state.getInternetConnectionStatus() + " : " + state.getStartDateTime() + " : " + state.getEndDateTime());
		});
		
		excelService.publishConnectionDataInExcel();
		
	}




}
