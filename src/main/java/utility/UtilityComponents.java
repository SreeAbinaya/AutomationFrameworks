package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityComponents {
	
	WebDriver driver;
	Properties creteProjectProp;
	
	public Properties createProjectInit() throws IOException {
		creteProjectProp = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//pages_resources//CreateProjectConstants.properties");
		creteProjectProp.load(file);
		return creteProjectProp;
	}
	
	
	public UtilityComponents(WebDriver driver) {
		this.driver = driver;
	}

	
	public void waitForElementsToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementsToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsToDisappear(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(locator));
	}
	
	public WebElement findElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	
	
	

}
