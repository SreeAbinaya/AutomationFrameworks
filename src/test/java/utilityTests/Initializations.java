package utilityTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LandingPage;
import pages.LoginPage;
import pages.NotificationModule;
import pages.CreateProjectModule;


public class Initializations {
	
	Properties prop;
	WebDriver driver;
	
	
	public Properties init() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
		prop.load(file);
		return prop;
	}
	
	
	public String fetchBrowserName() throws IOException {
		init();
		String browsername = prop.getProperty("browser");
		return browsername;
	}
	
	public String fetchURL() throws IOException {
		init();
		String urllink = prop.getProperty("url");
		return urllink;
	}
	
	public WebDriver intializeBrowser() throws IOException {
		String browsername = fetchBrowserName();
		if(browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "/Users/sreeabinaya/Selenium/eclipse-workspace/KyroSeleniumAutomation/drivers/chromedriver");
			//ChromeOptions option = new ChromeOptions();
			//option.setBinary("/Users/sreeabinaya/Downloads/chrome-mac-arm64/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing");
			driver = new ChromeDriver();
		}
		else if(browsername.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.chrome.driver", "/Users/sreeabinaya/Selenium/eclipse-workspace/KyroSeleniumAutomation/drivers/geckodriver");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;
	}
	
	public void urlLaunch() throws IOException {
		String url = fetchURL();
		driver.get(url);
	}
	
	public LoginPage loginPage() {
		return new LoginPage(driver);
	}
	
	public LandingPage landingPage() {
		return new LandingPage(driver);
	}
	
	public CreateProjectModule createProjectModule() throws IOException {
		return new CreateProjectModule(driver);
	}
	
	public NotificationModule notifModule() {
		return new NotificationModule(driver);
	}

}
