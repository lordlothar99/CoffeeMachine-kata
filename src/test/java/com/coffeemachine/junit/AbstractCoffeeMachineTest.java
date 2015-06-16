package com.coffeemachine.junit;

import org.junit.Before;
import org.mockito.Mockito;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.ReportManagerImpl;

public abstract class AbstractCoffeeMachineTest {

	protected CoffeeMachineController coffeeMachineController;
	protected DrinkMaker drinkMaker;

	@Before
	public void init() {
		drinkMaker = Mockito.mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, new ReportManagerImpl());
	}

}
