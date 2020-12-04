package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import cucumber.api.java.en.Given;
import junit.framework.Assert;
import utilities.CukeLogs;
import utilities.CustomWaits;
import utilities.DIService;

@SuppressWarnings("deprecation")
public class GivenSteps {

	CukeLogs cukelogs;
	WebDriver driver;
	CustomWaits waits;

	public GivenSteps(DIService service) throws Exception {
		this.driver = service.getDriver();
		this.waits = service.getWaits();
		this.cukelogs = CukeLogs.getLogger("StepDefinitions");
	}

	@Given("^I am on the \"([^\"]*)\" Website$")
	public void navigateToWebsite(String URL) throws Throwable {
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		cukelogs.info("Executed Browser Launch\r\n");
	}

	@Given("^I am on the \"([^\"]*)\" page$")
	public void iAmOnThePage(String arg) throws Throwable {
		String URL = "https://www.google.com";
		String newBaseURL = URL + arg;
		String currentURL = driver.getCurrentUrl();
		if (!(newBaseURL.equals(currentURL))) {
			driver.navigate().to(newBaseURL);
		}
		Assert.assertEquals(newBaseURL, driver.getCurrentUrl());
	}

	@Given("^element with id \"([^\"]*)\" is displayed$")
	public void elementIsDisplayed(String link) throws Throwable {
		WebElement element = waits.explicitWait(link);
		assertTrue(element!=null);
	}
	
	@Given("^I drag element \"([^\"]*)\" to location \"([^\"]*)\"$")
	public void dragAndDrop(String arg1, String arg2) throws Throwable {
	    WebElement element = driver.findElement(By.id(arg1));
	    WebElement target = driver.findElement(By.id(arg2));
	    (new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	@Given("^I switch to alert$")
	public void switchToAlert() throws Throwable {
		driver.switchTo().alert();
	}
	
	@Given("^The cookie \"([^\"]*)\" exists$")
	public void checkCookie(String cookie) throws Throwable {
		Cookie getCookie = driver.manage().getCookieNamed(cookie);
		Assert.assertNotNull(getCookie);
	}
	
	@Given("^The text \"([^\"]*)\" exists in the page source$")
	public void findInPageSource(String text) throws Throwable {
		boolean status = driver.getPageSource().contains(text);
		Assert.assertTrue(status);
	}
	
	@Given("^I switch to iframe with element id \"([^\"]*)\"$")
	public void iFrame(String text) throws Throwable{
		WebElement iframe = driver.findElement(By.id(text));
		driver.switchTo().frame(iframe);
	}
	
	@Given("^I switch out of iframe$")
	public void iframeOut() throws Throwable {
		driver.switchTo().defaultContent();
	}
}
