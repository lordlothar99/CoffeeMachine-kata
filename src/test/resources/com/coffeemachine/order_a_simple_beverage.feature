Feature: Order a simple drink
	In order to pilot the drink maker
	As an orchestrator
	I want to send a command
	
	Scenario Outline: Order of a simple drink
		When a <drink> with <sugar> sugar and <cash amount> euros is ordered
		Then I should send '<command>' to the drink maker
	Examples:
    	| drink		| sugar	| cash amount	| command	|
    	| tea		| 1		| 0.4			| T:1:0		|
    	| chocolate	| 0		| 0.5			| H::		|
    	| coffee	| 2		| 0.6			| C:2:0		|

  Scenario: Display a message on the coffee machine interface
    When I want to display 'hello world !' on the interface
    Then I should send 'M:hello world !' to the drink maker