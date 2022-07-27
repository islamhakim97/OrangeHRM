package com.OrangeHRM.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.com.testBase;

public class ResetPassword extends testBase{
	public ResetPassword() throws IOException
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory Elements
	@FindBy(id="securityAuthentication_userName")
	WebElement HRMUsername;
	@FindBy(id="btnSearchValues")
	WebElement ResetPassBtn;

	
	
   //Page Factory Methods
	public boolean checkEmailISent()
	{
		js.executeScript("arguments[0].style.border='3px solid purple'", HRMUsername);
		HRMUsername.sendKeys("Islam Hakim");
		js.executeScript("arguments[0].style.border='3px solid purple'", ResetPassBtn);
		ResetPassBtn.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return ResetPassBtn.isDisplayed();
     	
	}
}
