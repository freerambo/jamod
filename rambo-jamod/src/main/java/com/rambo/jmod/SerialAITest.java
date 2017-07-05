/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod -> SerialAITest.java
 * Created on 20 Jun 2017-4:55:05 pm
 */
package com.rambo.jmod;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu  </a>
 * @version v 1.0 
 * Create:  20 Jun 2017 4:55:05 pm
 */
import java.net.*;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

import java.io.*;
//import net.wimpi.modbus.*;
//import net.wimpi.modbus.msg.*;
//import net.wimpi.modbus.io.*;
//import net.wimpi.modbus.net.*;
//import net.wimpi.modbus.util.*;
 
public class SerialAITest {

  public static void main(String[] args) {
	  
    try {

    
    	/* The important instances of the classes mentioned before */
    	SerialConnection con = null; //the connection
    	ReadInputRegistersRequest req = null; //the request
    	ReadInputRegistersResponse res = null; //the response
    	ModbusSerialTransaction trans = null; //the transaction

    	
    	/* Variables for storing the parameters */
    	String portname= "COM11"; //the name of the serial port to be used
    	int unitid = 2; //the unit identifier we will be talking to
    	int ref = 12; //the reference, where to start reading from
    	int count = 1; //the count of IR's to read
    	int repeat = 1; //a loop for repeating the transaction
    	
    	
    	//1. Setup the parameters         
    	
    	
    	//2. Set master identifier

//    	ModbusCoupler.getReference().setUnitID(2);
    	
    	//3. Setup serial parameters
    	SerialParameters params = new SerialParameters();
    	params.setPortName(portname);
    	params.setBaudRate(9600);
    	params.setDatabits(8);
    	params.setStopbits(1);
    	params.setParity("even");
    	params.setEncoding("rtu");

    	
    	//4. Open the connection
    	con = new SerialConnection(params);
    	con.open();

    	//5. Prepare a request
    	req = new ReadInputRegistersRequest(ref, count);
    	req.setUnitID(unitid);
    	req.setHeadless();

    	//6. Prepare a transaction
    	trans = new ModbusSerialTransaction(con);
    	trans.setRequest(req);
    	
    	//7. Execute the transaction repeat times
    	int k = 0;
    	do {
    	  trans.execute();
    	  res = (ReadInputRegistersResponse) trans.getResponse();
    	  for (int n = 0; n < res.getWordCount(); n++) {
    	    System.out.println("Word " + n + "=" + res.getRegisterValue(n));
    	  }
    	  k++;
    	} while (k < repeat);

    	//8. Close the connection
    	con.close();  
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main
  
}//class SerialAITest