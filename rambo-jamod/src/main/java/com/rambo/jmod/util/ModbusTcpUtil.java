package com.rambo.jmod.util;


import java.net.InetAddress;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.SimpleRegister;

public class ModbusTcpUtil {

	/**
	 * read only boolean data F02
	 * 
	 * @param ip
	 * @param ref
	 * @param count
	 * @param slaveId
	 * @return
	 * @throws ModbusIOException
	 * @throws ModbusSlaveException
	 * @throws ModbusException
	 */
	public static int readDiscretesInput(String ip, int port, int slaveId, int ref, int count) {
		int data = 0;

		try {
			InetAddress addr = InetAddress.getByName(ip);

			// build the connection
			TCPMasterConnection con = ModbusConnection.openTcpConnection(ip, port);

			

			// the 1st: the ref of register; 2nd is the number of register will be read
			ReadInputDiscretesRequest req = new ReadInputDiscretesRequest(ref, count);

			// the Slave Id
			req.setUnitID(slaveId);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);

			trans.setRequest(req);

			// execute the search
			trans.execute();

			// get the result
			ReadInputDiscretesResponse res = (ReadInputDiscretesResponse) trans.getResponse();

			if(res.getDiscretes().getBit(0)){
				data = 1;
			}

			// 关闭连接
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * 
	 * @function: readonly register data F04
	 * @param ip
	 * @param port
	 * @param ref
	 * @param slaveId
	 * @return
	 * @author: Rambo Zhu    20 Jun 2017 3:50:06 pm
	 */
	public static int readInputRegister(String ip, int port, int slaveId, int ref, int count) {
		int data = 0;

		try {
			TCPMasterConnection con = ModbusConnection.openTcpConnection(ip, port);
			con.connect();
			
			ReadInputRegistersRequest req = new ReadInputRegistersRequest(ref, count);
			
			req.setUnitID(slaveId);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);

			trans.setRequest(req);

			trans.execute();

			ReadInputRegistersResponse res = (ReadInputRegistersResponse) trans.getResponse();

			data = res.getRegisterValue(0);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * ReadCoilsRequest read/write boolean F01 
	 * @function:                    
	 * @param ip
	 * @param port
	 * @param ref
	 * @param slaveId
	 * @return
	 * @author: Rambo Zhu     20 Jun 2017 3:27:01 pm
	 */
	public static int readCoil(String ip, int port, int slaveId, int ref, int count) {
		int data = 0;
		try {
			
			TCPMasterConnection con = ModbusConnection.openTcpConnection(ip, port);
			con.connect();

			ReadCoilsRequest req = new ReadCoilsRequest(ref, count);

			req.setUnitID(slaveId);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);

			trans.setRequest(req);

			trans.execute();

			ReadCoilsResponse res = ((ReadCoilsResponse) trans.getResponse());

			if(res.getCoils().getBit(0)){
				data = 1;
			}

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return data;
	}

	/**
	 * 
	 * @function: read/write register data F03
	 * @param ip
	 * @param port
	 * @param ref
	 * @param slaveId
	 * @return
	 * @author: Rambo Zhu     20 Jun 2017 3:50:26 pm
	 */
	public static int readRegister(String ip, int port, int slaveId, int ref, int count) {
		int data = 0;
		try {
			TCPMasterConnection con = ModbusConnection.openTcpConnection(ip, port);
			con.connect();
			ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(ref, count);
			req.setUnitID(slaveId);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);

			trans.setRequest(req);

			trans.execute();

			ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();
			
			data = res.getRegisterValue(0);

			System.out.println(" - "+ res.getHexMessage() + " -- " + data + " -- " + res.getRegisterValue(1));
			
		    Float value = Float.intBitsToFloat((data<<16)+res.getRegisterValue(1));

		    System.out.println(value);
		    
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * unified method for read data 
	 * 
	 * @function:
	 * @param ip
	 * @param port
	 * @param ref
	 * @param slaveId
	 * @param fCode
	 * @return
	 * @author: Rambo Zhu     20 Jun 2017 4:14:44 pm
	 */
	public static int readData(String ip, int port, int slaveId, int ref,int count, String fCode) {
		int data = 0;
		switch (fCode) {
        case "F01":
        	data = ModbusTcpUtil.readDiscretesInput(ip, port, slaveId, ref, count);
            break;
        case "F02":
        	data = ModbusTcpUtil.readCoil(ip, port, slaveId, ref, count);
            break;
        case "F03":
        	data = ModbusTcpUtil.readRegister(ip, port, slaveId, ref, count);
            break;
        case "F04":
        	data = ModbusTcpUtil.readInputRegister(ip, port, slaveId, ref, count);
            break;
        default:
            throw new IllegalArgumentException("Invalid function code for reading ");
    }
		return data;
	}	
	
	
/**
 * unified method for write data
 * @function:
 * @param ip
 * @param port
 * @param slaveId
 * @param ref
 * @param value
 * @param fCode
 * @author: Rambo Zhu     20 Jun 2017 4:21:00 pm
 */
	public static void writeData(String ip, int port,int slaveId,
			int ref, int value, String fCode) {
		switch (fCode) {
	        case "F05":
	        	ModbusTcpUtil.writeDigitalOutput(ip, port, slaveId, ref, value);
	            break;
	        case "F06":
	        	ModbusTcpUtil.writeRegister(ip, port, slaveId, ref, value);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid function code for writing ");
	    }
		
	}	
	
	/**
	 * 
	 * Write the RE data to register F06
	 * 
	 * @param ip
	 * @param port
	 * @param slaveId
	 * @param ref
	 * @param value
	 */
	public static void writeRegister(String ip, int port, int slaveId,
			int ref, int value) {

		try {
			TCPMasterConnection connection = ModbusConnection.openTcpConnection(ip, port);
			connection.connect();

			ModbusTCPTransaction trans = new ModbusTCPTransaction(connection);

			SimpleRegister register = new SimpleRegister(value);

			
			WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(
					ref, register);

			req.setUnitID(slaveId);
			trans.setRequest(req);

			System.out.println("ModbusSlave: FC" + req.getFunctionCode()
					+ " ref=" + req.getReference() + " value="
					+ register.getValue());
			trans.execute();

			connection.close();
		} catch (Exception ex) {
			System.out.println("Error in code");
			ex.printStackTrace();
		}
	}

	/**
	 * Write the DO data to register F05
	 * 
	 * @param ip
	 * @param port
	 * @param slaveId
	 * @param ref
	 * @param value
	 */
	public static void writeDigitalOutput(String ip, int port, int slaveId,
			int ref, int value) {

		try {
			TCPMasterConnection connection = ModbusConnection.openTcpConnection(ip, port);

			connection.connect();

			ModbusTCPTransaction trans = new ModbusTCPTransaction(connection);

			boolean val = true;

			if (value == 0) {
				val = false;
			}

			WriteCoilRequest req = new WriteCoilRequest(ref, val);

			req.setUnitID(slaveId);
			trans.setRequest(req);

			System.out.println("ModbusSlave: FC" + req.getFunctionCode()
			+ " ref=" + req.getReference() + " value="
			+ val);
			
			trans.execute();
			connection.close();
		} catch (Exception ex) {
			System.out.println("writeDigitalOutput Error in code");
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		System.out.println(0x4028);
		System.out.println(0x51D8);
		System.out.println((0x4028<<16) +  0x51D8 );
		System.out.println(0x402851D8);
		
		  Long dataAsLong = Long.parseLong("402851D8", 16);
		  System.out.println(dataAsLong);
	      Float value = Float.intBitsToFloat(dataAsLong.intValue());
	       
	      System.out.println(value);
	}
	
}