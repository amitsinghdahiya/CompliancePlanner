package ComplianceCenteral.Planner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actionutility.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "#username")
	WebElement usernm;

	@FindBy(css = "#password")
	WebElement pass;

	@FindBy(css="#loginLink")
	WebElement submit;

	public void loginaction(String username, String password) {
		usernm.sendKeys(username);
		pass.sendKeys(password);
		submit.click();
	}
	public void goTO() {
		driver.get("https://ccqa.cxcnetwork.com:9743/user/login.xhtml");
	}
	
	
}

