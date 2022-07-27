package com.OrangeHRM.util;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebListener implements WebDriverEventListener{
	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Value of the:"+element.toString()+"Before any changes made");
		//log.debug("Value of the:\"+element.toString()+\"Before any changes made");
		
	}
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Element Value Changed To:"+element.toString());
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on Element:"+element.toString()+" ");		
		
	}


	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on:"+element.toString());
		
	}
	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying To Find Element By : "+by.toString()+"");		
		
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : "+by.toString());		
		
	}


	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating Back to the previous page");		
		
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
        System.out.println("Navigated Back to the previous page");		
		
	}
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before NavigatingTo:"+url+"");
		
	}
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("Navigated To:"+url+"");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating Back to the Next page");		
		
	}


	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated Forward( to the Next page");		
		
	}


	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Trying To Switch To Window : "+ windowName);		
		
	}
	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Switching to Window"+windowName.toString()+"");		
		
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		System.out.println("Exception Occure: May be [Can not find Element] Or[ something else .. Ivestigate!] "+ throwable);		
		
	}
	

}
