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
		for (Map.Entry<DrinkType, Integer> soldDrink : soldDrinks.entrySet()) {
			report += sep + getDrinkReport(soldDrink.getValue(), soldDrink.getKey());
			sep = "\n";
		}

		report += sep;
		report += "Total earned money: " + computeTotalEarnedMoney() + " euros";

		return report;
	}

	private String getDrinkReport(int soldDrinkCount, DrinkType drinkType) {
		String drinkReport = soldDrinkCount + " ";
		drinkReport += drinkType.toString().toLowerCase();
		if (soldDrinkCount > 1) {
			drinkReport += "s";
		}
		drinkReport += " sold";
		return drinkReport;
	}

	private BigDecimal computeTotalEarnedMoney() {
		BigDecimal earnedMoney = BigDecimal.ZERO;
		for (Map.Entry<DrinkType, Integer> soldDrink : soldDrinks.entrySet()) {
			Integer soldDrinkCount = soldDrink.getValue();
			DrinkType drinkType = soldDrink.getKey();

			earnedMoney = earnedMoney.add(computeEarnedMoney(soldDrinkCount, drinkType));
		}

		return earnedMoney;
	}

	private BigDecimal computeEarnedMoney(Integer soldDrinkCount, DrinkType drinkType) {
		return drinkType.getPrice().multiply(new BigDecimal(soldDrinkCount));
	}
}
