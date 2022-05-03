package TestRunner.loginSteps;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.String;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import TestRunner.SetupClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SignUp_Step extends SetupClass {
	
	WebDriverWait wait = new WebDriverWait(driver,50);
         JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@Given("^user is already on Website Home Page ii$")
	public void user_is_already_on_Website_Home_Page_ii() throws Throwable {
		driver.get("https://www.slidegeeks.com/");
		Thread.sleep(3000);
		driver.manage().deleteAllCookies();
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		Thread.sleep(3000);

		WebElement pricing = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Pricing']")));
		pricing.click();
		Thread.sleep(2000);
		WebElement Join_now = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"div[id='Individual'] form[name='hikashop_product_form_205548_hikashop_category_information_menu_117']")));

		Join_now.click();
		Thread.sleep(2000);

		WebElement name = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='site_signup_name']")));
		Thread.sleep(3000);
		name.sendKeys("Automated Program");
		Thread.sleep(3000);

		// Generate Random Email Address
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		System.out.println(generatedString);

		String signup_email = generatedString;
		String full_email = "selenium.testing." + generatedString + "@gmail.com";
		System.out.println(full_email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement new_email = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='site_signup_email']")));

		new_email.sendKeys(full_email);
		Thread.sleep(3000);

		WebElement password = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='site_signup_password']")));

		password.sendKeys("Geeks@123");

		WebElement captcha = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='captchtext']")));

		captcha.sendKeys("Y3Tt6bfwI");

		WebElement selectRadioBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='site_signup_checkbox']")));

		selectRadioBtn.click();

		WebElement register_btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='site_signup_btn']")));
		Thread.sleep(3000);
		register_btn.click();
		Thread.sleep(5000);
	}

	@Then("^Stripe Checkout$")
	public void Stripe_Checkout() throws Throwable {
		
		//driver.get("https://www.slidegeeks.com/subscriptions");
		//Thread.sleep(3000);
		// WebElement Join_now = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Monthly")));
		// Join_now.click();
		//Thread.sleep(5000);
		//String URL = driver.getCurrentUrl();
               // Assert.assertEquals(URL, "https://www.slidegeeks.com/component/pago/checkout");
		//System.out.println("URL matching --> Part executed");
		//Thread.sleep(5000);
		 try {
		Thread.sleep(2000);
		// select stripe option
             WebElement cp_btn  = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
		//WebElement co_btn  =  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#pg-checkout-billing-payment-form > div > div:nth-child(1) > label")));
	        cp_btn.click();
		Thread.sleep(2000);
	     } catch( NoSuchElementException popup) { 
	     }
		
		// place order button 
		try {
			
		 WebElement place_order_btn  = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#hikabtn_checkout_next")));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();",place_order_btn);	
			//js.executeScript("arguments[0].click();", place_order_btn);
			Thread.sleep(1000);
		    place_order_btn.click();
			Thread.sleep(3000);
		} catch (NoSuchElementException popup) {
		}
		
	
	}
	@Then("^Stripe Checkout Redirection$")
	public void Stripe_Checkout_Redirection() throws Throwable {
	 String stripe_page_title=driver.getTitle();
		Thread.sleep(3000);
	    System.out.println("Title of the Page is --> "+stripe_page_title);
	    
	    String page_title="https://checkout.stripe.com/";
	    
	    if(page_title.equalsIgnoreCase(stripe_page_title))
	    {
	    	System.out.println(" user is on the Stripe page");
	    	log.info("USER IS ON THE STRIPE PAGE");
	    }
	    else
	    {
	    	System.out.println("user is on the wrong page");
	    	log.info("USER IS ON THE WRONG PAGE");
	    }	
		
		Thread.sleep(1000);
		 WebElement Stripe_email = driver.findElement(By.cssSelector("#email"));
		 Stripe_email.sendKeys("slidetech.qa@gmail.com");
		Thread.sleep(2000);
		 WebElement Stripe_back = driver.findElement(By.cssSelector("#root > div > div > div.App-Overview > header > div > div > a > div > div > div.Header-backArrowContainer > svg"));
		Thread.sleep(2000);
			Stripe_back.click();
		Thread.sleep(5000);
		//driver.get("https://www.slidegeeks.com/component/pago/checkout");
		//Thread.sleep(2000);
		

                 WebElement Account = driver.findElement(
				By.xpath("/html/body/div[1]/header/div/div/nav/div/div[2]/div[2]/div[2]/div/div[2]/ul/li[1]/a"));
		Thread.sleep(3000);
		Account.click();
		Thread.sleep(3000);
		WebElement Delete_Account = driver
				.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div/div[2]/div/ul/li[6]/a"));
		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView();", Delete_Account);
		Thread.sleep(3000);
		Delete_Account.click();
		Thread.sleep(3000);
		WebElement Delete_Account_reason = driver.findElement(By.cssSelector("#only-free-download-product"));
		Thread.sleep(3000);
		Delete_Account_reason.click();
		Thread.sleep(3000);
		WebElement Delete_Profile = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[3]/button[1]"));
		Thread.sleep(3000);
		Delete_Profile.click();
		Thread.sleep(3000);
		WebElement No_Delete = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[3]/button[2]"));
		Thread.sleep(3000);
		No_Delete.click();
		Thread.sleep(7000);
		Thread.sleep(7000);

		String verifyDeleteAccountMessage = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='alert-message login-sucesmsg']")))
				.getText();
		System.out.println("verifyText1 = " + verifyDeleteAccountMessage);
		Assert.assertTrue("Your are not on paypal page",
				verifyDeleteAccountMessage.contentEquals("Your Account has been deleted successfully."));
		Thread.sleep(3000);						    
	}

}
