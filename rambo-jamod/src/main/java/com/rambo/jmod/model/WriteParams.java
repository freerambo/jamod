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
public class WriteParams extends ReadParams{

	public int value;
	  
	public WriteParams() {
		super();
	}
	public WriteParams(int ref, int count, int slaveId, int value) {
		super(ref, count, slaveId);
		this.value = value;
	}
}
