@POM 
Feature: Test the Page Object Model 

@setup
Scenario: Test Login Page POM 
	Given I am on the "xxxxx" Website 
	And element with id "userName" is displayed
	When I log in with username "xxxxx" and password "xxxxx" 
	Then element with id "at-lbl-username" has text of "xxxx xxx" 
