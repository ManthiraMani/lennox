package pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utility.Base;

public class AddLeadObjects extends Base {
	
	
	
	
public WebElement clickOnAddLead(){
		
		checkElementPresentStatusByXpath("//a[@class='btn btn-primary hide-mobile introjs-l-9 introjs-l-83']//span[contains(text(),'ADD LEAD')]");		
		WebElement addLead=driver.findElement(By.xpath("//a[@class='btn btn-primary hide-mobile introjs-l-9 introjs-l-83']//span[contains(text(),'ADD LEAD')]"));
		return addLead;
	}
	
	
	public WebElement enterFirstName(){
		
		checkElementPresentStatusByXpath("//input[@id='firstName']");
		WebElement firstName=driver.findElement(By.xpath("//input[@id='firstName']"));
		return firstName;
	}
	
	
	public WebElement enterLastName(){
		
		checkElementPresentStatusByXpath("//input[@id='lastName']");
		WebElement lastName=driver.findElement(By.xpath("//input[@id='lastName']"));
		return lastName;
		
	}
	
	
	public WebElement enterPhone(){
		
		checkElementPresentStatusByXpath("//input[@id='phNo']");
		WebElement phone=driver.findElement(By.xpath("//input[@id='phNo']"));
		return phone;
	}
	
	
	public WebElement enterEmail(){
		
		checkElementPresentStatusByXpath("//input[@id='email']");
		WebElement email=driver.findElement(By.xpath("//input[@id='email']"));
		return email;
		
	}
	
	public WebElement oldDescription(){		
		
		checkElementPresentStatusByXpath("//input[@id='oldSystemDescription']");
		WebElement email=driver.findElement(By.xpath("//input[@id='oldSystemDescription']"));
		return email;
	}
	
	
	
	
	public void triggerAction(String xpath){
		
		Actions acn=new Actions(driver);
		acn.moveToElement(driver.findElement(By.xpath(xpath))).click().perform();
	}
	
	public WebElement clickOnSchedulingRequestDate(){
		
		checkElementPresentStatusByXpath("//input[@id='calender1']");
		WebElement clickOnReqCalendar=driver.findElement(By.xpath("//input[@id='calender1']"));
		return clickOnReqCalendar;
		
		
	}
	
	public void scheduleRequestDate(String date) {

		List<WebElement> options = driver
				.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));

		for (WebElement opt : options) {

			String allDates = opt.getText();
			System.out.println(allDates);			
			if (opt.getText().equalsIgnoreCase(date)) {
				Actions actions = new Actions(driver);
				actions.moveToElement(opt).click().perform();
				break;
			}
		}

	}
	
	
	
	
	public WebElement schedulingRequestTime(){
		
		checkElementPresentStatusByXpath("//div[@class='time-select schedul-req-time']");
		WebElement selectLead=driver.findElement(By.xpath("//div[@class='time-select schedul-req-time']"));
		return selectLead;
		
		
	}
	
	//select[@name='scheduleRequestTime']
	
	
	public void selectScheduledTimings(String time) {

		List<WebElement> options = driver
				.findElements(By.xpath("//select[@name='scheduleRequestTime']/option"));

		for (WebElement opt : options) {
			String allTimings = opt.getText();
			System.out.println(allTimings);
			if (opt.getAttribute("innerHTML").contains(time)) {					
				opt.click();					
				break;
			}
		}

	}
	
	
	

	public WebElement addDocument(){
		
		checkElementPresentStatusByXpath("//p[contains(text(),'Add Document')]");
		WebElement addDoc=driver.findElement(By.xpath("//p[contains(text(),'Add Document')]"));
		return addDoc;
		
		
	}
	
	public WebElement selectDocumentType(){
		
		WebElement documentType=driver.findElement(By.xpath("//select[@name='documents[1].documentType']"));
		return documentType;
		
	}
	
	
	public void selectDocumentTypeValue(String type){				
		
		waitForObjectsSync();
		
		List<WebElement> options=driver.findElements(By.xpath("//select[@name='documents[1].documentType']/option"));
		
		for(WebElement opt: options){	
			
			if (opt.getText().equalsIgnoreCase(type)) {				
				 opt.click();				
				break;
	            
	 
	          }
			
			
		}
		
		
	}
	
	
	public WebElement selectFile(){
		
		WebElement documentType=driver.findElement(By.xpath("//input[@name='documentFiles[1]']"));
		return documentType;
	
	}
	
	
	
	public WebElement addLeadDocs(){
		
		checkElementPresentStatusByXpath("//span[contains(text(),'Add To Lead')]");
		WebElement documentType=driver.findElement(By.xpath("//span[contains(text(),'Add To Lead')]"));
		return documentType;
	}
	
	
	public WebElement addImages(){		
		checkElementPresentStatusByXpath("//p[contains(.,'Add Image')]");
		WebElement selectLead=driver.findElement(By.xpath("//p[contains(.,'Add Image')]"));
		return selectLead;
		
		
	}
	
	
	
	public WebElement saveLead(){
		
		checkElementPresentStatusByXpath("//span[normalize-space()='SAVE LEAD']");
		WebElement selectLead=driver.findElement(By.xpath("//span[normalize-space()='SAVE LEAD']"));
		return selectLead;
	}
	
	public WebElement successMsgForLead(){		
		
		checkElementPresentStatusByXpath("//li[contains(text(),'Lead Saved Successfully')]");
		WebElement selectLead=driver.findElement(By.xpath("//li[contains(text(),'Lead Saved Successfully')]"));
		return selectLead;
	}
}
