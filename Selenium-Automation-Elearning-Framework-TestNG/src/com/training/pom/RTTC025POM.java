package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RTTC025POM {
	private WebDriver driver;

	public RTTC025POM(WebDriver driver) {
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
	
	@FindBy(linkText = "Returns")
	private WebElement returnsLink;

	@FindBy(id = "button-filter")
	private WebElement filterClick;
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[5]")
	private WebElement totalValue;
	
	

	public boolean displayReportsList() {

		//WebElement menuIcon= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/a[1]"));
		Actions act1=new Actions(driver);
		act1.moveToElement(menuIcon).build().perform();
		String reportsIconList[]= {"Sales", "Products","Customers","Marketing"};

		int linksCount=driver.findElements(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li")).size();

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
		int salesCount=driver.findElements(By.xpath("/html[1]/body[1]/div[1]/nav[1]/ul[1]/li[10]/ul[1]/li[1]/ul/li")).size();
		//System.out.println("Sales links count== "+salesCount);

		//WebElement salesIcon=driver.findElement(By.linkText("Sales"));
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

	public String navigateToReturns() {
		//WebElement ordersLink=driver.findElement(By.linkText("Returns"));
		returnsLink.click();
		return driver.getTitle();

	}

	public boolean displayGroupByListboxOptions() {
		Select menuIcon=new Select(driver.findElement(By.name("filter_group")));
		List<WebElement> menuList=menuIcon.getOptions();
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
		Select menuIcon=new Select(driver.findElement(By.name("filter_group")));
		menuIcon.selectByValue("week");
		//WebElement filterClick=driver.findElement(By.id("button-filter"));
		filterClick.click();
		//WebElement totalValue=driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		System.out.println("Total= "+totalValue.getText());
		System.out.println("=========================");
		return driver.findElement(By.xpath("//td[contains(text(),'No. Returns')]")).getText();
	}
}
