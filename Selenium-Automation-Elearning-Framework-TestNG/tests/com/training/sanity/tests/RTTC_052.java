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
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_052POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_052 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_052POM createCustomersGroup;
	private RTTC_051POM login;
	private static Properties properties;
	//private String customerGrp = "Ket1234";
	private ScreenShot sh;
	private String name="Asmita";
	private String lastName;
	@BeforeSuite
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		login = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		login.loginAdmin("admin", "admin@123");
		createCustomersGroup = new RTTC_052POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	// Admin logins the Retail website and navigates to Dashboard
	@Test(priority = 1)
	public void displayCustomersOptions() {
		boolean expectedOptions = true;
		boolean actualOptions = createCustomersGroup.displayCustomersOptions();
		assertEquals(actualOptions, expectedOptions);

	}
	// click on Customers link and navigate to Customer Groups page
	@Test(priority = 2)
	public void navigateToCustomerGroupsPage() {
		createCustomersGroup.navigateToCustomersPage();

		String expectedPageTitle = "Customers";
		String actualPageTitle = driver.getTitle();
		assertEquals(actualPageTitle, expectedPageTitle);

	}
	//search customer to be edited
	@Test(priority = 3)
	public void searchCustomerToEdit() {

		String expectedCustomerName = "Mandar P"; 
		//saving the lastname in lastName for further use
		lastName=expectedCustomerName.substring(expectedCustomerName.indexOf(" ")+1);// use 'ketu b' or 'alka b' as other options for testing

		String actualCustomerName = createCustomersGroup.selectCustomerBySearch(expectedCustomerName);
		System.out.println("Selected Customer name= " + actualCustomerName);
		assertEquals(actualCustomerName, expectedCustomerName);
	}
	//navigate to Edit Customer page
	@Test(priority = 4)
	public void clickEditButton() {
		String expectedCustomerName = "Edit Customer"; // use 'ketu b' or 'alka b' as other options for testing
		String actualCustomerName = createCustomersGroup.clickEditIcon();
		assertEquals(actualCustomerName, expectedCustomerName);
	}
	//edit the field values, add Reward points and save the changes
	@Test(priority = 5)
	public void editFields() {
		String expectedCustomerName = "Success: You have modified customers!"; // use 'ketu b' or 'alka b' as other
		// options for testing
		String actualCustomerName = createCustomersGroup.editFieldValues(name);
		boolean expected = actualCustomerName.contains(expectedCustomerName);
		assertEquals(expected, true);
	}
	//display all option available under Reports
	@Test(priority = 6)
	public void displayReportsOptions() {
		boolean expected = true;
		boolean actual = createCustomersGroup.displayReportsList();
		assertEquals(actual, expected);
	}
	//display all options available under Reports->Customers	
	@Test(priority=7)
	public void displayReportsCustomersOptions() {

		boolean expected=true;
		boolean actual=createCustomersGroup.displayReportCustomersList();
		assertEquals(actual, expected);
	}
	//click on Reward Points link and navigate to Returns Report page
	@Test(priority=8)
	public void navigateToRewardPointsPage() {

		String expectedPageTitle="Customer Reward Points Report";
		String actualPageTitle=createCustomersGroup.navigateToRewardPoints();
		assertEquals(actualPageTitle, expectedPageTitle);
	}
	//search the customer for which reward points have been added
	@Test(priority = 9)
	public void searchCustomerWithRewardPoints() {

		String expectedCustomerName = name + " " + lastName; // use 'ketu b' or 'alka b' as other options for testing
		String actualCustomerName = createCustomersGroup.selectCustomerToSearchRewardPoints(expectedCustomerName);
		System.out.println("Selected Customer name= " + actualCustomerName);
		assertEquals(actualCustomerName, expectedCustomerName);
	}
	//display the reward points
	@Test(priority = 10)
	public void confirmRewardPoints() {

		boolean expected = true; // use 'ketu b' or 'alka b' as other options for testing
		String customerRewardPoints = createCustomersGroup.confirmRewardPoints();
		System.out.println("Customer reward points= " + customerRewardPoints);
		boolean actual=(Integer.parseInt(customerRewardPoints))>50?true:false;
		assertEquals(actual, expected);
	}

}
