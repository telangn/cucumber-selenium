package utilities;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaits {

	WebDriver driver;

	public CustomWaits(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement explicitWait(String id) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		} catch (NullPointerException | TimeoutException e) {
			e.printStackTrace();
		}
		return element;
	}

	@SuppressWarnings("deprecation")
	public WebElement fluentWait(String id) {
		WebElement element = null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(id));
			}
		});
		return element;
	}

	public boolean isInteractable(WebElement element) {
		return element != null && element.isDisplayed() && element.isEnabled();
	}

	public boolean waitForJStoLoad() {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active) == 0;");
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoaded = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return wait.until(jQueryLoaded) && wait.until(jsLoaded);
	}
}
