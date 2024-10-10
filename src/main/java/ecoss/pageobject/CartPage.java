package ecoss.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecoss.AbstractComponents.abstractcomponent;

public class CartPage extends abstractcomponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li.items.even.ng-star-inserted")
	List<WebElement> itemListCart;
	
	@FindBy(css="ul li button[type='button']")
	WebElement checkout;
	
	By itemListBy = By.cssSelector("li.items.even.ng-star-inserted h3");
	
	public boolean getItemAddedToCart(String productName) {
		List<WebElement> cartList = itemListCart;
		boolean itemInCart = cartList.stream().anyMatch(cart -> cart
				.findElement(itemListBy).getText().equals(productName));
		return itemInCart;
	}
	
	public CheckoutPage CheckOutItem() {
		checkout.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}

}
