
package utilities;

import org.openqa.selenium.WebDriver;

/*Dependency Injection class */

public class DIService {

	private static String browserType = Settings.BROWSER.getValue();
	private static WebDriver driver = null;
	private static CustomWaits waits = null;

	private final CukeLogs cukeLogs = CukeLogs.getLogger("Dependency Injection");

	public WebDriver getDriver() throws Exception {
		if (driver == null) {
			driver = utilities.DriverFactory.createDriver(browserType);
		}
		cukeLogs.info("WebDriver instance created from Dependency Injection Class\r\n");
		return driver;
	}

	public CustomWaits getWaits() throws Exception {
		if (waits == null) {
			waits = new CustomWaits(getDriver());
		}
		cukeLogs.info("CustomWait instance created from Dependency Injection Class\r\n");
		return waits;
	}
}
