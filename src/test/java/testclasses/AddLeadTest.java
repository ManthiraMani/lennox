package testclasses;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import extentlisteners.ExtentListeners;
import pageobjects.AddLeadObjects;
import pageobjects.HomepageObjects;
import pageobjects.LoginPage;
import utility.Base;

public class AddLeadTest extends Base {

	String documentPath = System.getProperty("user.dir") + "\\sample_docs\\sample.pdf";
	String uploadExeLocation = System.getProperty("user.dir") + "\\drivers\\upld.exe";
	
	DataFormatter formatter=new DataFormatter();
	public static String FilePath = System.getProperty("user.dir") + "\\data\\Lennox.xlsx";
	private static String sheet_name = "Sheet1";	
	
	

	
	
	String expectedmsg = "Lead Saved Successfully";

	@BeforeMethod()
	public void setBrowserForTest() throws Throwable {

		setBrowser();
		LoginPage lp = new LoginPage();
		lp.logIN();
		waitForObjectsSync();

	}

	@Test(dataProvider="driveTest")
	public void addLead(String testnae, String first_name, String last_name, String phone_no, String email_id, 
			String reqDate, String reqTime, String uploadDoc, String docType, String uploadImages) {

		try {			

			// Navigate to Proposal
			HomepageObjects hp = new HomepageObjects();
			hp.clickOnMenu().click();
			hp.salesTools().click();
			hp.buildAProposal().click();
			waitForObjectsSync();
			hp.selectLead().click();

			// Enter FirstName,LastName, PhoneNo, Email
			waitForObjectsSync();
			AddLeadObjects alo = new AddLeadObjects();
			alo.clickOnAddLead().click();
			alo.enterFirstName().sendKeys(first_name);
			alo.enterLastName().sendKeys(last_name);
			alo.enterPhone().sendKeys(phone_no);
			alo.enterEmail().sendKeys(email_id);

			JavascriptExecutor jscr = (JavascriptExecutor) driver;
			jscr.executeScript("window.scrollBy(0,800)");
			waitForObjectsSync();

			// Set Scheduling Request Date and Time
			alo.triggerAction("//input[@id='calender1']");
			alo.scheduleRequestDate(reqDate);
			waitForObjectsSync();
			alo.schedulingRequestTime().click();
			alo.selectScheduledTimings(reqTime);
			pressEscKey();
			waitForObjectsSync();

			// Uploads documents
			if (uploadDoc.equalsIgnoreCase("Yes")) {
				alo.addDocument().click();
				waitForObjectsSync();
				alo.triggerAction("//select[@name='documents[1].documentType']");
				alo.selectDocumentTypeValue(docType);
				alo.selectFile().sendKeys(documentPath);
				pressEscKey();
				alo.addLeadDocs().click();				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)");

			} else {
				System.out.println("No document Attached");
			}

			
			// Upload Images
			if (uploadImages.equalsIgnoreCase("Yes")) {
				Actions actn = new Actions(driver);
				actn.moveToElement(alo.addImages()).click().perform();				
				Runtime.getRuntime().exec(uploadExeLocation);
				waitForObjectsSync();
				JavascriptExecutor js2 = (JavascriptExecutor) driver;
				js2.executeScript("window.scrollBy(0,1000)");
				waitForObjectsSync();

			} else {
				System.out.println("No images Attached");
			}

			
			// Save Lead Inputs
			alo.saveLead().click();
			waitForObjectsSync();
			String actualmsg = alo.successMsgForLead().getText();
			Assert.assertEquals(actualmsg, expectedmsg, "Lead Is Not Saved");
			ExtentListeners.takeScreenshot();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@AfterMethod
	public void tearDown() {
		// driver.close();
	}
	
	
	
	
	
	
	
	
	@DataProvider(name = "driveTest")
	public Object[][] getData() throws IOException {
		
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheet_name);
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		System.out.println(colCount);
		Object data[][] = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);

				data[i][j] = formatter.formatCellValue(cell);

			}
		}
		
		return data;

	}


}
