package testcom.qa.opencart.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtil {
	
	WebDriver driver;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement findElement(By byVal)
	{
		WebElement ele= driver.findElement(byVal);
		return ele;
	}
	
	public void sendKeys(By byVal, String value)
	{
		WebElement ele= findElement(byVal);
		ele.clear();
		ele.sendKeys(value);
	}
}
