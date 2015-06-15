package com.coffeemachine;

import static com.coffeemachine.DrinkOrderBuilder.newMessage;
import static com.coffeemachine.DrinkOrderBuilder.newOrder;
import static com.coffeemachine.DrinkType.CHOCOLATE;
import static com.coffeemachine.DrinkType.COFFEE;
import static com.coffeemachine.DrinkType.EXTRA_HOT_CHOCOLATE;
import static com.coffeemachine.DrinkType.EXTRA_HOT_COFFEE;
import static com.coffeemachine.DrinkType.EXTRA_HOT_TEA;
import static com.coffeemachine.DrinkType.ORANGE_JUICE;
import static com.coffeemachine.DrinkType.TEA;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class CoffeeMachineControllerTest {

	private CoffeeMachineController coffeeMachineController;
	private DrinkMaker drinkMaker;
	@Parameter(value = 1)
	public String expectedCommand;
	@Parameter(value = 0)
	public Object order;

	@Before
	public void init() {
		drinkMaker = Mockito.mock(DrinkMaker.class);
		coffeeMachineController = new CoffeeMachineController(drinkMaker);
	}

	@Parameters(name = "\"{1}\" = {0}")
	public static Collection<Object[]> getExpectedMessagesForOrders() {
		return Arrays
				.asList(new Object[][] {
						{ newOrder().of(TEA).withOneSugar().withCashAmount("0.4").asOrder(), "T:1:0" },
						{ newOrder().of(CHOCOLATE).withCashAmount("0.5").asOrder(), "H::" },
						{ newOrder().of(COFFEE).withTwoSugars().withCashAmount("0.6").asOrder(), "C:2:0" },
						{ newMessage("Hello world !"), "M:Hello world !" },
						{ newOrder().of(TEA).withOneSugar().withCashAmount("0.3").asOrder(),
								"M:Unsufficient funds : 0.1 euros missing" },
						{ newOrder().of(CHOCOLATE).withOneSugar().asOrder(), "M:Unsufficient funds : 0.5 euros missing" },
						{ newOrder().of(COFFEE).withOneSugar().asOrder(), "M:Unsufficient funds : 0.6 euros missing" },
						{ newOrder().of(COFFEE).withCashAmount("0.3").withOneSugar().asOrder(),
								"M:Unsufficient funds : 0.3 euros missing" },
						{ newOrder().of(TEA).withCashAmount("0.4").asOrder(), "T::" },
						{ newOrder().of(CHOCOLATE).withCashAmount("0.5").asOrder(), "H::" },
						{ newOrder().of(COFFEE).withCashAmount("0.6").asOrder(), "C::" },
						{ newOrder().of(ORANGE_JUICE).withCashAmount("0.6").asOrder(), "O::" },
						{ newOrder().of(EXTRA_HOT_COFFEE).withCashAmount("0.6").asOrder(), "Ch::" },
						{ newOrder().of(EXTRA_HOT_TEA).withOneSugar().withCashAmount("0.4").asOrder(), "Th:1:0" },
						{ newOrder().of(EXTRA_HOT_CHOCOLATE).withTwoSugars().withCashAmount("0.5").asOrder(), "Hh:2:0" },
				//
				});
	}

	@Test
	public void should_send_command_when_received_order() {
		if (this.order instanceof DrinkOrder) {
			coffeeMachineController.orderDrink((DrinkOrder) this.order);
		} else {
			coffeeMachineController.displayMessage((String) this.order);
		}
		verify(drinkMaker).sendCommand(expectedCommand);
	}

}
