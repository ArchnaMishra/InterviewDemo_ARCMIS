package testcom.qa.sauselabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;
	
	private By drpDownSort= By.className("select.product_sort_container");
	private By dropdwnSortList=By.className("select.product_sort_container option");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHomePageTitle()
	{
		String homePageTitle= driver.getTitle();
		System.out.println("Home Page Title " + homePageTitle);
		return homePageTitle;		
	}
	
	public String getHomePageURL()
	{
		String homePageURL= driver.getCurrentUrl();
		System.out.println("Home Page URL " + homePageURL);
		return homePageURL;		
	}
	
	
	

	
}
