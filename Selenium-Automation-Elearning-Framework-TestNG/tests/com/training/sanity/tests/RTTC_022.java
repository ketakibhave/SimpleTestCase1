package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_022POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_022 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_022POM filterSalesList;
	private static Properties properties;

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	//Admin logins the Retail website and navigates to Dashboard
	@Test(priority = 1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterSalesList = new RTTC_022POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		filterSalesList.sendUserName("admin");
		filterSalesList.sendPassword("admin@123");
		filterSalesList.clickLoginBtn();
		String expectedPageTitle = "Dashboard";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display the SubOptions available under Reports Option
	@Test(priority = 2)
	public void displayReportsOptions() throws InterruptedException {
		

		boolean expected = true;
		boolean actual = filterSalesList.displayReportsList();
		assertEquals(actual, expected);
	}
	//Display the SubOptions available under Sales Option
	@Test(priority = 3)
	public void displaySalesOptions() {

		boolean expected = true;
		boolean actual = filterSalesList.displaySalesList();
		assertEquals(actual, expected);
	}
	//click on Sales link and navigate to Sales Report page
	@Test(priority = 4)
	public void navigateToOrdersPage() {

		String expectedPageTitle = "Sales Report";
		String actualPageTitle = filterSalesList.navigateToOrders();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display options available under GroupBy dropdown
	@Test(priority = 5)
	public void displayGroupByListBox() {

		boolean expected = true;
		boolean actual = filterSalesList.displayGroupByListboxOptions();
		assertEquals(actual, expected);
	}
	//Filter the results using the option "week"
	@Test(priority = 6)
	public void filterOrderDetails() {

		String expectedTitle = "Total";
		String actualTitle = filterSalesList.filterListbyGroup();
		assertEquals(actualTitle, expectedTitle);
		ScreenShot ss=new ScreenShot(driver);
		ss.captureScreenShot("TC022");
	}

}
