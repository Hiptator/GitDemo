package ecoss.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecoss.AbstractComponents.abstractcomponent;

public class ProductPage extends abstractcomponent {
	
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[id='toast-container'] div div")
	WebElement addToCart;
	
	By addToCartBy = By.cssSelector("div[id='toast-container'] div div");
	
	public String addProductToCart(WebDriver driver, String productName) {
		List<WebElement> productList = driver.findElements(By.cssSelector(".card-body"));
		WebElement productItem = productList.stream()
				.filter(item -> item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		productItem.findElement(By.cssSelector("div button.btn.w-10.rounded")).click();
		waitForElementToAppear(addToCartBy);
		String addCartSuccess = addToCart.getText();
		return addCartSuccess;
	}

}
