package TestRunner;

import java.time.Duration;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import webApp.CommonData;

public class SetupClass {
	public static WebDriver driver;
	public static String AppURL;
	public static Properties property = new Properties(System.getProperties());
	public static String browserName;
	public static Logger log;
	public static String Seleniumdriver;
	public static WebElement webelement;

	public static String browser;
	public Actions ac = new Actions(driver);
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	
	public static final String UserName = "rahulsharma_8RUv0Y";

	public static final String Automate_Key = "zg8foQC6mjQGoHx9CQJE";
	public static final String URL = "https://" + UserName + ":" + Automate_Key + "@hub-cloud.browserstack.com/wd/hub";

	@BeforeClass
	public static void before_Class() throws Exception {

		log = Logger.getLogger(BeforeClass.class.getName());
		property.load(new FileReader("config//config.properties"));
		AppURL = property.getProperty("App_url");

		// on source lab setup
		AppURL = property.getProperty("App_url");
		System.out.println("Bname=====" + AppURL);

		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
			browser = System.getenv("browser");
			System.out.println("env browser = " + browser);

		} else {
			browser = property.getProperty("browser");
			System.out.println("config browser = " + browser);
		}

		property.setProperty("browser", browser);

		System.out.println("setProperty browser = " + browser);

		if ((property.getProperty("browser").equals("chrome"))) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 50);
			js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
		}
		// if (browser.equalsIgnoreCase("firefox"))

		// if (browser.equalsIgnoreCase("chrome"))
		else if ((property.getProperty("browser").equals("firefox"))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 50);
			js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
		} else if ((property.getProperty("browser").equals("edge"))) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 50);
			js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
		}
		else if ((property.getProperty("browser").equals("safari"))) {

			// DesiredCapabilities caps = new DesiredCapabilities();

			/*
			 * caps.setCapability("browser", browser); caps.setCapability("browser_version",
			 * "104"); caps.setCapability("os", "windows"); caps.setCapability("os_version",
			 * "10"); caps.setCapability("resolution", "1920x1200");
			 */

			MutableCapabilities capabilities = new MutableCapabilities();
			capabilities.setCapability("browserName", "Safari");
			capabilities.setCapability("browserVersion", "15.0");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Monterey");
			//browserstackOptions.put("resolution", "1920x1080");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.141.59");
			capabilities.setCapability("bstack:options", browserstackOptions);

			try {
				driver = new RemoteWebDriver(new URL(URL), capabilities);
				wait = new WebDriverWait(driver, 30);
				js = (JavascriptExecutor) driver;
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			System.out.println("platform does not provide");
		}

	}

	public static WebElement visibilityofelement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				// Check for condition in every 2 seconds
				.pollingEvery(Duration.ofSeconds(2))
				// Till time out i.e. 30 seconds
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static Boolean waitForElementText(By locator, String text) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				// Check for condition in every 2 seconds
				.pollingEvery(Duration.ofSeconds(2))
				// Till time out i.e. 30 seconds
				.withTimeout(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.textToBe(locator, text));
	}

	@AfterClass

	public static void after_Class() {

		try {
			driver.quit();
			Thread.sleep(2000);
		} catch (Exception closeBrowser) {

		}

	}
}
