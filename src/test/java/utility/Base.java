package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static WebDriver driver;
	public Properties properties;
	String waitingTime;
	String chromeBrowser = "chrome";
	String firefoxBrowser = "firefox";
	String edgeBrowser = "edge";

	public static Logger log = LogManager.getLogger(Base.class.getName());
	public static String atuoITFilePath=System.getProperty("user.dir") + "\\sample_docs\\Lennox.png";
	
	
	

	public void setBrowser() throws Throwable {

		// Reading Json Input
		String chromeDriverPath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
		String firefoxDriverPath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";
		String ieDriverPath = System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe";
		
		String propertyPath = System.getProperty("user.dir") + "\\properties\\config.properties";
		properties = new Properties();
		FileInputStream readProperties = new FileInputStream(propertyPath);
		properties.load(readProperties);

		waitingTime = properties.getProperty("implicitTime");
		String testExecutionBrowser = properties.getProperty("browser");
		System.out.println("Execution browser " + testExecutionBrowser);

		if (testExecutionBrowser.equals(chromeBrowser)) {

			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			log.info("Inititate chrome browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver();
			log.info("Initiated chrome browser");

		} else if (testExecutionBrowser.equals(firefoxBrowser)) {

			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			log.info("Initiate firefox browser");
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("javascript.enabled", true);
			driver = new FirefoxDriver(options);
			log.info("Initiated firefox browser");

		} else if (testExecutionBrowser.equals(edgeBrowser)) {

			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver();
			log.info("Initiated IE/Edge browser");
		}

		driver.manage().window().maximize();
		log.info("Browser has been maximized");
		String url = properties.getProperty("url");
		driver.get(url);
		int impWait = Integer.parseInt(waitingTime);
		driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
		log.info(driver.getTitle());

	}

	public void waitForObjectsSync() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement checkElementPresentStatusByXpath(String expression) {


		for (int i = 0; i < 10; i++) {

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(expression)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expression)));
			boolean elementIsDisplayed = driver.findElement(By.xpath(expression)).isDisplayed();
			if (elementIsDisplayed == true) {
				break;
			}

		}

		WebElement element = driver.findElement(By.xpath(expression));
		return element;

	}

	public WebElement checkElementPresentStatusByID(String id) {


		for (int i = 0; i < 10; i++) {

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			boolean elementIsDisplayed = driver.findElement(By.id(id)).isDisplayed();
			if (elementIsDisplayed == true) {
				break;
			}

		}

		WebElement element = driver.findElement(By.id(id));
		return element;

	}

	public WebElement checkElementPresentStatusByName(String name) {


		for (int i = 0; i < 10; i++) {

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			boolean elementIsDisplayed = driver.findElement(By.name(name)).isDisplayed();
			if (elementIsDisplayed == true) {
				break;
			}

		}

		WebElement element = driver.findElement(By.name(name));
		return element;

	}

	public void getInputs() {

		try {
			String userdatapropertyPath = System.getProperty("user.dir") + "\\properties\\userdata.properties";
			properties = new Properties();
			FileInputStream readProperties = new FileInputStream(userdatapropertyPath);
			properties.load(readProperties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebElement notificationAlert() {

		checkElementPresentStatusByXpath("//snack-bar-container[@role='alert']/alert/div");
		WebElement notification = driver.findElement(By.xpath("//snack-bar-container[@role='alert']/alert/div"));
		return notification;
	}
	
	
	public void pressEscKey(){
		try {
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
