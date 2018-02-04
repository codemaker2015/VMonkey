package com.illposed.osc;

import java.util.Date;

/**
 * Interface for things that listen for incoming OSC Messages
 */
public interface OSCListener {
	
	/**
	 * @param time     The time this message is to be executed. null means execute now
	 * @param message  The message to execute.
	 */
	public void acceptMessage(Date time, OSCMessage message);

}
