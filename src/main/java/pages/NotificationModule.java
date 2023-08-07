package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.UtilityComponents;

public class NotificationModule extends UtilityComponents {
	
	WebDriver driver;
	
	public NotificationModule(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	String notificationXpath = "//div[@data-test-id='layout-wrapper']//div[@data-test-id='notification-content']";

	public String getNotificationTitle() {
		WebElement notif = driver.findElement(By.xpath(notificationXpath));
		String text = notif.getText();
		String[] projTitle = text.split("-");
		String title = projTitle[1].trim();
		return title;
	}
	
	public void clickNotification() {
		WebElement notif = driver.findElement(By.xpath(notificationXpath));
		notif.click();
	}
}
