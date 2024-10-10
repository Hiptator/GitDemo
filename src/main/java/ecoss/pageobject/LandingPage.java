package ecoss.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ecoss.AbstractComponents.abstractcomponent;

public class LandingPage extends abstractcomponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[id='toast-container'] div div")
	WebElement loginSuccessMsg;
	
	By loginMsgBy = By.cssSelector("div[id='toast-container'] div div");
	
	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String LoginAccount(String email,String password) {
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		waitForElementToAppear(loginMsgBy);
		String loginSuccess = loginSuccessMsg.getText();
		waitForElementToDisappear(loginMsgBy);
		return loginSuccess;
	}
	
	public String RegisterAccount(String firstName,String lastName,String userEmail,String userMobile,String userPassword) {
		driver.findElement(By.cssSelector("p.login-wrapper-footer-text")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("userEmail")).sendKeys(userEmail);
		driver.findElement(By.id("userMobile")).sendKeys(userMobile);
		WebElement drop = driver.findElement(By.cssSelector("select.custom-select")); // Dropdown handling
		Select Drop = new Select(drop);
		Drop.selectByIndex(2);
		driver.findElement(By.id("userPassword")).sendKeys(userPassword);
		driver.findElement(By.id("confirmPassword")).sendKeys(userPassword);
		driver.findElement(By.cssSelector("input[value='Female']")).click();
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		driver.findElement(By.id("login")).click();
		String actualMsg = driver.findElement(By.cssSelector("div h1.headcolor")).getText();
		return actualMsg;
	}
	
}
