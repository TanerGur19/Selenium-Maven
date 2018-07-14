package suitecrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuiteCRMCreateTaskPage {

	public SuiteCRMCreateTaskPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "name")  // its not self described thats why we say FindBy
	public WebElement subject;

	public WebElement status; // if you have same name as id you don't need FindBy

	public WebElement description; // id is also description

	//@FindBy(xpath="//input[@value='Save']")
	public WebElement SAVE;
	
	
	
	
	
}
