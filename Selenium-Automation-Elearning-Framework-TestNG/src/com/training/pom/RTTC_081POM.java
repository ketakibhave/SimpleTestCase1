package com.training.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RTTC_081POM {
private WebDriver driver;
	
	public RTTC_081POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//navigate to Returns page--	
	@FindBy(xpath = "//nav/ul/li[6]/a")
	WebElement salesOptions;
	
	@FindBy(xpath = "//nav/ul/li[6]/ul/li[3]")
	WebElement returnsLink;
	
	@FindBy(xpath = "//div/div/div/div/a")
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
	
	@FindBy(xpath = "//div/div/div/button")
	WebElement deleteIcon;
	
	public void showSalesOptionsAndNavigatetoReturns() {
		Actions act= new Actions(driver);
		act.moveToElement(salesOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[6]/ul/li")).size();
		System.out.println("Menu-Sale options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[6]/ul/li["+i+"]/a")).getText());
		}
		returnsLink.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		System.out.println("=================================");
	}

	public void returnOrder(String orderid) {
		addNewBtn.click();
		orderID.sendKeys(orderid);
		customerName.clear();
		customerName.sendKeys("alka b");
		firstName.click();
		firstName.clear();
		firstName.sendKeys("alka");
		lastName.clear();
		lastName.sendKeys("b");
		email.clear();
		email.sendKeys("alkab@gmail.com");
		telephone.clear();
		telephone.sendKeys("222520");
		product.sendKeys("Finger Ring");
		model.sendKeys("SKU-012");
		saveBtn.click();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified returns!";
		System.out.println("Success Message for Return the product is as expected? "+successMsg.contains(expectedMsg));
	}

	public void deleteFromReturnsList(String orderid) throws InterruptedException {
		String toFind="//*[contains(text(),'"+orderid+"')]/parent::tr/td[1]";
		WebElement orderInTable=driver.findElement(By.xpath(toFind));
		orderInTable.click();
		Thread.sleep(3000);
		deleteIcon.click();
		Thread.sleep(3000);
		Alert alert= driver.switchTo().alert();
		alert.accept();
		String successMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMsg="Success: You have modified returns!";
		System.out.println("Success Message for Delete as expected? "+successMsg.contains(expectedMsg));
	}

	public void verifyInDatabase() {
		// TODO Auto-generated method stub
		
	}
}
