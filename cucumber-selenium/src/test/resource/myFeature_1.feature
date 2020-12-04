@runfile 
Feature: Navigate to xxxx and Search Pages 


@setup 
Scenario: Log into xxx 
	Given I am on the "xxxx" Website 
	And element with id "userName" is displayed 
	When I enter password 
	
		| fields   | value                       |
		| username | xxx.xxx@xxxx  |
		| password | x                           |
		
		
	Then element with id "at-lbl-username" has text of "Ninad Telang" 
	
	
	
Scenario Outline: Navigate to All Pages 
	When I click element with id "<Link>" 
	Then element with id "<Page>" is displayed 
	
	Examples: 
		| Link                      | Page                   |
		| at-xxx-operations | at-hdr-tailsign        |
		| at-linkNavUpload          | fileupload             |
		| at-linkNavfleetreport     | at-airlineSearch-input |
		
		
Scenario: Excercise Text Search 
	When I enter text "yyyyyy" into field with id "at-airlineSearch-input" 
	Then element with id "yyyyyy" is displayed 
	
	
	
	
	
