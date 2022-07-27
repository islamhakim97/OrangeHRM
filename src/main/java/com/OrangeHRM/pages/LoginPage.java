package com.OrangeHRM.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jcajce.provider.asymmetric.EC;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.com.testBase;

public class LoginPage extends testBase {

	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver,this);
	}
	//WebElements
    @FindBy(id="txtUsername")
    WebElement Username;
    @FindBy(id="txtPassword")
    WebElement Password;
	@FindBy(name="Submit")
	WebElement LoginBtn;
	@FindBy(xpath="//span[text()='Invalid credentials']")
	WebElement InvalidCredentials;
	// Forget Password Elements
	@FindBy(linkText="Forgot your password?")
	WebElement ForgetPass;
	
	
	

	//Methods
	public void getMainPage()
	{
		driver.get(prop.getProperty("URL")) ;
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


    public HomePage performValidLogin(String mail,String pass) throws IOException
    {
    	
        getMainPage();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		js.executeScript("arguments[0].style.border='3px solid purple'", Username);
		Username.sendKeys(mail);
		js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
		js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();
		
	
		return new HomePage();
    }
    public HomePage performLoginWithInvalidData(String mail,String pass) throws IOException
    {
    	js.executeScript("arguments[0].style.border='3px solid purple'", Username);
		Username.sendKeys(mail);
		js.executeScript("arguments[0].style.border='3px solid purple'", Password);
		Password.sendKeys(pass);
		js.executeScript("arguments[0].style.border='3px solid purple'", LoginBtn);
		LoginBtn.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	return new HomePage();
    }
    public boolean InvalidCredentialsIsDisplayed()
    {
    	
    	return InvalidCredentials.isDisplayed();
    }
    //Forgot password?
    public ResetPassword checkForgotPassword() throws IOException
    {
    	
		js.executeScript("arguments[0].style.border='3px solid purple'", ForgetPass);
		ForgetPass.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return new ResetPassword();
		
    }

    

	

}
