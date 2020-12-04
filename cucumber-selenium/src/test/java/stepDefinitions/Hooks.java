package stepDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.CukeLogs;
import utilities.DIService;

public class Hooks {
	
	CukeLogs cukelogs; 
	WebDriver driver;
	
	public Hooks(DIService service) throws Exception {
		this.driver = service.getDriver();
		this.cukelogs = CukeLogs.getLogger("Hooks");
	}
		
	@Before("@setup")
	public void setUp() {
		/*driver.manage().deleteAllCookies();*/
		/*driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		cukelogs.info("Executed Before Hook -- Tag: Setup\r\n");
	}

	@After
	public void snapShotOnFail(Scenario scenario) throws Throwable {
		try {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
		cukelogs.info("Executed After Hook -- Snap shot on Failed Scenarios\r\n");
	}

	@After("@destroy")
	public void tearDown() throws Throwable {
		driver.quit();
		cukelogs.info("Executed After Hook -- Tag: Destroy\r\n");
	}
}
