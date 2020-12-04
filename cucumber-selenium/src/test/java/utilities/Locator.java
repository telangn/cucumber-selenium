package utilities;

import org.openqa.selenium.By;

/* Helper Class for Locators
 * 
 * Example of locator expressions passed through regex of step -
 * 
 * id : username
 * css : #input .pwd
 * xpath : //button[text()="Some text"]
 * class : name
 * 
 * Use this in Step Definitions Classes like so -
 * driver.findElement(utilities.Locator.build(locatorExpression));
 */

public class Locator {

	public static By build(String locator) {

		String[] parts = locator.split(":");
		String use = parts[0].trim().toLowerCase();
		String value = parts[1].trim();

		if (use.equals("id")) {
			return By.id(value);
		} else if (use.equals("css")) {
			return By.cssSelector(value);
		} else if (use.equals("xpath")) {
			return By.xpath(value);
		} else if (use.equals("class")) {
			return By.className(value);
		} else {
			return null;
		}
	}
}
