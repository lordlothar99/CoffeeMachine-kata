Feature: Send an email notification when a shortage is detected
	In order to notify the administrator that a shortage has been detected and a refill is necessry 
	As a coffee machine controller
	I want to send an email notification and display a message on the console
	
	Scenario: Send an email and display a message when a shortage is detected
		Given a coffee machine with a drink maker
		When there is no more coffee
		And a coffee with 0 sugar and 0.6 euros is ordered
		Then I should send an email notification for "coffee"
		And I should send 'M:A shortage of coffee has been detected. A notification has been sent.' to the drink maker