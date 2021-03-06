package com.training.generics;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


/**
 * 
 * @author Naveen
 * @see in this class the path for screenshot is hard coded, please refer to others.properties file 
 *   the entry is kept, and this path shall be able to change from properties file 
 */
public class ScreenShot {

	private WebDriver driver; 
	
	// the driver information will be given by selenium test case 
	public ScreenShot(WebDriver driver){
		this.driver = driver; 
	}
	
	public void captureScreenShot(){
		
		// to be changed 
		String path = "C:\\Automation training\\Project\\Screenshots\\";
		String fileName ="";

		//GregorianCalendar calendar = new GregorianCalendar(); 
		
		//int date =  calendar.get(Calendar.DATE); 
		//int minute = calendar.get(Calendar.MINUTE);
		//int second = calendar.get(Calendar.SECOND); 
		
		//int dateout= calendar.get(Calendar.DATE);
		Date now= new Date();
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("dd-MM-yy_HH_MM_SS");
		/*
		System.out.println(dateFormat.format(now));
		
		fileName = new Integer(date).toString() + "-" + new Integer(minute).toString() +"-" +
					new Integer(second).toString() +".png"; 
		*/
		fileName= dateFormat.format(now)+"-test"+".png";
		
		// 1. create file 
		// 2. capture screenshot from selenium 
		// 3. store it in physical driver 
		
		
		try {
			/*
			File outputFile;
			String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	        outputFile = new File(timeStamp);
	        outputFile.mkdir();
	        System.out.println(outputFile.getCanonicalPath());
	        */
			TakesScreenshot takeScreenShot  = (TakesScreenshot) driver; 
			File file = takeScreenShot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(path +fileName));
		//System.out.println("Path & fileName==>"+path+fileName);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	public void captureScreenShot(String fileName1) {
		
		String path =  "C:\\Automation training\\Project\\Screenshots\\";
	
		// 1. create file 
		// 2. capture screenshot from selenium 
		// 3. store it in physical driver 
		Date now= new Date();
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("dd-MM-yy_HH_MM_SS");
		
		try {
			TakesScreenshot takeScreenShot  = (TakesScreenshot) driver; 
			File file = takeScreenShot.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(file, new File(path +fileName1+"_" +dateFormat.format(now)+".png"));
		} 
		catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
