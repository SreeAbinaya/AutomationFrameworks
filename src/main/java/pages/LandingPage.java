package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.UtilityComponents;

public class LandingPage extends UtilityComponents {
	
	WebDriver driver;
	String createProjectXpath = "//span[text() = 'Create Project']";
	String projectTitle = "//div[contains(@class,'MuiBox-root')]//h3";
	String notificationXpath = "//span[@aria-haspopup='true']";

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
	}
	
	public void createNewProject() {
		
		WebElement createProject = driver.findElement(By.xpath(createProjectXpath));
		waitForWebElementsToAppear(createProject);
		createProject.click();
		
	}
	
	public String getProjectTitle() {
		WebElement projtitle = driver.findElement(By.xpath(projectTitle));
		waitForWebElementsToAppear(projtitle);
		String title = projtitle.getText();
		return title;
	}
	
	public void verifyNotificationAppears() {
		WebElement notif= driver.findElement(By.xpath(notificationXpath));
		waitForWebElementsToAppear(notif);
		String noOfNotifs = notif.getText();
	}
	
	public void clickNotifications() {
		WebElement notif= driver.findElement(By.xpath(notificationXpath));
		notif.click();
		
	}

}
