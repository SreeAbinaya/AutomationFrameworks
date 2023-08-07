package utilityTests;

import org.testng.annotations.AfterMethod;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTests extends Initializations {
	
public WebDriver driver;
	
	@BeforeMethod
	public void launchWebsite() throws IOException {
		String browserName = fetchBrowserName();
		driver = intializeBrowser();	
		urlLaunch();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
