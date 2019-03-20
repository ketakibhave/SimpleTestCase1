package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RTTC_053POM {
	private WebDriver driver;
	boolean match = false;

	public RTTC_053POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;
/*
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.loginBtn.click();
	}
	*/
	public void loginAdmin(String userName, String password)
	{
		this.userName.clear();
		this.userName.sendKeys(userName);
		this.password.clear();
		this.password.sendKeys(password);
		this.loginBtn.click();
	}
	@FindBy(xpath = "//h3[contains(text(),'Latest Orders')]")
	private WebElement latestOrdersText;
	/*
	@FindBy(xpath = "//tr[1]//td[6]//a[1]")
	private WebElement viewDetails;
	*/
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/button/i")
	private WebElement generate;
	
	@FindBy(css = "#invoice")
	private WebElement invoiceID;
	
	@FindBy(css = "#button-history")
	private WebElement addHistory;
	
	public boolean displayLatestOrdersSection() {
		System.out.println(latestOrdersText.getText());
		return latestOrdersText.isDisplayed();
	}

	public String ordersPage() {
		boolean flag=true;
		int j=1;
		
			for(int i=1;i<=5;i++) {
				if(driver.findElement(By.xpath("//table[1]/tbody[1]/tr["+i+"]/td[3]")).getText().equals("Pending"))
					{
					flag=false;
					j=i;
					break;
					}
			}
		System.out.println("Flag= "+flag);
		if(flag)
			return "No orders in Pending state";
		else
		{
		String xpathloc="//tr["+j+"]//td[6]//a[1]";
		driver.findElement(By.xpath(xpathloc)).click();
		generate.click();
		System.out.println(invoiceID.getText());
		Select statusDropDown=new Select(driver.findElement(By.cssSelector("#input-order-status")));
		statusDropDown.selectByVisibleText("Complete");
		addHistory.click();
		return driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		}
	}
	
	
}
