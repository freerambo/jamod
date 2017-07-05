/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod -> ModbusConnection.java
 * Created on 30 Jun 2017-4:34:04 pm
 */
package com.rambo.jmod.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.rambo.jmod.model.SerialParams;
import com.rambo.jmod.model.WriteParams;

import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.SerialParameters;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu </a>
 * @version v 1.0 Create: 30 Jun 2017 4:34:04 pm
 */
public class ModbusConnection {

	/**
	 * Get a Modbus TCP COnnection
	 * 
	 * @function:
	 * @param ip
	 * @param port
	 * @return
	 * @author: Rambo Zhu 30 Jun 2017 4:45:01 pm
	 * @throws Exception 
	 */
	public static TCPMasterConnection openTcpConnection(String ip, int port) throws Exception {
		InetAddress addr = InetAddress.getByName(ip);
		// build the connection
		TCPMasterConnection con = new TCPMasterConnection(addr);
		con.setPort(port);
		con.connect();
		return con;
	}

	/**
	 * Get a Modbus Serial COnnection
	 * 
	 * @function:
	 * @param portName
	 * @param baudRate
	 * @param databits
	 * @param stopbits
	 *            only can be "1", "1.5" or "2"
	 * @param parity
	 *            only can be "none", "odd" or "even"
	 * @return
	 * @author: Rambo Zhu 30 Jun 2017 4:44:07 pm
	 * @throws Exception 
	 */
	public static SerialConnection openSerialConnection(SerialParams sParams) throws Exception {
		SerialParameters params = new SerialParameters();

		params.setPortName(sParams.portName);
		params.setBaudRate(sParams.baudRate);
		params.setDatabits(sParams.databits);
		params.setStopbits(sParams.stopbits);
		params.setParity(sParams.parity);
		params.setEncoding(sParams.encoding);
		
		// Open the Serial Connection
		SerialConnection con = new SerialConnection(params);
		con.open();
		return con;
	}

}
