package com.OrangeHRM.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Base.com.testBase;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPageTest extends testBase {

	public LoginPageTest() throws IOException {
		super();

	}

	LoginPage loginPage;
	HomePage homePage;
	 

	@Parameters({ "Browser" })
	@BeforeMethod()
	public void setup(Method method, String browser) throws IOException, ATUTestRecorderException 
																									
																									

	{
		initialization(browser);
		loginPage = new LoginPage();// after you initialize your driver
		// Start Take Video :
		TestUtils.StartTakeVideo(method.getName());
		loginPage.getMainPage();
		// Log TCs Name To Report
		TestUtils.LogTCsNamesToREport(method.getName());

	}

	@AfterMethod()
	public void tearDown(Method method, ITestResult result) throws IOException, ATUTestRecorderException // ITestResult is TestNG listener to log test																										// status[pass|fail|skipped]

	{

		//Take SnapShot:
		TestUtils.TakePicture(method.getName());
		//Stop Video 
		TestUtils.Recorder.stop();
		//Log Test Status to the Report
		TestUtils.LogTestStatusToExtentReport(result);

		driver.quit();

		// driver.close();

	}

	// Login TCs
	@Test(priority = 1) // [1-pass] groups= {"Regression"},alwaysRun = true
	public void PerformValidLoginTest() throws IOException {
		String validUser = prop.getProperty("username");
		String validpass = prop.getProperty("password");
		homePage = loginPage.performValidLogin(validUser, validpass);
		boolean Ar = homePage.DashboardisDisplayed();
		Assert.assertTrue(Ar, "Login Fail , In correct Username Or Password");
	}

	@Test(priority = 2, dataProvider = "testLoginData") // ,dataProvider="testLoginData" [4 Pass]
	public void CheckLoginwithInvalidPasswordOrmail(String mail, String pass) throws IOException// String fname,String
																								// lname
	{
		loginPage.performLoginWithInvalidData(mail, pass);
		boolean actualResult = loginPage.InvalidCredentialsIsDisplayed();
		// ASsert User can't Login with invalid Credentials
		Assert.assertEquals(actualResult, true, "Login Success , With invalid Username Or Password");

	}

	@DataProvider
	public Object[][] testLoginData() throws IOException {
		// if you ** change the file name **[don't forget to change the File Path ]in
		// the getDataFromExcel Method
		// AND [pass the correct sheet name to getDataFrom Excel Method]].
		String ExcelSheetname = "Sheet1";// "FbLoginData";//"RegisterDataSheet";
		Object[][] data = TestUtils.getDataFromExcel(ExcelSheetname);
		return data;
	}

}
