package ComplianceCenteral.Planner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C://Users//Admin//eclipse-workspace//Planner//src//main//java//Resources//Config.properties");
		prop.load(fis);

		String browsername = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browsername = prop.getProperty("browser");
		if (browsername.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			
if (browsername.contains("headless")){
	

			WebDriverManager.chromedriver().setup();
			options.addArguments("headless");
}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
			
		} else if (browsername.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver","C://Users//Admin//Downloads//geckodriver");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}
	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTO();
		return loginPage;
	}
@AfterMethod
public void tearDown() {
	driver.close();
}
}
