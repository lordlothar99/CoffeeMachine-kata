package com.coffeemachine;

import java.math.BigDecimal;

public enum DrinkType {

	TEA("T:", new BigDecimal("0.4")), //
	CHOCOLATE("H:", new BigDecimal("0.5")), //
	COFFEE("C:", new BigDecimal("0.6")), //
	ORANGE_JUICE("O:", new BigDecimal("0.6")), //
	EXTRA_HOT_COFFEE("Ch:", new BigDecimal("0.6")), //
	EXTRA_HOT_CHOCOLATE("Hh:", new BigDecimal("0.5")), //
	EXTRA_HOT_TEA("Th:", new BigDecimal("0.4"));

	private String code;
	private BigDecimal price;

	private DrinkType(String code, BigDecimal price) {
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
