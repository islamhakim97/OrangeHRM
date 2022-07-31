package com.OrangeHRM.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

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

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPageTest extends testBase {

	public LoginPageTest() throws IOException {
		super();

	}

	LoginPage loginPage;
	HomePage homePage;
	String date;

	@Parameters({ "Browser" })
	@BeforeMethod(groups= {"E2E","Regression"})
	public void setup(Method method, String browser) throws IOException, ATUTestRecorderException

	{
		initialization(browser);
		loginPage = new LoginPage();// after you initialize your driver
		date=TestUtils.TCstime();
		String name =method.getName()+date;//Assign DAte and Time To videoname
		System.out.println(name);

		// Start Take Video :
		TestUtils.StartTakeVideo(name);
		loginPage.getMainPage();
		// Log TCs Name To Report
		System.out.println(name);
		TestUtils.LogTCsNamesToREport(name);
		System.out.println(name);

	}

	@AfterMethod(groups= {"E2E","Regression"})
	public void tearDown(Method method, ITestResult result) throws IOException, ATUTestRecorderException // ITestResult is TestNG listener to log test																										// status[pass|fail|skipped]

	{
		//String date=TestUtils.TCstime();
		String name =method.getName()+date;//Assign Date and Time To picName
		System.out.println(name);
		//Take SnapShot:
		TestUtils.TakePicture(name);
		//Stop Video
		TestUtils.Recorder.stop();
		//Log Test Status to the Report
		TestUtils.LogTestStatusToExtentReport(result,name);

		driver.quit();

		// driver.close();

	}

	// Login TCs
	@Test(priority = 1,groups= {"E2E","Regression"}) // [1-pass] groups= {"Regression"},alwaysRun = true
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
	@Test(priority=3,groups={"E2E"})
	public void testLogo()
	{
		boolean actualResult=loginPage.checkLogo();
		Assert.assertEquals(actualResult, true, "Logo is not Found");
	}
	@Test(priority=4,groups={"E2E"})
	public void testURL()
	{
		String AR=driver.getCurrentUrl();
		String ER="https://opensource-demo.orangehrmlive.com/";
		Assert.assertEquals(AR, ER, "URL is not Correct");
	}
	@Test(priority=4,groups={"E2E"})
	public void testTitle()
	{
		String AR=driver.getTitle();
		String ER="OrangeHRM";
		Assert.assertEquals(AR, ER, "Title is not Correct");
	}

	@DataProvider(parallel=true)//parallel=true when u use parallel execution with data providers parallel=true
	public Object[][] testLoginData() throws IOException {
		// if you ** change the file name **[don't forget to change the File Path ]in
		// the getDataFromExcel Method
		// AND [pass the correct sheet name to getDataFrom Excel Method]].
		String ExcelSheetname = "Sheet1";// "FbLoginData";//"RegisterDataSheet";
		Object[][] data = TestUtils.getDataFromExcel(ExcelSheetname);
		return data;
	}

}
