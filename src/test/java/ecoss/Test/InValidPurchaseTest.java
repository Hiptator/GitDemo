package ecoss.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecoss.TestComponents.BasedTest;

public class InValidPurchaseTest extends BasedTest {
	
	@Test(dataProvider="getNegativeData", groups="Negative")
	public void invalidLoginCredentials(HashMap<String,String> input) throws IOException {
		String expectedError = "Incorrect email or password.";
		String errorMsg = landingpage.LoginAccount(input.get("useremail"),input.get("password"));
		Assert.assertEquals(errorMsg, expectedError);
	}
	
	@DataProvider
	public Object getNegativeData() throws IOException {
		List<HashMap<String,String>> data = getJsonFile(System.getProperty("user.dir")+"\\src\\test\\java\\ecoss\\data\\InvalidCredentials.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
