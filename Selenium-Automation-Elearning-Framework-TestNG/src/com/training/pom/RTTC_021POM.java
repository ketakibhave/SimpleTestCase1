package com.training.pom;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RTTC_021POM {

	private WebDriver driver;
	boolean match = false;

	public RTTC_021POM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

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

	@FindBy(xpath = "//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-customer']/a[1]")
	private WebElement menuIcon;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li")
	private List<WebElement> customerOptions;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[1]")
	private WebElement navigateToCustomersLink;

	@FindBy(css = "#input-name")
	private WebElement customerName;

	@FindBy(css = "#button-filter")
	private WebElement filterIcon;

	@FindBy(xpath = "//form[1]/div[1]/table[1]/tbody/tr/td")
	private List<WebElement> elements;

	@FindBy(xpath = "//table/tbody/tr/td/input")
	private WebElement customerSelection;

	@FindBy(xpath = "//div[@class='pull-right']//button[@type='button']")
	private WebElement deleteIcon;

	@FindBy(xpath = "//table[1]/tbody[1]/tr[1]/td[1]")
	private WebElement confirmMsg;

	public boolean displayCustomersOptions() {

		Actions act1 = new Actions(this.driver);
		act1.moveToElement(this.menuIcon).build().perform();
		String customerIconList[] = { "Customers", "Customer Groups", "Custom Fields" };
		int count = customerOptions.size();
		System.out.println("List of links available under Customers==> ");
		for (int i = 1; i <= count; i++) {
			this.match = customerIconList[i - 1].equals(driver
					.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[" + i + "]")).getText());
			System.out.println(driver
					.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[7]/ul[1]/li[" + i + "]")).getText());
			if (!match) {
				System.out.println("Match?? " + this.match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return this.match;
	}

	public void navigateToCustomersPage() {
		this.navigateToCustomersLink.click();
	}

	public String selectCustomerBySearch(String name) {
		this.customerName.sendKeys(name);
		this.filterIcon.click();
		int size = this.elements.size();
		if (size == 1)
			return driver.findElement(By.xpath("//form[1]/div[1]/table[1]/tbody/tr/td[1]")).getText();
		else
			return driver.findElement(By.xpath("//form[1]/div[1]/table[1]/tbody/tr/td[2]")).getText();
	}

	public String deleteCustomerRecord() {

		this.customerSelection.click();
		System.out.println("Is selected??" + customerSelection.isSelected());

		try {
			this.deleteIcon.click();
			Alert deleteConfirm = this.driver.switchTo().alert();

			deleteConfirm.accept();
		} catch (UnhandledAlertException e) {
			System.out.println("Exception occured== " + e);
		}
		return this.confirmMsg.getText();

	}

}
