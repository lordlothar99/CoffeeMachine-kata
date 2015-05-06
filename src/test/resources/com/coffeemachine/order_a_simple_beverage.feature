Feature: Order a simple beverage
	In order to pilot the drink maker
	As an orchestrator
	I want to send a command

	Scenario: order of a coffee
		When a coffee is ordered
		Then i should send the command "C::" to the drink maker

	Scenario: order of a tea
		When a tea is ordered
		Then i should send the command "T::" to the drink maker
		
	Scenario Outline: Order of a simple beverage
		When a <beverage name> is ordered
		Then i should send the command <expected command> to the drink maker
		
		Examples:
		| beverage name | expected command |
		| coffee        | "C::"            |
		| tea           | "T::"            |