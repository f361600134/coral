package com.coral.api.dto;

import java.io.Serializable;

public class Tester implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3092131245476463844L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tester(String name) {
		super();
		this.name = name;
	}

}
