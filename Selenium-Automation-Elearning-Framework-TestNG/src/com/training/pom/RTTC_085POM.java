package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RTTC_085POM {
	private WebDriver driver;
	public RTTC_085POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//nav/ul/li[6]/a")
	WebElement salesOptions;

	@FindBy(xpath = "//nav/ul/li[6]/ul/li[1]/a")
	WebElement ordersLink;
	
	@FindBy(id = "input-order-id")
	WebElement orderID;
	
	@FindBy(id = "input-customer")
	WebElement customer;
	
	@FindBy(id = "input-total")
	WebElement total;
	
	@FindBy(id = "input-date-added")
	WebElement addDate;
	
	@FindBy(id = "input-date-modified")
	WebElement modifyDate;
	
	@FindBy(xpath = "//i[@class='fa fa-filter']")
	WebElement filterIcon;
	
	@FindBy(xpath = "//tbody/tr/td[2]")
	WebElement resultOrderID;
	
	public void showSalesOptions() {
		Actions act= new Actions(driver);
		act.moveToElement(salesOptions).build().perform();
		int count=driver.findElements(By.xpath("//nav/ul/li[6]/ul/li")).size();
		System.out.println("Menu-Sale options=>");
		for(int i=1;i<=count;i++) {
			System.out.println(driver.findElement(By.xpath("//nav/ul/li[6]/ul/li["+i+"]/a")).getText());
		}
		ordersLink.click();
		
	}

	public boolean filterResults(String orderID2, String orderStatus, String addedDate, String customer2, String total2,
			String modifiedDate) {
		//orderID2=orderID2.replaceAll(".0", "");
		orderID.clear();
		orderID.sendKeys(orderID2);
		Select status=new Select(driver.findElement(By.cssSelector("#input-order-status")));
		status.selectByVisibleText(orderStatus);
		addDate.clear();
		addDate.sendKeys(addedDate);
		customer.clear();
		customer.sendKeys(customer2);
		total.clear();
		total.sendKeys(total2);
		modifyDate.clear();
		modifyDate.sendKeys(modifiedDate);
		filterIcon.click();
		boolean orderIDPresent= !(resultOrderID.getText().equals(null));
		
		return orderIDPresent;
	}
		
}
