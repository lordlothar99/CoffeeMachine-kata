package com.coffeemachine.junit;

import static org.mockito.Mockito.verify;

import org.junit.Test;

public class CoffeeMachineControllerDisplayTest extends AbstractCoffeeMachineTest {

	@Test
	public void should_send_M_command_when_user_wants_to_display_a_message_on_the_interface() {
		coffeeMachineController.displayMessage("Hello world !");
		verify(drinkMaker).sendCommand("M:Hello world !");
	}

}
