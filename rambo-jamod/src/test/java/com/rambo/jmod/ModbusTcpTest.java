package com.rambo.jmod;

import java.net.InetAddress;

import org.junit.Test;

import com.rambo.jmod.util.ModbusTcpUtil;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;

/**
 * Unit test for simple App.
 */
public class ModbusTcpTest {
   
	
//	@Test
	public void testReadHolding(){
			try { 
				//F03 0x4
				InetAddress addr = InetAddress.getByName("172.21.76.119");
				TCPMasterConnection con = new TCPMasterConnection(addr);
				con.setPort(502);
				con.connect();
				ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(30, 6);
				req.setUnitID(3);
				ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
				trans.setRequest(req);
				trans.execute();
				ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();
				System.out.println(res.getByteCount() + "; " + res.getDataLength()+ "; " + res.getFunctionCode()+ "; " + res.getOutputLength()+ "; " + res.getWordCount()+ "; " + res.getRegisters().length );
				Register[] results = res.getRegisters();
				for(Register result : results ){
					System.out.println("result "+result.getValue());
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
//	@Test 
	public void readDigit(){ // F03
//		int i = ModbusTcpUtil.readRegister("172.21.76.119", 502, 33, 3);
//    	System.out.println("read Holding " + i);
	}
	
//	@Test 
	public void readRegisterTest(){ // F03
//		int i = ModbusTcpUtil.readRegister("192.168.0.7", 23, 3059, 1);
//    	System.out.println("read Holding " + i);
	}
	
//	@Test
	public void readInputDigit(){ // F04
//    	int i = ModbusTcpUtil.readInputRegister("172.21.76.119", 502, 44, 4);
//    	System.out.println("readInput "+ i);
	}
	
//	@Test
	public void readCoils(){  // F01
//    	int i = ModbusTcpUtil.readCoil("172.21.76.119", 502, 0, 1);
//    	System.out.println("readCoils "+ i);
	}
	
//	@Test
	public void readCoilInput(){ // F02
//    	int i = ModbusTcpUtil.readDiscretesInput("172.21.76.119", 502, 22, 2);
//    	System.out.println("readCoils "+ i);
	}
	
//	@Test
	public void writeCoil(){ // F05 
		
    	ModbusTcpUtil.writeDigitalOutput("172.21.76.119", 502, 1, 2, 1);
	}
	
//	@Test
	public void writeRegister(){ //  
    	ModbusTcpUtil.writeRegister("172.21.76.119", 502, 3, 30, 30);
	}
	@Test
	public void readTest(){ //  
//		int i = ModbusTcpUtil.readData("172.21.76.119", 502, 0, 1, "F01");
//		System.out.println("F01 " + i);
//		i = ModbusTcpUtil.readData("172.21.76.119", 502, 22, 2, "F02");
//		System.out.println("F02 " + i);
//		int i = ModbusTcpUtil.readData("192.168.0.7", 23,  1, 0x0C03, 2, "F03");
		
		int i = ModbusTcpUtil.readData("192.168.0.7", 23,  1, 3059, 2, "F03");

		System.out.println("F03 " + i);
		
//		01030002375B
//		i = ModbusTcpUtil.readData("172.21.76.119", 502, 44, 4, "F04");
//		System.out.println("F04 " + i);
	}
	
//	@Test
	public void writeTest(){ //  
    	ModbusTcpUtil.writeData("172.21.76.119", 502, 3, 30, 30, "F06");
    	ModbusTcpUtil.writeData("172.21.76.119", 502, 1, 2, 1, "F05");
	}
	
	
}
