package com.omnichannel.isp.monitor.state;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * This is a class corresponding to State Design pattern to facilitate the invocation of required actions for each state
 * @author JB25TV
 *
 */

@Getter
@Setter
@Component
public class InternetStateContext implements InternetState {
	
	private InternetState internetState;
	
	public boolean doAction() {
		return this.internetState.doAction();
		
	}

}
