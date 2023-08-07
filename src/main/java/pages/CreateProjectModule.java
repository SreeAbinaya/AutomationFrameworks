package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utility.UtilityComponents;

public class CreateProjectModule extends UtilityComponents {

	WebDriver driver ;
	Properties createProjectProp;
	String dialogboxXpath = "//div[contains(@class,'MuiDialog-paperScroll')]";
	String nextButton = "//button[text()='Next']";
	String projectManagerDropdown = "//form//div[contains(@class,'MuiGrid-root')]/div[contains(@class,'MuiAutocomplete-root')]";
	String projManagerDropdownOptions = "//form//div[contains(@class,'MuiGrid-root')]/div[contains(@aria-owns,'listbox')]";
	String projManagerName = "//input[contains(@aria-activedescendant,'0')]";
	String submitXpath = "//button[text()='Submit']";

	public CreateProjectModule(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		
		createProjectProp = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//pages_resources//CreateProjectConstants.properties");
		createProjectProp.load(file);
	}
	
	
	public void verifyDialogBoxAppears() {
		WebElement dialogBox = driver.findElement(By.xpath(dialogboxXpath));
		waitForWebElementsToAppear(dialogBox);
	}
	
	public WebElement fetchCheckBox(String checkboxName) {
		return driver.findElement(By.xpath("//span[text()='"+createProjectProp.getProperty(checkboxName)+"']"));
		
	}
	
	public void clickCheckBox(String checkboxName) {
		WebElement checkbox = fetchCheckBox(checkboxName);
		checkbox.click();
	}
	
	public void clickNextButton() {
		WebElement nextbutton = driver.findElement(By.xpath(nextButton));
		nextbutton.click();
	}
	
	public void enterProjectName(String projectTitle) {
		WebElement projectNameTextBox = driver.findElement(By.name("project_name"));
		projectNameTextBox.sendKeys(projectTitle);
	}
	
	public void enterProjectDescription(String projectDescription) {
		
		WebElement projectNameTextBox = driver.findElement(By.name("description"));
		Actions a = new Actions(driver);
		a.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
		a.sendKeys(Keys.ENTER).build().perform();
		projectNameTextBox.sendKeys(projectDescription);
	}
	
	public void chooseProjectManager() {
		WebElement dropdown = driver.findElement(By.xpath(projectManagerDropdown));
		dropdown.click();
		WebElement dropdownOptions = driver.findElement(By.xpath(projManagerDropdownOptions));
		waitForWebElementsToAppear(dropdownOptions);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		a.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void clickSubmit() {
		WebElement submit = driver.findElement(By.xpath(submitXpath));
		submit.click();
	}
	
	

}
