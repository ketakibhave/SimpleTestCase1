package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC025POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC025 {
	WebDriver driver;
	private String baseUrl;
	private RTTC025POM filterReturnsList;
	private static Properties properties;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	//Admin logins the Retail website and navigates to Dashboard
	@Test(priority=1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterReturnsList=new RTTC025POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		filterReturnsList.sendUserName("admin");
		filterReturnsList.sendPassword("admin@123");
		filterReturnsList.clickLoginBtn();
		String expectedPageTitle="Dashboard";
		String actualPageTitle=driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display the SubOptions available under Reports Option
	@Test(priority=2)
	public void displayReportsOptions() throws InterruptedException {
		boolean expected=true;
		boolean actual=filterReturnsList.displayReportsList();
		assertEquals(actual, expected);
	}
	//Display the SubOptions available under Sales Option
	@Test(priority=3)
	public void displaySalesOptions() {
		
		boolean expected=true;
		boolean actual=filterReturnsList.displaySalesList();
		assertEquals(actual, expected);
	}
	//click on Returns link and navigate to Returns Report page
	@Test(priority=4)
	public void navigateToReturnsPage() {
		
		String expectedPageTitle="Returns Report";
		String actualPageTitle=filterReturnsList.navigateToReturns();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display options available under GroupBy dropdown
	@Test(priority=5)
	public void displayGroupByListBox() {
		
		boolean expected=true;
		boolean actual=filterReturnsList.displayGroupByListboxOptions();
		assertEquals(actual, expected);
	}
	//Filter the results using the option "week"
	@Test(priority=6)
	public void filterReturnsDetails() {
		
		String expectedTitle="No. Returns";
		String actualTitle=filterReturnsList.filterListbyGroup();
		assertEquals(actualTitle, expectedTitle);
		ScreenShot ss=new ScreenShot(driver);
		ss.captureScreenShot("TC025");
	}
	
}

