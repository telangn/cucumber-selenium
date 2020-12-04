package stepDefinitions;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.datatable.DataTable;
import cucumber.api.java.en.When;
import pageObjectModels.LoginPage;
import utilities.CustomWaits;
import utilities.DIService;

public class WhenSteps {

	WebDriver driver;
	CustomWaits waits;

	public WhenSteps(DIService service) throws Exception {
		this.driver = service.getDriver();
		this.waits = service.getWaits();
	}

	@When("^I enter password$")
	public void enterPassword(DataTable table) throws Throwable {
		java.util.List<java.util.List<String>> data = table.cells();
		driver.findElement(By.id("userName")).sendKeys(data.get(1).get(1));
		driver.findElement(By.id("loginPassword")).sendKeys(data.get(2).get(1));
		driver.findElement(By.id("loginbtn")).click();
	}

	@When("^I click element with id \"([^\"]*)\"$")
	public void clickElement(String link) throws Throwable {
		WebElement element = waits.explicitWait(link);
		if (waits.isInteractable(element)) {
			element.click();
		} else {
			System.out.println("Element is not interactable");
		}
	}

	@When("^I select option \"([^\"]*)\" from dropdown \"([^\"]*)\"$")
	public void selectOptionFromDropdown(String dropItem, String dropDown) throws Throwable {
		Select dropDownList = new Select(driver.findElement(By.id(dropDown)));
		List<WebElement> options = dropDownList.getOptions();
		driver.findElement(By.id(dropDown)).click();

		for (WebElement option : options) {
			if (option.getAttribute("value").equals(dropItem)) {
				option.click();
				break;
			}
		}
		// dropDownList.selectByValue(dropItem);
	}

	@When("^I enter text \"([^\"]*)\" into field with id \"([^\"]*)\"$")
	public void enterTextIntoField(String text, String link) throws Throwable {
		WebElement element = waits.explicitWait(link);
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	@When("^I navigate back$")
	public void navigateBack() throws Throwable {
		driver.navigate().back();
	}

	@When("^I navigate forward$")
	public void navigateForward() throws Throwable {
		driver.navigate().forward();
	}

	@When("^I refresh page$")
	public void refreshPage() throws Throwable {
		driver.navigate().refresh();
	}

	@When("^I switch to browser tab with title \"([^\"]*)\"$")
	public void switchTab(String title) throws Throwable {
		String newWindow = title;
		String currentWindow = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(winHandle).getTitle().equals(newWindow)) {
				break;
			} else
				driver.switchTo().window(currentWindow);
		}
	}

	@When("^I scroll to element with id \"([^\"]*)\"$")
	public void scrollTo(String id) throws Throwable {
		WebElement element = waits.explicitWait(id);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		Thread.sleep(1000);
		/*
		 * scrollIntoView() - pass in true if object is below you; false if object is
		 * above you
		 */
	}

	@When("^I log in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void loginPagePom(String arg1, String arg2) throws Throwable {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(arg1, arg2);
	}

}
