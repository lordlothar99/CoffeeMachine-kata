package com.coffeemachine.store;

import java.util.HashMap;
import java.util.Map;

import com.coffeemachine.DrinkType;

public class DrinksSellings {

	private final Map<DrinkType, DrinkSelling> sellings = new HashMap<DrinkType, DrinkSelling>();

	public void add(DrinkType drinkType, int soldCount) {
		DrinkSelling drinkSelling = sellings.get(drinkType);
		if (drinkSelling == null) {
			drinkSelling = new DrinkSelling(drinkType);
			sellings.put(drinkType, drinkSelling);
		}
		drinkSelling.addSoldQuantity(soldCount);
	}

	public Iterable<DrinkSelling> iterator() {
		return sellings.values();
	}

}
