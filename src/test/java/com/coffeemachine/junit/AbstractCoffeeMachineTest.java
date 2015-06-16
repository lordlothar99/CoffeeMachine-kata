package com.coffeemachine.junit;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import com.coffeemachine.CoffeeMachineController;
import com.coffeemachine.DrinkMaker;
import com.coffeemachine.report.ReportManager;
import com.coffeemachine.report.ReportManagerImpl;
import com.coffeemachine.store.DrinksSellingsDao;

public abstract class AbstractCoffeeMachineTest {

	protected CoffeeMachineController coffeeMachineController;
	protected DrinkMaker drinkMaker;
	protected DrinksSellingsDao drinksSellingsDao;
	protected ReportManager reportManager;

	@Before
	public void init() {
		drinkMaker = mock(DrinkMaker.class);
		drinksSellingsDao = mock(DrinksSellingsDao.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker, drinksSellingsDao);
		reportManager = new ReportManagerImpl(drinksSellingsDao);
	}

}
