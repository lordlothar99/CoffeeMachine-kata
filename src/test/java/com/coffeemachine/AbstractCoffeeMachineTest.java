package com.coffeemachine;

import org.junit.Before;
import org.mockito.Mockito;

public abstract class AbstractCoffeeMachineTest {

	protected CoffeeMachineController coffeeMachineController;
	protected DrinkMaker drinkMaker;

	@Before
	public void init() {
		drinkMaker = Mockito.mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, new ReportManagerImpl());
	}

}
