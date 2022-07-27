package com.OrangeHRM.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.Base.com.testBase;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestUtils extends testBase{
	public static ATUTestRecorder Recorder;
	int i =0;
	// For Saving TCsVideoes Names
	
   static Random rand = new Random();

	public TestUtils() throws IOException {
		super();
	}
    //Take SnapShot 
	public static void TakePicture (String name) throws IOException
	{
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		if(name=="CheckLoginwithInvalidPasswordOrmail") // name of TC that use ExelsheetData (not to be overwritten)
		{
			
			int i=rand.nextInt(5) +1; //1->5
			int j=rand.nextInt(6) +1; //1->6
			name+=i+j;
		}
		FileUtils.copyFile(srcFile, new File("D:\\Eclipse\\OrangeHRM2\\Snapshots\\"+name+".png"));
	}
	//Take Video 
	public static void TakeVideo(String videoname) throws ATUTestRecorderException
	{
		if(videoname=="CheckLoginwithInvalidPasswordOrmail") // name of TC that use ExelsheetData (not to be overwritten)
		{

			int i=rand.nextInt(5) +1;
			int j=rand.nextInt(6) +1;
			videoname+=i+j;
		}
		
		Recorder=new ATUTestRecorder("D:\\Eclipse\\OrangeHRM2\\RecordedVideoes",videoname,false);
		
	}

	// Start Take Video
	public static void StartTakeVideo(String videoname) throws ATUTestRecorderException {

		TestUtils.TakeVideo(videoname);
		TestUtils.Recorder.start();

	}
	// log TestCases Names to Report
	public static void LogTCsNamesToREport(String videoname)
	{
		logger = extent.startTest(videoname);
	}
	
	public static void LogTestStatusToExtentReport(ITestResult result)
	{
		// ** Log Test Status to the Report:
				// *** For adding Test Status To the Extent Report:
				if (result.getStatus() == ITestResult.SUCCESS) {
					logger.log(LogStatus.PASS, "Test Pass");
					// Add Snapshots to the Report in case of success
					logger.log(LogStatus.PASS, "<a href='" + result.getName() + ".png"
							+ "'><span class='label info'>Download Snapshot</span></a>");
					// Add Video to the Report
					logger.log(LogStatus.PASS,
							"<a href='" + result.getName() + ".mov" + "'><span class='label info'>Download Video</span></a>");

				} else if (result.getStatus() == ITestResult.FAILURE) {
					logger.log(LogStatus.FAIL, result.getThrowable());
					// Add Snapshots to the Report in case of failure
					logger.log(LogStatus.FAIL, "<a href='" + result.getName() + ".png"
							+ "'><span class='label info'>Download Snapshot</span></a>");
					// Add Video to the Report
					logger.log(LogStatus.PASS,
							"<a href='" + result.getName() + ".mov" + "'><span class='label info'>Download Video</span></a>");

				} else if (result.getStatus() == ITestResult.SKIP) {
					logger.log(LogStatus.SKIP, "Test Skipped");

				}
	}
			
	public static Object[][]getDataFromExcel(String ExcelSheetename) throws IOException
	{
		//"/Volumes/IslamHakim/Data Excel Sheets/LoginData.xlsx"
		File file=new File("D:\\Web Automation Using Selenium\\ExcelDataSheets\\LoginData.xlsx");
		FileInputStream fis2 = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis2);
		
		XSSFSheet sheet=workbook.getSheet(ExcelSheetename);
		int rows=sheet.getLastRowNum(); // num of rows
		int columns = sheet.getRow(0).getLastCellNum(); // num of columns
		Object[][] data = new Object[rows][columns];
			//Get Data from Excel sheet
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				data[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
			
	}


}
