package com.coffeemachine;

import static com.coffeemachine.Sugar.NONE;
import static com.coffeemachine.Sugar.ONE;
import static com.coffeemachine.Sugar.TWO;

public class DrinkOrderBuilder {

	private final DrinkOrder drinkOrder;

	public DrinkOrderBuilder() {
		this.drinkOrder = new DrinkOrder();
		this.drinkOrder.setSugar(NONE);
	}

	public static DrinkOrderBuilder newOrder() {
		return new DrinkOrderBuilder();
	}

	public static String newMessage(String message) {
		return message;
	}

	public DrinkOrderBuilder of(DrinkType drinkType) {
		this.drinkOrder.setType(drinkType);
		return this;
	}

	public DrinkOrderBuilder withOneSugar() {
		this.drinkOrder.setSugar(ONE);
		return this;
	}

	public DrinkOrderBuilder withTwoSugars() {
		this.drinkOrder.setSugar(TWO);
		return this;
	}

	public DrinkOrderBuilder withCashAmount(float cashAmount) {
		this.drinkOrder.setCashAmount(cashAmount);
		return this;
	}

	public DrinkOrder asOrder() {
		return this.drinkOrder;
	}
}
