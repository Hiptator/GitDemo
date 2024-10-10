package ecoss.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecoss.AbstractComponents.abstractcomponent;

public class OrderPage extends abstractcomponent{
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr.ng-star-inserted")
	List<WebElement> orders;
	
	public boolean getOrderVerification(String productName) {
		boolean orderVerification = orders.stream().anyMatch(item->item.findElement(By.cssSelector("td:nth-child(3)")).getText().equals(productName));
		return orderVerification;
	}

}
