package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class RTTC_022POM {
	private WebDriver driver; 

	public RTTC_022POM(WebDriver driver) {
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
	
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/a[1]")
	private WebElement menuIcon;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li")
	private List <WebElement> linksCountElement;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[1]/ul/li")
	private List <WebElement> salesOptions;
	
	@FindBy(linkText = "Sales")
	private WebElement salesIcon;
	
	@FindBy(linkText = "Orders")
	private WebElement ordersLink;

	@FindBy(xpath = "//select[@id='input-group']")
	private Select dropdownList;
	
	@FindBy(id = "button-filter")
	private WebElement filterClick;
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[6]")
	private WebElement totalValue;
	
	public boolean displayReportsList() {

		//WebElement menuIcon= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/a[1]"));
		Actions act1=new Actions(driver);
		act1.moveToElement(menuIcon).build().perform();
		String reportsIconList[]= {"Sales", "Products","Customers","Marketing"};

		int linksCount=linksCountElement.size();
		//System.out.println("Links count== "+linksCount);

		boolean match=true;
		System.out.println("Reports===> ");
		for(int i=1;i<=linksCount;i++) {
			match= reportsIconList[i-1].equals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li["+i+"]")).getText());
			System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li["+i+"]")).getText());
			if(!match)
			{
				System.out.println("Match?? "+match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return match;
	}

	public boolean displaySalesList() {
		String salesOptionList[]= {"Orders","Tax","Shipping","Returns","Coupons"};
		int salesCount=salesOptions.size();
		salesIcon.click();
		boolean match=true;
		System.out.println("Sales===>");
		for(int i=1;i<=salesCount;i++) {
			match= salesOptionList[i-1].equals(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[1]/ul[1]/li["+i+"]/a[1]")).getAttribute("text"));
			System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[1]/ul[1]/li["+i+"]/a[1]")).getAttribute("text"));
			if(!match)
			{
				System.out.println("Match?? "+match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return match;

	}

	public String navigateToOrders() {
		ordersLink.click();
		return driver.getTitle();

	}

	public boolean displayGroupByListboxOptions() {
		Select dropdownList=new Select(driver.findElement(By.name("filter_group")));
		List<WebElement> menuList=dropdownList.getOptions();
		String groupByListValues[]= {"year","month","week","day"};
		boolean match=true;
		int ListLength=menuList.size();
		System.out.println("Length= "+ListLength);
		System.out.println("Group By options===>");
		for(int i=0;i<ListLength;i++)
		{
			match=(menuList.get(i).getAttribute("value")).equals(groupByListValues[i]);
			System.out.println(menuList.get(i).getAttribute("value"));
			if(!match)
			{
				System.out.println("Match?? "+match);
				System.out.println("=========================");
				return match;
			}
		}
		System.out.println("=========================");
		return match;
	}
	
	public String filterListbyGroup() {
		Select dropdownList=new Select(driver.findElement(By.name("filter_group")));
		dropdownList.selectByValue("week");
		filterClick.click();
		System.out.println("Total= "+totalValue.getText());
		System.out.println("=========================");
		return driver.findElement(By.xpath("//td[contains(text(),'Total')]")).getText();
	}

}
