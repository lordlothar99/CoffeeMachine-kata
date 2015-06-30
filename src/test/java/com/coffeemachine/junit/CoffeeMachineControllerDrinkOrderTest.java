package com.coffeemachine.junit;

import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.CHOCOLATE;
import static com.coffeemachine.DrinkType.COFFEE;
import static com.coffeemachine.DrinkType.HOT_CHOCOLATE;
import static com.coffeemachine.DrinkType.HOT_COFFEE;
import static com.coffeemachine.DrinkType.HOT_TEA;
import static com.coffeemachine.DrinkType.ORANGE_JUICE;
import static com.coffeemachine.DrinkType.TEA;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
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
	public static Collection<Object[]> getExpectedMessagesForOrders() {
		return Arrays
				.asList(new Object[][] {
						{ newOrder().ofDrink(TEA).withOneSugar().withCashAmount("0.4").asOrder(), "T:1:0" },
						{ newOrder().ofDrink(CHOCOLATE).withCashAmount("0.5").asOrder(), "H::" },
						{ newOrder().ofDrink(COFFEE).withTwoSugars().withCashAmount("0.6").asOrder(), "C:2:0" },
						{ newOrder().ofDrink(TEA).withOneSugar().withCashAmount("0.3").asOrder(),
								"M:Unsufficient funds : 0.1 euros missing" },
						{ newOrder().ofDrink(CHOCOLATE).withOneSugar().asOrder(), "M:Unsufficient funds : 0.5 euros missing" },
						{ newOrder().ofDrink(COFFEE).withOneSugar().asOrder(), "M:Unsufficient funds : 0.6 euros missing" },
						{ newOrder().ofDrink(COFFEE).withCashAmount("0.3").withOneSugar().asOrder(),
								"M:Unsufficient funds : 0.3 euros missing" },
						{ newOrder().ofDrink(TEA).withCashAmount("0.4").asOrder(), "T::" },
						{ newOrder().ofDrink(CHOCOLATE).withCashAmount("0.5").asOrder(), "H::" },
						{ newOrder().ofDrink(COFFEE).withCashAmount("0.6").asOrder(), "C::" },
						{ newOrder().ofDrink(ORANGE_JUICE).withCashAmount("0.6").asOrder(), "O::" },
						{ newOrder().ofDrink(HOT_COFFEE).withCashAmount("0.6").asOrder(), "Ch::" },
						{ newOrder().ofDrink(HOT_TEA).withOneSugar().withCashAmount("0.4").asOrder(), "Th:1:0" },
						{ newOrder().ofDrink(HOT_CHOCOLATE).withTwoSugars().withCashAmount("0.5").asOrder(), "Hh:2:0" },
				//
				});
	}

	@Test
	public void should_send_command_when_received_order() {
		coffeeMachineController.orderDrink(this.order);
		verify(drinkMaker).sendCommand(expectedCommand);
	}

}
