package com.coffeemachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ReportManagerImpl implements ReportManager {

	private final Map<DrinkType, Integer> soldDrinks = new HashMap<DrinkType, Integer>();

	@Override
	public void soldDrink(DrinkType drinkType) {
		Integer soldDrinksCount = soldDrinks.get(drinkType);
		if (soldDrinksCount == null) {
			soldDrinksCount = 0;
		}
		soldDrinks.put(drinkType, ++soldDrinksCount);
	}

	@Override
	public String generateReport() {
		String report = "";
		String sep = "";
		BigDecimal earnedMoney = BigDecimal.ZERO;
		for (Map.Entry<DrinkType, Integer> soldDrink : soldDrinks.entrySet()) {
			Integer soldDrinkCount = soldDrink.getValue();
			DrinkType drinkType = soldDrink.getKey();
			report += sep;
			report += soldDrinkCount;
			report += " ";
			report += drinkType.toString().toLowerCase();
			if (soldDrinkCount > 1) {
				report += "s";
			}
			report += " sold";
			sep = "\n";

			earnedMoney = earnedMoney.add(earnedMoney(soldDrinkCount, drinkType));
		}

		report += sep;
		report += "Total earned money: " + earnedMoney + " euros";

		return report;
	}

	private BigDecimal earnedMoney(Integer soldDrinkCount, DrinkType drinkType) {
		return drinkType.getPrice().multiply(new BigDecimal(soldDrinkCount));
	}
}
