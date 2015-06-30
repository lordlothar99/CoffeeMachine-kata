package com.coffeemachine;

import java.math.BigDecimal;

public enum DrinkType {

	CHOCOLATE("H", new BigDecimal("0.5")), //
	COFFEE("C", new BigDecimal("0.6")), //
	ORANGE_JUICE("O", new BigDecimal("0.6")), //
	HOT_CHOCOLATE("Hh", new BigDecimal("0.5")), //
	HOT_COFFEE("Ch", new BigDecimal("0.6")), //
	HOT_TEA("Th", new BigDecimal("0.4")), //
	TEA("T", new BigDecimal("0.4"));

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

	@Override
	public String toString() {
		return super.name().toLowerCase();
	}
}
