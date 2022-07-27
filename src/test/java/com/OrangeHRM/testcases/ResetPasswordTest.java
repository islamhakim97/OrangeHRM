package com.OrangeHRM.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Base.com.testBase;
import com.OrangeHRM.pages.LoginPage;
import com.OrangeHRM.pages.ResetPassword;
import com.OrangeHRM.util.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class ResetPasswordTest extends testBase {

	public ResetPasswordTest() throws IOException {
		super();
	}

	LoginPage loginPage;
	ResetPassword resetPassword;
	
	@Parameters({ "Browser" })
	@BeforeMethod() //alwaysRun = true to run before TCs when Using groups in xml
	public void setup(Method method, String browser) throws IOException, ATUTestRecorderException 
	{
		initialization(browser);
		loginPage = new LoginPage();// after you initialize your driver
		// Start Take Video :
		TestUtils.StartTakeVideo(method.getName());
		// log TestCases Names to Report
		loginPage.getMainPage();
		TestUtils.LogTCsNamesToREport(method.getName());

	}

	@AfterMethod()
	public void tearDown(Method method, ITestResult result) throws IOException, ATUTestRecorderException // ITestResult is TestNG listener to log test status[pass|fail|skipped]
	{

		//  Take SnapShot:
		TestUtils.TakePicture(method.getName());
		TestUtils.Recorder.stop();
		// ** Log Test Status to the Report:
		TestUtils.LogTestStatusToExtentReport(result);
		driver.quit();

		// driver.close();

	}

	// Forgot Password Functionality
	@Test(priority = 1) // [1-Fail]   //groups= {"Regression"}
	public void checkForgotPasswordFunctionality() throws IOException {
		resetPassword = loginPage.checkForgotPassword();
		boolean ar = resetPassword.checkEmailISent();
		Assert.assertEquals(ar, false, "You Can't Set a new Password");

	}

}