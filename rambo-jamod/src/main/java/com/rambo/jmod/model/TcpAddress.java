/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod.model -> TcpAddress.java
 * Created on 30 Jun 2017-5:06:39 pm
 */
package com.rambo.jmod.model;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu </a>
 * @version v 1.0 Create: 30 Jun 2017 5:06:39 pm
 */
public class TcpAddress {

	public String ip;
	public int port;

	public TcpAddress() {
	}

	public TcpAddress(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

}
