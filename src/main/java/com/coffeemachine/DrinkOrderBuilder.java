package com.coffeemachine;

import java.math.BigDecimal;

public class DrinkOrderBuilder {

	private final DrinkOrder drinkOrder;

	public DrinkOrderBuilder() {
		this.drinkOrder = new DrinkOrder();
	}

	public static DrinkOrderBuilder newOrder() {
		return new DrinkOrderBuilder();
	}

	public DrinkOrderBuilder of(DrinkType drinkType) {
		this.drinkOrder.setType(drinkType);
		return this;
	}

	public DrinkOrderBuilder withOneSugar() {
		return withSugarQuantity(1);
	}

	public DrinkOrderBuilder withTwoSugars() {
		return withSugarQuantity(2);
	}

	public DrinkOrderBuilder withCashAmount(String cashAmount) {
		return withCashAmount(new BigDecimal(cashAmount));
	}

	public DrinkOrderBuilder withCashAmount(BigDecimal cashAmount) {
		this.drinkOrder.setCashAmount(cashAmount);
		return this;
	}

	public DrinkOrder asOrder() {
		return this.drinkOrder;
	}

	public DrinkOrderBuilder withSugarQuantity(int sugarQuantity) {
		this.drinkOrder.setSugarQuantity(sugarQuantity);
		return this;
	}
}
