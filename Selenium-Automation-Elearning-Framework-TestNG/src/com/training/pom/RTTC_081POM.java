package com.training.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RTTC_081POM {
	private WebDriver driver;

	public RTTC_081POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//navigate to Returns page--	
	//@FindBy(xpath = "//nav/ul/li[6]/a")
	@FindBy(xpath = "//*[@id='menu-sale']/a/i")
	WebElement salesOptions;

	//@FindBy(xpath = "//nav/ul/li[6]/ul/li[3]")
	@FindBy(xpath = "//*[@id='menu-sale']/ul/li[3]/a")
	WebElement returnsLink;

	//@FindBy(xpath = "//div/div/div/div/a")
	@FindBy(xpath = "//*[@id='content']/div[1]/div/div/a")
	WebElement addNewBtn;

	@FindBy(id= "input-order-id")
	WebElement orderID;

	@FindBy(id= "input-customer")
	WebElement customerName;

	@FindBy(id= "input-firstname")
	WebElement firstName;

	@FindBy(id= "input-lastname")
	WebElement lastName;

	@FindBy(id= "input-email")
	WebElement email;

	@FindBy(id= "input-telephone")
	WebElement telephone;

	@FindBy(id= "input-product")
	WebElement product;

	@FindBy(id= "input-model")
	WebElement model;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;

	//back to Returns page to delete record and verify it	
	//@FindBy(xpath = "//div/div/div/button")
	@FindBy(xpath = "//*[@id='content']/div[1]/div/div/button/i")
	WebElement deleteIcon;

	@FindBy(xpath = "//td[contains(text(),'No results!')]")
	WebElement NoRecordsMsg;

	@FindBy(id= "button-filter")
	WebElement btnFilter;

	//methods---	
	public void showSalesOptionsAndNavigatetoReturns() {
		Actions act= new Actions(driver);
		act.moveToElement(salesOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[6]/ul/li")).size();
		System.out.println("Menu-Sale options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[6]/ul/li["+i+"]/a")).getText());
		}
		returnsLink.click();
		System.out.println("=================================");
	}

	public void returnOrderWithDBValues(String orderid1, String customer, String first, String last, String emailid, String phone, String prodName, String modelName) {
		addNewBtn.click();
		orderID.sendKeys(orderid1);
		customerName.clear();
		customerName.sendKeys(customer);
		firstName.click();
		firstName.clear();
		firstName.sendKeys(first);
		lastName.clear();
		lastName.sendKeys(last);
		email.clear();
		email.sendKeys(emailid);
		telephone.clear();
		telephone.sendKeys(phone);
		product.sendKeys(prodName);
		model.sendKeys(modelName);
		saveBtn.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified returns!";
		System.out.println("Success Message for Return the product is as expected? "+successMsg.contains(expectedMsg));
	}

	public void selectFromReturnsList(String orderid) throws InterruptedException {
		orderID.sendKeys(orderid);
		btnFilter.click();
		String toFind="//*[contains(text(),'"+orderid+"')]/parent::tr/td[1]";
		WebElement orderInTable=driver.findElement(By.xpath(toFind));
		orderInTable.click();
		deleteIcon.click();

	}

	public void deleteFromReturnsList(String orderid1) {
		FluentWait<WebDriver> wait= new WebDriverWait(driver, 10).pollingEvery(1, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert= driver.switchTo().alert();
		alert.accept();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified returns!";
		System.out.println("Success Message for Delete as expected? "+successMsg.contains(expectedMsg));

	}
	public String verifyReturnOrderDelete(String orderid1) {
		orderID.clear();
		orderID.sendKeys(orderid1);
		btnFilter.click();
		return NoRecordsMsg.getText();
	}

	public void verifyInDatabase() {
		//Code to send the data to Database

	}

}
