package ecoss.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecoss.TestComponents.BasedTest;
import ecoss.pageobject.CartPage;
import ecoss.pageobject.CheckoutPage;
import ecoss.pageobject.OrderPage;
import ecoss.pageobject.ProductPage;

public class PurchaseOrderTest extends BasedTest{

	
	@Test(dataProvider="getData", groups="Sanity")
	public void ASubmitOrder(HashMap<String,String> input) throws IOException {
		// Login Account Page
		String loginSuccess = landingpage.LoginAccount(input.get("useremail"), input.get("password"));
		Assert.assertEquals("Login Successfully", loginSuccess);
		// Go To Product Page
		ProductPage productpage = landingpage.goToProductPage();
		String addCartSuccess = productpage.addProductToCart(driver, input.get("productname"));
		Assert.assertEquals("Product Added To Cart", addCartSuccess);
		// go To Cart Page
		CartPage cartpage = productpage.goToCartPage();
		// find item purchased in cart
		boolean itemInCart = cartpage.getItemAddedToCart(input.get("productname"));
		Assert.assertTrue(itemInCart);
		CheckoutPage checkoutpage = cartpage.CheckOutItem();
		// Check out Page
		String successMsg = checkoutpage.ProceedPayment();
		Assert.assertEquals(successMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider="getData",groups="Sanity")
	public void BOrderVerification(HashMap<String,String> input) throws IOException {
		// Login Account Page
		String loginSuccess = landingpage.LoginAccount(input.get("useremail"), input.get("password"));
		Assert.assertEquals("Login Successfully", loginSuccess);
		// Go To Product Page
		ProductPage productpage = landingpage.goToProductPage();
		// Go To Order Page
		OrderPage orderpage = productpage.goToOrderPage();
		Boolean orderVerification = orderpage.getOrderVerification(input.get("productname"));
		Assert.assertTrue(orderVerification);
	}
	
	@Test(groups="Regression")
	public void RegisterAccount() throws IOException {
		// TestData
		String firstName ="Sakuragi";
		String lastName = "Hanamichi";
		String userEmail = "Sakuragi3@gmail.com";
		String userMobile = "1234567898";
		String userPassword = "Abcd@123";
		// Registration Account
		String actualMsg = landingpage.RegisterAccount(firstName, lastName, userEmail, userMobile, userPassword);
		Assert.assertEquals(actualMsg, "Account Created Successfully");
	}
	
	@DataProvider
	public Object getData() throws IOException {
		// Call JsonFile class from Based
		List<HashMap<String,String>> data = getJsonFile(System.getProperty("user.dir")+"\\src\\test\\java\\ecoss\\data\\PurchaseOrder.json");
		
		return new Object[][] { { data.get(0)}, { data.get(1) } };
	}

}
