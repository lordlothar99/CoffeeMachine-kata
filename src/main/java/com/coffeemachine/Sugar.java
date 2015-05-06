package com.coffeemachine;

public enum Sugar {

	NONE(":"), ONE("1:0"), TWO("2:0");

	private String code;

	private Sugar(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
