package ecoss.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecoss.pageobject.CartPage;
import ecoss.pageobject.OrderPage;
import ecoss.pageobject.ProductPage;

public class abstractcomponent {
	
	WebDriver driver;
	public abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="i.fa.fa-shopping-cart")
	WebElement goToCart;
	
	@FindBy(css="button.btn.btn-custom")
	WebElement goToProduct;
	
	@FindBy(css="i.fa.fa-handshake-o")
	WebElement goToOrder;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public ProductPage goToProductPage() {
		goToProduct.click();
		ProductPage productpage = new ProductPage(driver);
		return productpage;
	}
	
	public CartPage goToCartPage() {
		goToCart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage() {
		goToOrder.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

}
