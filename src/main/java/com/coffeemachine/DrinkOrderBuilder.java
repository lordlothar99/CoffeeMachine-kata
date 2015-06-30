package com.coffeemachine;

import static com.coffeemachine.DrinkType.valueOf;

import java.math.BigDecimal;

public class DrinkOrderBuilder {

	private final DrinkOrder drinkOrder;

	public DrinkOrderBuilder() {
		this.drinkOrder = new DrinkOrder();
	}

	public static DrinkOrderBuilder newOrder() {
		return new DrinkOrderBuilder();
	}

	public DrinkOrderBuilder ofDrink(DrinkType drinkType) {
		this.drinkOrder.setType(drinkType);
		return this;
	}

	public DrinkOrderBuilder ofDrink(String drinkType) {
		return ofDrink(valueOf(drinkType.replace(' ', '_').toUpperCase()));
	}

	public DrinkOrderBuilder withOneSugar() {
		return withSugarQuantity(1);
	}

	public DrinkOrderBuilder withTwoSugars() {
		return withSugarQuantity(2);
	}

	public DrinkOrderBuilder withMoney(String money) {
		return withMoney(new BigDecimal(money));
	}

	public DrinkOrderBuilder withMoney(BigDecimal money) {
		this.drinkOrder.setMoney(money);
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
