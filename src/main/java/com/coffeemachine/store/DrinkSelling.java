package com.coffeemachine.store;

import com.coffeemachine.DrinkType;

public class DrinkSelling {

	private final DrinkType drinkType;
	private int quantity;

	public DrinkSelling(DrinkType drinkType) {
		this.drinkType = drinkType;
	}

	public int getQuantity() {
		return quantity;
	}

	public DrinkType getDrinkType() {
		return drinkType;
	}

	public void addSoldQuantity(int quantity) {
		this.quantity += quantity;
	}

}
