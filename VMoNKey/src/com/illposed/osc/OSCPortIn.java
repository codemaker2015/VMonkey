package com.illposed.osc;

import java.net.*;
import java.io.IOException;
import com.illposed.osc.utility.OSCByteArrayToJavaConverter;
import com.illposed.osc.utility.OSCPacketDispatcher;

/**
 * OSCPortIn is the class that listens for OSC messages.	
 * Then, using a program such as SuperCollider or sendOSC, send a message
 * to this computer, port 57110 (defaultSCOSCPort), with the address /message/receiving
 */
public class OSCPortIn extends OSCPort implements Runnable {

	// state for listening
	protected boolean isListening;
	protected OSCByteArrayToJavaConverter converter = new OSCByteArrayToJavaConverter();
	protected OSCPacketDispatcher dispatcher = new OSCPacketDispatcher();
	
	/**
	 * Create an OSCPort that listens on the specified port.
	 * @param port UDP port to listen on.
	 * @throws SocketException
	 */
	public OSCPortIn(int port) throws SocketException {
		socket = new DatagramSocket(port);
		this.port = port;
	}

	/**
	 * Run the loop that listens for OSC on a socket until isListening becomes false.
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
			// buffers were 1500 bytes in size, but this was
			// increased to 1536, as this is a common MTU
		byte[] buffer = new byte[1536];
		DatagramPacket packet = new DatagramPacket(buffer, 1536);
		while (isListening) {
			try {
				socket.receive(packet);
				OSCPacket oscPacket = converter.convert(buffer, packet.getLength());
				dispatcher.dispatchPacket(oscPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Start listening for incoming OSCPackets
	 */
	public void startListening() {
		isListening = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Stop listening for incoming OSCPackets
	 */
	public void stopListening() {
		isListening = false;
	}
	
	/**
	 * Am I listening for packets?
	 */
	public boolean isListening() {
		return isListening;
	}
	
	/**
	 * Register the listener for incoming OSCPackets addressed to an Address
	 * @param anAddress  the address to listen for
	 * @param listener   the object to invoke when a message comes in
	 */
	public void addListener(String anAddress, OSCListener listener) {
		dispatcher.addListener(anAddress, listener);
	}
	
	/**
	 * Close the socket and free-up resources. It's recommended that clients call
	 * this when they are done with the port.
	 */
	public void close() {
		socket.close();
	}
}
