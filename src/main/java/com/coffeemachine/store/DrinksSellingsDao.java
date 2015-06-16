package com.coffeemachine.store;

import com.coffeemachine.DrinkType;

public interface DrinksSellingsDao {

	DrinksSellings getDrinksSellings();

	void addDrinkSelling(DrinkType type);

}
