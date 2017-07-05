/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod.util -> TcpProcess.java
 * Created on 30 Jun 2017-5:37:59 pm
 */
package com.rambo.jmod.util;

import java.util.ArrayList;
import java.util.List;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.net.TCPMasterConnection;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu  </a>
 * @version v 1.0 
 * Create:  30 Jun 2017 5:37:59 pm
 */
public class ModbusProcess {

	/**
	 * @function:
	 * @param req
	 * @author: Rambo Zhu     30 Jun 2017 5:32:12 pm
	 * @throws ModbusException 
	 * @throws ModbusSlaveException 
	 * @throws ModbusIOException 
	 */
	public static ModbusResponse tcpProcess(TCPMasterConnection con, ModbusRequest req) throws ModbusIOException, ModbusSlaveException, ModbusException {
		// TODO Auto-generated method stub
		
		ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
		trans.setRequest(req);
		trans.execute();

		return trans.getResponse();
	}
	
	
	public static ModbusResponse serialProcess(SerialConnection con, ModbusRequest req) throws ModbusIOException, ModbusSlaveException, ModbusException {
		
		ModbusSerialTransaction trans = new ModbusSerialTransaction(con);
		trans.setRequest(req);
		trans.execute();
		return trans.getResponse();
	}
	
	
	public static List<Integer> readCoils(ReadCoilsResponse res) {
		if(res != null && res.getBitCount() > 0){
			int length = res.getBitCount();
			ArrayList<Integer> list = new ArrayList<Integer>(length);
			for(int i = 0; i < length; i++){
				list.add(res.getCoilStatus(i)? 1 : 0);
			}
			return list;
		}
		return null;
	}
	
	
	public static List<Integer> readDiscretesInputs(ReadInputDiscretesResponse res) {
		System.out.println(res.getDiscretes().size());
		if(res != null && res.getDiscretes().size() > 0){
			int length = res.getDiscretes().size();
			ArrayList<Integer> list = new ArrayList<Integer>(length);
			for(int i = 0; i < length; i++){
				list.add(res.getDiscretes().getBit(i)? 1 : 0);
			}
			return list;
		}
		return null;
	}
	
	public static List<Integer> readInputRegisters(ReadInputRegistersResponse res) {

		if(res != null && res.getWordCount() > 0){
			int length = res.getWordCount();
			ArrayList<Integer> list = new ArrayList<Integer>(length);
			for(int i = 0; i < length; i++){
				list.add(res.getRegisterValue(i));
			}
			return list;
		}
		return null;
	}
	
	public static List<Integer> readRegisters(ReadMultipleRegistersResponse res) {

		if(res != null && res.getWordCount() > 0){
			int length = res.getWordCount();
			ArrayList<Integer> list = new ArrayList<Integer>(length);
			for(int i = 0; i < length; i++){
				list.add(res.getRegisterValue(i));
			}
			return list;
		}
		return null;
	}
	
	
}
