package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.Base;

public class LoginPage extends Base{
	
	

	
	public WebElement signINLink(){
		
		checkElementPresentStatusByXpath("//a[normalize-space()='Sign In']");		
		WebElement login_name=driver.findElement(By.xpath("//a[normalize-space()='Sign In']"));
		return login_name;
	}
	
	public WebElement userID(){
		
		checkElementPresentStatusByXpath("//input[@id='j_username']");		
		WebElement login_name=driver.findElement(By.xpath("//input[@id='j_username']"));
		return login_name;
	}
	
	
	public WebElement password(){
		
		checkElementPresentStatusByXpath("//input[@id='j_password']");
		WebElement login_password=driver.findElement(By.xpath("//input[@id='j_password']"));
		return login_password;
	}
	
	
	public WebElement signINBtn(){
		
		checkElementPresentStatusByXpath("//button[@id='loginSubmit']");
		WebElement signIn_btn=driver.findElement(By.xpath("//button[@id='loginSubmit']"));
		return signIn_btn;
		
	}
	
	
	public void logIN(){
		
		
		getInputs();
		
		LoginPage lp=new LoginPage();
		lp.signINLink().click();			
		lp.userID().sendKeys("lenproautomation8@lenqat.com");
		lp.password().sendKeys("Community17");
		lp.signINBtn().click();
	}
	

}



