package com.illposed.osc;

import com.illposed.osc.utility.*;

/**
 * OSCPacket is the abstract superclass for the various
 * kinds of OSC Messages. The actual packets are:
*/
public abstract class OSCPacket {

	protected boolean isByteArrayComputed;
	protected byte[] byteArray;

	/**
	 * Default constructor for the abstract class
	 */
	public OSCPacket() {
		super();
	}

	/**
	 * Generate a representation of this packet conforming to the
	 * the OSC byte stream specification. Used Internally.
	 */
	protected void computeByteArray() {
		OSCJavaToByteArrayConverter stream = new OSCJavaToByteArrayConverter();
		computeByteArray(stream);
	}

	/**
	 * Subclasses should implement this method to product a byte array
	 * formatted according to the OSC specification.
	 * @param stream OscPacketByteArrayConverter
	 */
	protected abstract void computeByteArray(OSCJavaToByteArrayConverter stream);

	/**
	 * Return the OSC byte stream for this packet.
	 * @return byte[]
	 */
	public byte[] getByteArray() {
		if (!isByteArrayComputed) 
			computeByteArray();
		return byteArray;
	}

	/**
	 * Run any post construction initialization. (By default, do nothing.) 
	 */ 
	protected void init() {
		
	}
}