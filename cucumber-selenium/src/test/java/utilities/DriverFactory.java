package utilities;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import static utilities.BrowserSwitches.*;

public class DriverFactory {

	private static final CukeLogs cukeLogs = CukeLogs.getLogger("DriverFactory");
	
	@SuppressWarnings("deprecation")
	public static WebDriver createDriver(String browserOption) throws Exception {
		cukeLogs.info("Creating WebDriver in DriverFactory Class\r\n");
		switch (browserOption.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/drivers/chromedriver");
			return new ChromeDriver(getChromeCapabilities());
		case "firefox":
			/* "webdriver.firefox.marionette" */
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/drivers/geckodriver");
			return new FirefoxDriver(getFirefoxCapabilities());
		case "grid":
			return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getGridCapabilities());
		default:
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/drivers/chromedriver");
			return new ChromeDriver(getChromeCapabilities());
		}
	}
	
	private static DesiredCapabilities getGridCapabilities() {
		DesiredCapabilities gridCapability = DesiredCapabilities.chrome();
		gridCapability.setBrowserName("chrome");
		return gridCapability;
	}

	private static DesiredCapabilities getChromeCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
		return capabilities;
	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(getChromeSwitches());
		options.setExperimentalOption(PREFS, getChromePrefs());
		options.setExperimentalOption(MOBILE, getMobileEmulation());
		return options;
	}

	private static List<String> getChromeSwitches() {
		List<String> chromeSwitches = new ArrayList<>();
		/*chromeSwitches.add(INCOGNITO);*/
		chromeSwitches.add(ALLOW_INSECURE_CONTENT);
		chromeSwitches.add(IGNORE_CERTIFICATE_ERRORS);
		chromeSwitches.add(INFO_BARS);
		chromeSwitches.add(GPU);
		chromeSwitches.add(PLUGINS);
		chromeSwitches.add(EXTENSIONS);
		chromeSwitches.add(NO_SANDBOX);
		chromeSwitches.add(SHM);
		/*chromeSwitches.add(START_FULLSCREEN);*/
		if (Settings.HEADLESS.getValue().equalsIgnoreCase("on")) {
			chromeSwitches.add(HEADLESS);
		}
		return chromeSwitches;
	}

	private static Map<String, Object> getChromePrefs() {
		Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put(BROWSER_NOTIFICATIONS, DISABLED);
		return chromePrefs;
	}

	private static Map<String, String> getMobileEmulation() {
		Map<String, String> mobileEmulation = new HashMap<>();
		if (Settings.MOBILE.getValue().equalsIgnoreCase("android")) {
			mobileEmulation.put(DEVICE_NAME, ANDROID);
		} else if(Settings.MOBILE.getValue().equalsIgnoreCase("iphone")) {
			mobileEmulation.put(DEVICE_NAME, IPHONE);
		}
		return mobileEmulation;
	}

	private static DesiredCapabilities getFirefoxCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setAcceptInsecureCerts(true);
		capabilities.setCapability(MARIONETTE, true);
		/*capabilities.setCapability(HOMEPAGE_KEY, HOMEPAGE_VALUE);*/
		capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxOptions());
		return capabilities;
	}

	@SuppressWarnings("deprecation")
	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.setLogLevel(Level.WARNING);
		options.setBinary(binaryOptions());
		return options;
	}
	
	private static FirefoxBinary binaryOptions() {
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		if (Settings.HEADLESS.getValue().equalsIgnoreCase("on")) {
			firefoxBinary.addCommandLineOptions("--headless");
		}
		return firefoxBinary;
	}
}
