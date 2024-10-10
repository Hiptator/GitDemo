package ecoss.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ecoss.AbstractComponents.abstractcomponent;

public class CheckoutPage extends abstractcomponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String ProceedPayment() {
		driver.findElement(By.cssSelector("div.field:nth-child(2) input")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Malaysia");
		driver.findElement(By.cssSelector("section button span")).click();
		driver.findElement(By.cssSelector(
				"body > app-root:nth-child(1) > app-order:nth-child(2) > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > form:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > input:nth-child(2)"))
				.sendKeys("Sakuragi");
		driver.findElement(By.cssSelector("div a.btnn.action__submit.ng-star-inserted")).click();
		String successMsg = driver.findElement(By.cssSelector("tr td h1")).getText();
		return successMsg;
	}

}
