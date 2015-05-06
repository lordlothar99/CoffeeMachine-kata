package com.coffeemachine;

public enum DrinkType {

	TEA("T:", 0.4f), CHOCOLATE("H:", 0.5f), COFFEE("C:", 0.6f), ORANGE_JUICE("O:", 0.6f), EXTRA_HOT_COFFEE("Ch:", 0.6f), EXTRA_HOT_CHOCOLATE(
			"Hh:", 0.5f), EXTRA_HOT_TEA("Th:", 0.4f);

	private String code;
	private float price;

	private DrinkType(String code, float price) {
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public float getPrice() {
		return price;
	}
}
