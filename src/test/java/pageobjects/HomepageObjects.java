package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.Base;

public class HomepageObjects extends Base {
	
	
	public WebElement profileUpdateYes() {
						
		checkElementPresentStatusByXpath("//button[@id='profile-update-confirmation']");		
		WebElement login_name=driver.findElement(By.xpath("//button[@id='profile-update-confirmation']"));
		return login_name;

	}
	public WebElement clickOnMenu(){
		
		checkElementPresentStatusByXpath("//a[@href='#navigation']");		
		WebElement homeMenu=driver.findElement(By.xpath("//a[@href='#navigation']"));
		return homeMenu;
	}
	
	public WebElement salesTools(){
		
		checkElementPresentStatusByXpath("//a[normalize-space()='Sales Tools']");		
		WebElement salesTools=driver.findElement(By.xpath("//a[normalize-space()='Sales Tools']"));
		return salesTools;
	}
	
	
	public WebElement buildAProposal(){
		
		checkElementPresentStatusByXpath("//a[contains(text(),'Build a Proposal')]");
		WebElement buildProposal=driver.findElement(By.xpath("//a[contains(text(),'Build a Proposal')]"));
		return buildProposal;
	}
	
	
	public WebElement selectLead(){
		
		checkElementPresentStatusByXpath("//a[contains(text(),'SELECT LEAD')]");
		WebElement selectLead=driver.findElement(By.xpath("//a[contains(text(),'SELECT LEAD')]"));
		return selectLead;
		
	}
	


}
