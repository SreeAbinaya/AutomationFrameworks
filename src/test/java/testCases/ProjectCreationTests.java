package testCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import pages.CreateProjectModule;
import pages.LandingPage;
import pages.LoginPage;
import pages.NotificationModule;
import utility.DataReader;
import utilityTests.BaseTests;

public class ProjectCreationTests extends BaseTests {
	
	@DataProvider
	public Object[][] getCreateProjectData() throws IOException {
		DataReader data = new DataReader();
		List<HashMap<String, String>> userdata =data.readDataFromJson();
		return new Object[][] {{userdata.get(0)}};
	}
	
	@Test(dataProvider="getCreateProjectData")
	public void createaNewProject(HashMap<String,String> input) throws IOException {
		String projectTitle = input.get("projectTitle");
		LoginPage login = loginPage();
		login.enterUserName(input.get("username"));
		login.enterPassword(input.get("password"));
		login.clickLogin();
		LandingPage landing = landingPage();
		landing.createNewProject();
		CreateProjectModule project = createProjectModule();
		project.verifyDialogBoxAppears();
		project.clickCheckBox("mark_as_public");
		project.clickNextButton();
		project.enterProjectName(projectTitle);
		project.chooseProjectManager();
		project.clickSubmit();
		String title = landing.getProjectTitle();
		Assert.assertEquals(title, projectTitle);
	}
	
	@DataProvider
	public Object[][] viewProjectData() throws IOException, InvalidFormatException {
		DataReader data = new DataReader();
		Object[][] userdata =data.readDataFromExcel();
		return new Object[][] {userdata[0]};
	}
	
	
	@Test(dataProvider="viewProjectData")
	public void verifyProjectManagerReceivedNotification(HashMap<String, String> input) {
		LoginPage login = loginPage();
		login.enterUserName(input.get("username"));
		login.enterPassword(input.get("paswword"));
		login.clickLogin();
		LandingPage landing = landingPage();
		landing.verifyNotificationAppears();
		landing.clickNotifications();
		NotificationModule notif = notifModule();
		String title = notif.getNotificationTitle();
		Assert.assertEquals(title, input.get("projectTitle"));
	}
	
	@Test
	public void verifyProjectMangerCanViewProjectInDashboard() {
		String projectTitle = "New Project 12";
		LoginPage login = loginPage();
		login.enterUserName("rashokciet@gmail.com");
		login.enterPassword("@rjuN006");
		login.clickLogin();
		LandingPage landing = landingPage();
		landing.clickNotifications();
		NotificationModule notif = notifModule();
		notif.clickNotification();
		String title = landing.getProjectTitle();
		Assert.assertEquals(title, projectTitle);
	}

}
