package pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "userName")
	WebElement usernameBox;
	@FindBy(id = "loginPassword")
	WebElement passwordBox;
	@FindBy(id = "loginbtn")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void insertUserName(String username) {
		usernameBox.sendKeys(username);
	}

	public void insertPassword(String password) {
		passwordBox.sendKeys(password);
	}

	public void clickSubmit() {
		loginBtn.click();
	}

	public void login(String username, String password) {
		insertUserName(username);
		insertPassword(password);
		clickSubmit();
	}

}
