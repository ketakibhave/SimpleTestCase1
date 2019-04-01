package com.training.sanity.tests;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.Toolkit;
import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_082POM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_082 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_082POM returnProduct;
	private static Properties properties;
	String name="manzoor";

	@BeforeTest
	public void setUp() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		login = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		login.loginAdmin("admin", "admin@123");
		returnProduct = new RTTC_082POM(driver);
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority=1)
	public void showOptions()
	{
		returnProduct.showSalesOptions();
	}
	
	@Test(priority=2, dataProvider = "getExcelData", dataProviderClass = RTTC_082.class)
	public void returnProductwithExcel(String orderid1, String customer, String first, String last, String emailid, String phone, String prodName, String modelName) throws InterruptedException, IOException, AWTException
	{
		ScreenShot ss=new ScreenShot(driver);
		
		returnProduct.returnOrder(orderid1, customer, first, last, emailid, phone, prodName, modelName);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
		ss.captureScreenShot("RTTC82Return"+orderid1);
		returnProduct.deleteFromReturnsList(orderid1);
		//take screenshot of alert popup message---
		Date now= new Date();
		SimpleDateFormat dateFormat;
		dateFormat = new SimpleDateFormat("dd-MM-yy_HH_MM_SS");
		Robot robot = new Robot();
		// create rectangle for full screenshot
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		// capture screen of the desktop
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		// save the screenshot to local system
		
		String filePath="C:\\Automation training\\Project\\Screenshots\\RTTC82Delete_"+orderid1+dateFormat.format(now)+".png";
		ImageIO.write(screenFullImage, "png", new File(filePath));
		
		//ss.captureScreenShot("RTTC82Delete"+orderid2);
		returnProduct.deleteConfirm();
		
	}
	
	@DataProvider
	public Object[][] getExcelData(){
		System.out.println("getExcel from 82");
		String fileName ="C:\\Automation training\\Project\\Testing2.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName, 1); 
	}
}
