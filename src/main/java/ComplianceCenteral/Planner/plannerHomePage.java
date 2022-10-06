package ComplianceCenteral.Planner;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import actionutility.AbstractComponents;

public class plannerHomePage extends AbstractComponents {
	WebDriver driver;
	
public plannerHomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(xpath="//span[contains(text(),'Compliance Planner')]")
WebElement plannerMenuItem;

@FindBy(xpath="//select[@id='EmployerId']")
WebElement selectEmployer;

		
public void menuItem(WebDriver driver) {
	plannerMenuItem.click();
}

public void SelectEmpDD(WebDriver driver) {
	Select selectlistOfEmployer = new Select(driver.findElement(By.xpath("//select[@id='EmployerId']")));
	List<WebElement> listOfEmployers = selectlistOfEmployer.getOptions();
	System.out.println("the size is " + listOfEmployers.size());
	for (int i = 0; i < listOfEmployers.size(); i++) {
		System.out.println("employer name is    " + listOfEmployers.get(i).getText());
		if (listOfEmployers.get(i).getText().equalsIgnoreCase("prachichecklist&employer (123456789 - 5555)")) {
			listOfEmployers.get(i).click();
			System.out.println("The employer     " + listOfEmployers.get(i).getText() + "     matches ");
			break;
		}
	}
}
}