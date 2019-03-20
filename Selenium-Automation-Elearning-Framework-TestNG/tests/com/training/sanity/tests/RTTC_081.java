package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_081POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_081 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_081POM returnProduct;
	private static Properties properties;
	String name="manzoor";

	@BeforeSuite
	public void setUp() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		login = new RTTC_051POM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
		login.loginAdmin("admin", "admin@123");
		returnProduct = new RTTC_081POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void returnProduct() throws InterruptedException {
		String orderID="102";
		returnProduct.showSalesOptionsAndNavigatetoReturns();
		returnProduct.returnOrder(orderID);
		returnProduct.deleteFromReturnsList(orderID);
		/*
		returnProduct.verifyInDatabase();
		*/
	}
}