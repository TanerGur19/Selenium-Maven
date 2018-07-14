package SuiteCRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuiteCRMLogInPage {

	public SuiteCRMLogInPage(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
		
		
		
	}
	@FindBy(id="user_name")
	
	public WebElement userName;
	
	public WebElement username_password; // if you have exact same id or name you can store without FindBy. if first character is number it wont work though
	
	@FindBy(id="bigbutton")
	public WebElement loginButton;
	
	public void login(String username, String password) {
		
		userName.sendKeys(username);
		username_password.sendKeys(password);
		loginButton.click();
		
		
	}
	
	
	
	
	
}
