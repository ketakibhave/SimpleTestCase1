package com.training.sanity.tests;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_083POM;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_083 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_083POM invalidDetailsTest;
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
		invalidDetailsTest = new RTTC_083POM(driver);
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority=1)
	public void showOptions()
	{
		invalidDetailsTest.showSalesOptions();
	}
	@Test(priority=2, dataProvider = "getExcelData", dataProviderClass = RTTC_082.class)
	public void invalidDetailsReturn(String orderid1, String customer, String first, String last, String emailid, String phone, String prodName, String modelName) throws InterruptedException, IOException, AWTException
	{
		String expectedMsg="Warning: Please check the form carefully for errors!";
		String actualMsg=invalidDetailsTest.returnOrder(orderid1, customer, first, last, emailid, phone, prodName, modelName);
		boolean actual= actualMsg.contains(expectedMsg);
		ScreenShot ss=new ScreenShot(driver);
		ss.captureScreenShot("RTTC83");
		Assert.assertEquals(actual, true);
	}
	
	@DataProvider
	public Object[][] getExcelData(){
		System.out.println("getExcel from 83");
		String fileName ="C:\\Automation training\\Project\\Testing2.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName, 2); 
	}
}
