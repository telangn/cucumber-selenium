package stepDefinitions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import utilities.CustomWaits;
import utilities.DIService;

@SuppressWarnings("deprecation")
public class ThenSteps {

	WebDriver driver;
	CustomWaits waits;

	public ThenSteps(DIService service) throws Exception {
		this.driver = service.getDriver();
		this.waits = service.getWaits();
	}

	@Then("^element with id \"([^\"]*)\" has text of \"([^\"]*)\"$")
	public void checkText(String link, String text) throws Throwable {
		WebElement element = waits.explicitWait(link);
		String actualText = element.getText();
		Assert.assertEquals(text, actualText);
	}

	@Then("^page title is \"([^\"]*)\"$")
	public void pageTitle(String text) throws Throwable {
		Assert.assertEquals(text, driver.getTitle());
	}

	@Then("^table with id \"([^\"]*)\" has header values of$")
	public void tableHeaders(String id, DataTable table) throws Throwable {
		java.util.List<java.util.List<String>> expectedHeaders = table.cells();
		List<WebElement> allHeaders = null;
		List<String> headerNames = new ArrayList<String>();

		WebElement element = waits.explicitWait(id);
		allHeaders = element.findElements(By.tagName("th"));

		for (WebElement header : allHeaders) {
			headerNames.add(header.getText());
		}
		for (int i = 0; i < headerNames.size(); i++) {
			Assert.assertEquals(expectedHeaders.get(i).get(0), headerNames.get(i));
		}
	}

	@Then("^I take a screenshot$")
	public void takeAScreenshot() throws Throwable {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File("/home/ninad/eclipse-workspace/Reports/" + System.currentTimeMillis() + " .png"));
	}

	@Then("^I tab from this id \"([^\"]*)\"$")
	public void tab(String text) throws Throwable {
		WebElement element = driver.findElement(By.id(text));
		element.sendKeys(Keys.TAB);
	}

	@Then("^I click ENTER with keyboard from this id \"([^\"]*)\"$")
	public void enter(String text) throws Throwable {
		WebElement element = driver.findElement(By.id(text));
		element.sendKeys(Keys.ENTER);
	}

	@Then("^element with id \"([^\"]*)\" follows Css Rules$")
	public void testCssValues(String arg1, DataTable table) throws Throwable {
		java.util.List<java.util.List<String>> data = table.cells();
		WebElement element = driver.findElement(By.id(arg1));
		String color = element.getCssValue(data.get(1).get(0));
		Assert.assertEquals(data.get(1).get(1), color);
	}

	@Then("^element with id \"([^\"]*)\" is selected$")
	public void elementSelected(String link) throws Throwable {
		WebElement element = waits.explicitWait(link);
		Assert.assertEquals(true, element.isSelected());
	}

	@Then("^element with id \"([^\"]*)\" is checked$")
	public void elementChecked(String link) throws Throwable {
		String check = waits.explicitWait(link).getAttribute("checked");
		Assert.assertTrue(check.equalsIgnoreCase("true"));
	}

	@Then("^element with id \"([^\"]*)\" has attribute \"([^\"]*)\" with string \"([^\"]*)\"$")
	public void hiddenText(String arg1, String arg2, String arg3) throws Throwable {
		WebElement element = driver.findElement(By.id(arg1));
		String value = element.getAttribute(arg2).replaceAll("\n", "");
		Assert.assertEquals(arg3, value);
	}
}
