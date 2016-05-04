package com.coffeemachine.junit;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.CHOCOLATE;
import static com.coffeemachine.DrinkType.COFFEE;
import static com.coffeemachine.DrinkType.HOT_CHOCOLATE;
import static com.coffeemachine.DrinkType.HOT_COFFEE;
import static com.coffeemachine.DrinkType.HOT_TEA;
import static com.coffeemachine.DrinkType.ORANGE_JUICE;
import static com.coffeemachine.DrinkType.TEA;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.coffeemachine.DrinkOrder;

@RunWith(Parameterized.class)
public class CoffeeMachineControllerDrinkOrderTest extends AbstractCoffeeMachineTest {

	@Parameter(value = 1)
	public String expectedCommand;
	@Parameter(value = 0)
	public DrinkOrder order;

	@Parameters(name = "\"{1}\" = {0}")
	public static Collection<Object[]> getExpectedCommandsForOrders() {
		return asList(
			expect("T:1:0").forOrder(newOrder().ofDrink(TEA).withOneSugar().withMoney("0.4").asOrder()),
			expect("H::").forOrder(newOrder().ofDrink(CHOCOLATE).withMoney("0.5").asOrder()),
			expect("C:2:0").forOrder(newOrder().ofDrink(COFFEE).withTwoSugars().withMoney("0.6").asOrder()),
			expect("M:Unsufficient funds : 0.1 euros missing").forOrder(newOrder().ofDrink(TEA).withOneSugar().withMoney("0.3").asOrder()),
			expect("M:Unsufficient funds : 0.5 euros missing").forOrder(newOrder().ofDrink(CHOCOLATE).withOneSugar().asOrder()),
			expect("M:Unsufficient funds : 0.6 euros missing").forOrder(newOrder().ofDrink(COFFEE).withOneSugar().asOrder()),
			expect("M:Unsufficient funds : 0.3 euros missing").forOrder(newOrder().ofDrink(COFFEE).withMoney("0.3").withOneSugar().asOrder()),
			expect("T::").forOrder(newOrder().ofDrink(TEA).withMoney("0.4").asOrder()),
			expect("H::").forOrder(newOrder().ofDrink(CHOCOLATE).withMoney("0.5").asOrder()),
			expect("C::").forOrder(newOrder().ofDrink(COFFEE).withMoney("0.6").asOrder()),
			expect("O::").forOrder(newOrder().ofDrink(ORANGE_JUICE).withMoney("0.6").asOrder()),
			expect("Ch::").forOrder(newOrder().ofDrink(HOT_COFFEE).withMoney("0.6").asOrder()),
			expect("Th:1:0").forOrder(newOrder().ofDrink(HOT_TEA).withOneSugar().withMoney("0.4").asOrder()),
			expect("Hh:2:0").forOrder(newOrder().ofDrink(HOT_CHOCOLATE).withTwoSugars().withMoney("0.5").asOrder())
			//
		);
	}

	private static Expectation expect(String expectedCommand) {
		return new Expectation(expectedCommand);
	}

	@Test
	public void should_send_command_when_received_order() {
		coffeeMachineController.orderDrink(this.order);
		verify(drinkMaker).sendCommand(expectedCommand);
	}

	private static class Expectation {

		private String expectedCommand;

		public Expectation(String expectedCommand) {
			this.expectedCommand = expectedCommand;
		}

		public Object[] forOrder(DrinkOrder drinkOrder) {
			return new Object[] { drinkOrder, expectedCommand };
		}
		
	}
}
