package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RTTC_051POM;
import com.training.pom.RTTC_055POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_055 {
	WebDriver driver;
	private String baseUrl;
	private RTTC_051POM login;
	private RTTC_055POM addProduct;
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
		addProduct = new RTTC_055POM(driver);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	// Admin logins the Retail website and adds a product
	@Test
	public void displayOptionsNavigateToOrders() {
		addProduct.showCatalogOptions();
		addProduct.createManufacturer(name);
		String modelName="SKU-012K3";
		addProduct.navigateToProductsAdd(name, modelName);
		String actualModelName= addProduct.searchAddedProduct(modelName);
		
		Assert.assertEquals(actualModelName, modelName);
		
	}
}
