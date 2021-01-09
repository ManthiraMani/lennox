package testclasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import utility.Base;

public class LoginTest extends Base {
	
	
	
	@BeforeMethod()
	public void setBrowserForTest() throws Throwable {

		setBrowser();
		
	}

	@Test
	public void registerNewUser() {

		try {
			
			
			//Test Starts
			getInputs();
			
			LoginPage lp=new LoginPage();
			lp.signINLink().click();			
			lp.userID().sendKeys(" lenproautomation8@lenqat.com");
			lp.password().sendKeys("Community17");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

}
