/*
 * Copyright: Energy Research Institute @ NTU
 * jamod
 * com.rambo.jmod -> SerialSlaveTest.java
 * Created on 30 Jun 2017-2:51:45 pm
 */
package com.rambo.jmod;

/**
 * function description：
 *
 * @author <a href="mailto:zhuyb@ntu.edu.sg">Rambo Zhu  </a>
 * @version v 1.0 
 * Create:  30 Jun 2017 2:51:45 pm
 */
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.*;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.util.SerialParameters;

public class SerialSlaveTest {
  
  public static void main(String[] args) {
    try {

    	/* The important instances and variables */
    	ModbusSerialListener listener = null;
    	SimpleProcessImage spi = null;
    	String portname = "COM10"; //the portname of the serial port to listen to  
    	
    	//1. Prepare a process image
    	spi = new SimpleProcessImage();
    	spi.addDigitalOut(new SimpleDigitalOut(true));
    	spi.addDigitalOut(new SimpleDigitalOut(false));
    	spi.addDigitalIn(new SimpleDigitalIn(false));
    	spi.addDigitalIn(new SimpleDigitalIn(true));
    	spi.addDigitalIn(new SimpleDigitalIn(false));
    	spi.addDigitalIn(new SimpleDigitalIn(true));
    	spi.addRegister(new SimpleRegister(251));
    	spi.addInputRegister(new SimpleInputRegister(45));

    	//2. Create the coupler and set the slave identity
    	ModbusCoupler.getReference().setProcessImage(spi);
    	ModbusCoupler.getReference().setMaster(false);
    	ModbusCoupler.getReference().setUnitID(2);  
    	
    	
    	//3. Set up serial parameters
    	SerialParameters params = new SerialParameters();
    	params.setPortName(portname);
    	params.setBaudRate(9600);
    	params.setDatabits(8);
    	params.setParity("None");
    	params.setStopbits(1);
    	params.setEncoding("rtu");
    	params.setEcho(false);
    	
    	
    	
    	//4. Set up serial listener
    	listener = new ModbusSerialListener(params);
    	listener.setListening(true);   
    	
    	
    	
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main
  
}//class SerialSlaveTest