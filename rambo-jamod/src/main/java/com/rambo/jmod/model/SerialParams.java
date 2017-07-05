/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod.model -> ed.java
 * Created on 30 Jun 2017-5:05:18 pm
 */
package com.rambo.jmod.model;

/** SerialParams
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu  </a>
 * @version v 1.0 
 * Create:  30 Jun 2017 5:05:18 pm
 */
public class SerialParams{
	
	public String portName;
	public int baudRate;
	public int databits;
	public int stopbits;
	public String parity;
	public String encoding;
	 
	public SerialParams() {
		super();
	}
	/**
	 * 
	 * @param portName
	 * @param baudRate
	 * @param databits
	 * @param stopbits
	 *            only can be "1", "1.5" or "2"
	 * @param parity
	 *            only can be "none", "odd" or "even"
	 */
	public SerialParams(String portName, int baudRate, int databits, int stopbits, String parity, String encoding) {
		super();
		this.portName = portName;
		this.baudRate = baudRate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity;
		this.encoding = encoding;
	}
}
