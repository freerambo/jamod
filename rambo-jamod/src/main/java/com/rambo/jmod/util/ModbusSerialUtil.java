/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod -> asd.java
 * Created on 20 Jun 2017-4:55:05 pm
 */
package com.rambo.jmod.util;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu  </a>
 * @version v 1.0 
 * Create:  20 Jun 2017 4:55:05 pm
 */
import java.net.*;
import java.util.List;

import com.rambo.jmod.model.ReadParams;
import com.rambo.jmod.model.SerialParams;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.SerialParameters;

import java.io.*;
//import net.wimpi.modbus.*;
//import net.wimpi.modbus.msg.*;
//import net.wimpi.modbus.io.*;
//import net.wimpi.modbus.net.*;
//import net.wimpi.modbus.util.*;
 
public class ModbusSerialUtil {

	
	/**
	 * 	ModbusSerialUtil read Coils
	 * @function:
	 * @param sParams
	 * @param request
	 * @return
	 * @author: Rambo Zhu     30 Jun 2017 6:19:20 pm
	 */
	public static List<Integer> readCoils(SerialParams sParams, ReadParams request) {
		List<Integer> results =  null;
		try {
			SerialConnection con = ModbusConnection.openSerialConnection(sParams);
			ReadCoilsRequest req = new ReadCoilsRequest(request.ref, request.count);
			req.setUnitID(request.slaveId);
			
			ReadCoilsResponse res = (ReadCoilsResponse) ModbusProcess.serialProcess(con, req);
			
			results = ModbusProcess.readCoils(res);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}

	/**
	 * ModbusSerialUtil readDiscretesInputs
	 * @function:
	 * @param sParams
	 * @param request
	 * @return
	 * @author: Rambo Zhu     30 Jun 2017 6:19:34 pm
	 */
	public static List<Integer> readDiscretesInputs(SerialParams sParams, ReadParams request) {
		List<Integer> results =  null;
		try {
			SerialConnection con = ModbusConnection.openSerialConnection(sParams);
			ReadInputDiscretesRequest req = new ReadInputDiscretesRequest(request.ref, request.count);
			req.setUnitID(request.slaveId);
			
			ReadInputDiscretesResponse res = (ReadInputDiscretesResponse) ModbusProcess.serialProcess(con, req);
			
			results = ModbusProcess.readDiscretesInputs(res);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}	
  
	/** 
	 *  ModbusSerialUtil readRegisters
	 * @function:
	 * @param sParams
	 * @param request
	 * @return
	 * @author: Rambo Zhu     30 Jun 2017 6:19:49 pm
	 */
	public static List<Integer> readRegisters(SerialParams sParams, ReadParams request) {
		List<Integer> results =  null;
		try {
			SerialConnection con = ModbusConnection.openSerialConnection(sParams);
			ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(request.ref, request.count);
			req.setUnitID(request.slaveId);
			
			ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) ModbusProcess.serialProcess(con, req);
			
			results = ModbusProcess.readRegisters(res);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}	
	
	/**
	 *  ModbusSerialUtil readInputRegisters
	 * @function:
	 * @param sParams
	 * @param request
	 * @return
	 * @author: Rambo Zhu     30 Jun 2017 6:20:07 pm
	 */
	public static List<Integer> readInputRegisters(SerialParams sParams, ReadParams request) {
		List<Integer> results =  null;
		try {
			SerialConnection con = ModbusConnection.openSerialConnection(sParams);
			
			ReadInputRegistersRequest req = new ReadInputRegistersRequest(request.ref, request.count);
			req.setUnitID(request.slaveId);
			
			ReadInputRegistersResponse res = (ReadInputRegistersResponse) ModbusProcess.serialProcess(con, req);
			
			results = ModbusProcess.readInputRegisters(res);
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}

}//class SerialAITest