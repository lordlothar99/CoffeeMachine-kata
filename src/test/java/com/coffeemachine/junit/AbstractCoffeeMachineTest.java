package com.coffeemachine.junit;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.store.DrinksSellingsDao;

public abstract class AbstractCoffeeMachineTest {

	protected CoffeeMachineController coffeeMachineController;
	protected DrinkMaker drinkMaker;

	@Before
	public void init() {
		drinkMaker = mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, mock(DrinksSellingsDao.class));
	}

}
