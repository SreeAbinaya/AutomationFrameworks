package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.UtilityComponents;

public class LoginPage extends UtilityComponents {
	
	WebDriver driver;
	
	@FindBy(css="[name='email']") WebElement username;
	@FindBy(css="[name='password']") WebElement password;
	@FindBy(css="[name='submit']") WebElement signIn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUserName(String uname) {
		username.sendKeys(uname);
	}
	
	public void enterPassword(String pswd) {
		password.sendKeys(pswd);
	}
	
	public void clickLogin() {
		signIn.click();

	}
	
	
	
	

}
