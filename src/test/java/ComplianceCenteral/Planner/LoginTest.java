package ComplianceCenteral.Planner;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends BaseClass {

	ExtentReports extent;

	@Test(dataProvider = "getData", retryAnalyzer =Retry.class )
	public void PlannerTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ExtentTest test = extent.createTest("PlannerTest");
		extent.flush();

		LoginPage loginpage = launchApplication();

		loginpage.loginaction(input.get("userN"), input.get("passW"));

		String actualtitile = driver.getTitle();
		String Expectedtitle = "Dashboard";
		Assert.assertEquals(actualtitile, Expectedtitle);

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Resources')]"))).build().perform();

		plannerHomePage homepage = new plannerHomePage(driver);
		homepage.menuItem(driver);

		// plannerHomePage.menuItem(driver);
		// driver.findElement(By.xpath("//span[contains(text(),'Compliance
		// Planner')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='EmployerId']")));

		/*
		 * Select employerDD = new
		 * Select(driver.findElement(By.cssSelector("#EmployerId"))); List<WebElement>
		 * employerlist = employerDD.getOptions();
		 * System.out.println("the size of employer list is " + employerlist.size());
		 * 
		 * for (int i = 0; i < employerlist.size(); i++) {
		 * System.out.println("List  of employers are" + employerlist.get(i).getText());
		 * 
		 * if (employerlist.get(i).getText().
		 * equalsIgnoreCase("PrachiChecklist&Employer (123456789 - 5555)"))
		 * 
		 * employerlist.get(i).click(); System.out.println("employer" +
		 * employerlist.get(i).getText()); break; }
		 */
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@id='EmployerId']")));
		Select selectlistOfEmployer = new Select(driver.findElement(By.xpath("//select[@id='EmployerId']")));
		List<WebElement> listOfEmployers = selectlistOfEmployer.getOptions();
		System.out.println("the size is " + listOfEmployers.size());
		for (int i = 0; i < listOfEmployers.size(); i++) {
			System.out.println("employer name is    " + listOfEmployers.get(i).getText());
			if (listOfEmployers.get(i).getText().equalsIgnoreCase(input.get("employerName"))) {
				listOfEmployers.get(i).click();
				System.out.println("The employer     " + listOfEmployers.get(i).getText() + "     matches ");
				break;
			}
		}

		Select taxyear = new Select(driver.findElement(By.cssSelector("#taxYear")));
		List<WebElement> taxyearvalues = taxyear.getOptions();
		System.out.println("tax year values are " + taxyearvalues.size());
		for (int i = 0; i < taxyearvalues.size(); i++) {
			System.out.println("Value of TY are" + taxyearvalues.get(i).getText());
			if (taxyearvalues.get(i).getText().equalsIgnoreCase("2023")) {
				taxyearvalues.get(i).click();
				System.out.println("2023 tax year is selected" + taxyearvalues.get(i).getText());
			}
		}
		Thread.sleep(4000);

		Select configname = new Select(driver.findElement(By.cssSelector("#planId")));
		List<WebElement> confignamevalues = configname.getOptions();
		System.out.println("config values are " + confignamevalues.size());
		for (int i = 0; i < confignamevalues.size(); i++) {
			System.out.println("Value of configurations are" + confignamevalues.get(i).getText());
			if (confignamevalues.get(i).getText().equalsIgnoreCase("Create Configuration")) {
				confignamevalues.get(i).click();
				System.out.println("Create Configuration is selected" + confignamevalues.get(i).getText());
			}
		}
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[contains(text(),'Setup')])[1]")));
		driver.findElement(By.xpath("(//div[contains(text(),'Setup')])[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[id*='createChecklistForm:j_idt138']")).sendKeys("config1234");
		Thread.sleep(4000);
		Select selectDD = new Select(driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt152']")));
		selectDD.selectByVisibleText("Yes");
		Thread.sleep(4000);
		WebElement PSD = driver.findElement(By.xpath("//input[@id='createChecklistForm:j_idt160']"));
		PSD.sendKeys("01/01/2023");
		Thread.sleep(4000);
		WebElement PED = driver.findElement(By.xpath("//input[@id='createChecklistForm:j_idt165']"));
		PED.sendKeys("12/31/2023");
		Thread.sleep(4000);
		Select chruchDD = new Select(driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt182']")));
		chruchDD.selectByVisibleText("No");
		Thread.sleep(4000);
		Select EmpQuestion = new Select(
				driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt195']")));
		EmpQuestion.selectByVisibleText("Yes");
		Thread.sleep(4000);
		Select AggregateALeQuestion = new Select(
				driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt207']")));
		AggregateALeQuestion.selectByVisibleText("Yes");
		Thread.sleep(4000);
		Select SelfFundedQuestion = new Select(
				driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt216']")));
		SelfFundedQuestion.selectByVisibleText("Yes");
		Thread.sleep(4000);
		Select VariableHrQuestion = new Select(
				driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt240']")));
		VariableHrQuestion.selectByVisibleText("Yes");
		Thread.sleep(4000);
		Select BenefitPreTaxQuestion = new Select(
				driver.findElement(By.cssSelector("select[id*='createChecklistForm:j_idt250']")));
		BenefitPreTaxQuestion.selectByVisibleText("No");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("a[id*='createChecklistForm:j_idt309']")).click();
		Thread.sleep(4000);

	}

	

	@DataProvider
	public Object[] getData() {
		// HashMap<String, String> map= new HashMap<String, String>();
		// map.put("userN", "testcxc");
		// map.put("passW","test123");
		// map.put("employerName", "prachichecklist&employer (123456789 - 5555)");

		List<HashMap<String, String>> data = getJasonDataToMap(
				(System.getProperty("user.dir") + "//src//test//java//ComplinacePlanner//Data//Login.jason"));
		return new Object[][] { { data.get(0) } };

	}

	public static List<WebElement> SelectClassFunction(WebElement ale) {

		Select selectObj = new Select(ale);
		return selectObj.getOptions();

	}

}
