package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.pom.RTTC_021POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_021 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_021POM deleteCustomerPOM;
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
		deleteCustomerPOM = new RTTC_021POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		deleteCustomerPOM.sendUserName("admin");
		deleteCustomerPOM.sendPassword("admin@123");
		deleteCustomerPOM.clickLoginBtn();
		String expectedPageTitle = "Dashboard";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//Display the SubOptions available under Customers Option
	@Test(priority = 2)
	public void displayCustomersOptions() {
		boolean expectedOptions = true;
		boolean actualOptions = deleteCustomerPOM.displayCustomersOptions();
		assertEquals(actualOptions, expectedOptions);

	}
	//click on Customers link and navigate to Customers page
	@Test(priority = 3)
	public void navigateToCustomerPage() {
		deleteCustomerPOM.navigateToCustomersPage();

		String expectedPageTitle = "Customers";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);

	}
	//Search the name of Customer to be deleted
	@Test(priority = 4)
	public void searchCustomerToDelete() {

		String expectedCustomerName = "komu b";	//use 'ketu b' or 'alka b' as other options for testing
		String actualCustomerName = deleteCustomerPOM.selectCustomerBySearch(expectedCustomerName);
		System.out.println("Selected Customer name= " + actualCustomerName);
		assertEquals(actualCustomerName, expectedCustomerName);
	}
	//delete the Customer
	@Test(priority = 5, dependsOnMethods = "searchCustomerToDelete")
	public void deleteCustomer() throws InterruptedException {
		String expectedDisplay = "No results!";
		String actualDisplay = deleteCustomerPOM.deleteCustomerRecord();
		assertEquals(expectedDisplay, actualDisplay);
	}

}
