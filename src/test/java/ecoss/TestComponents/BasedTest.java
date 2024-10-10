package ecoss.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecoss.pageobject.LandingPage;

public class BasedTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver InitializeWebDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream files = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ecoss\\recourses\\GlobalData.properties"); //get file from global data file
		prop.load(files); //use properties object to load the file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser"); //Get data file location mention above
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("firefox")){
			//insert Driver
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchingSystem() throws IOException {
		driver = InitializeWebDriver();
		landingpage = new LandingPage(driver);
		landingpage.goToLandingPage();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void ClosingBrowser() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonFile(String fileLocation) throws IOException {
		String JsonString = FileUtils.readFileToString(new File(fileLocation), StandardCharsets.UTF_8); // Read Json file to String
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonString, new TypeReference<List<HashMap<String,String>>>(){}); // Convert String to HashMap
		return data;
	}
	
	// ScreenShot
	public String getScreenShot(String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testcaseName+"//.png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"//reports//"+ testcaseName+"//.png";
	}

}







