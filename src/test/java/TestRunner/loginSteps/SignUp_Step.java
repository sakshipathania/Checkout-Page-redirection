package TestRunner.loginSteps;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestRunner.SetupClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SignUp_Step extends SetupClass {

	WebDriverWait wait = new WebDriverWait(driver, 50);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Given("^user is already on Website Home Page ii$")
	public void user_is_already_on_Website_Home_Page_ii() throws Throwable {
		driver.get("https://www.slidegeeks.com/");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.manage().deleteAllCookies();
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(4000);

		Thread.sleep(3000);

		WebElement pricing = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Pricing']")));
		pricing.click();
		Thread.sleep(5000);
		WebElement Join_now = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"div[id='Individual'] form[name='hikashop_product_form_205548_hikashop_category_information_menu_117']")));

		Join_now.click();
		Thread.sleep(5000);

		WebElement signup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='signupclass']")));

		signup.click();
		Thread.sleep(5000);

		/*
		 * WebElement name = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='site_signup_name']"))); Thread.sleep(3000);
		 * name.sendKeys("Automated Program"); Thread.sleep(3000);
		 */

		// without pop-up
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_name")));
		Thread.sleep(3000);
		name.sendKeys("Automated Program");

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

		/*
		 * WebElement new_email = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='site_signup_email']")));
		 * 
		 * new_email.sendKeys(full_email); Thread.sleep(3000);
		 * 
		 * WebElement password = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='site_signup_password']")));
		 * 
		 * password.sendKeys("Geeks@123");
		 * 
		 * WebElement captcha =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='captchtext']")));
		 * 
		 * captcha.sendKeys("Y3Tt6bfwI");
		 * 
		 * WebElement selectRadioBtn = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//input[@id='site_signup_checkbox']")));
		 * 
		 * selectRadioBtn.click();
		 * 
		 * WebElement register_btn = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//button[@id='site_signup_btn']"))); Thread.sleep(3000);
		 * register_btn.click(); Thread.sleep(5000);
		 */

		// without pop-up

		WebElement new_email = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_email")));
		Thread.sleep(3000);
		new_email.sendKeys(full_email);
		Thread.sleep(3000);

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_password")));
		Thread.sleep(3000);
		password.sendKeys("Geeks@123");
		Thread.sleep(3000);

		WebElement confirm_passwoed = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#register_password2")));
		Thread.sleep(3000);
		confirm_passwoed.sendKeys("Geeks@123");
		Thread.sleep(3000);

		WebElement captcha = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#captchtext")));
		Thread.sleep(3000);
		captcha.sendKeys("Y3Tt6bfwI");
		Thread.sleep(3000);

		WebElement register_btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pg-register-button-new")));
		Thread.sleep(3000);
		register_btn.click();
		Thread.sleep(5000);
	}

	@Then("^Stripe Checkout$")
	public void Stripe_Checkout() throws Throwable {
		try {
			Thread.sleep(2000);
			// select stripe option
			WebElement cp_btn = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
			cp_btn.click();
			Thread.sleep(2000);
		} catch (NoSuchElementException popup) {
		}

		// place order button
		try {

			WebElement place_order_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#hikabtn_checkout_next")));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", place_order_btn);
			// js.executeScript("arguments[0].click();", place_order_btn);
			Thread.sleep(1000);
			place_order_btn.click();
			Thread.sleep(7000);
		} catch (NoSuchElementException popup) {
		}

	}

	@Then("^Stripe Checkout Redirection$")
	public void Stripe_Checkout_Redirection() throws Throwable {
		String stripe_page_title = driver.getTitle();
		System.out.println("Title of the Page is --> " + stripe_page_title);

		String page_title = "https://checkout.stripe.com/";

		if (page_title.equalsIgnoreCase(stripe_page_title)) {
			System.out.println(" user is on the Stripe page");
			log.info("USER IS ON THE STRIPE PAGE");
		} else {
			System.out.println("user is on the wrong page");
			log.info("USER IS ON THE WRONG PAGE");
		}

		WebElement Stripe_email = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#email")));
		Stripe_email.sendKeys("slidetech.qa@gmail.com");
		Thread.sleep(2000);
		WebElement Stripe_back = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"#root > div > div > div.App-Overview > header > div > div > a > div > div > div.Header-backArrowContainer > svg")));
		Thread.sleep(2000);

		Stripe_back.click();

		if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		} else {
			System.out.println("Alert exists");
		}

		Thread.sleep(4000);
		// driver.get("https://www.slidegeeks.com/component/pago/checkout");
		// Thread.sleep(2000);

		WebElement Account = driver.findElement(By.xpath("//a[normalize-space()='Account']"));
		Thread.sleep(3000);
		Account.click();
		Thread.sleep(3000);
		WebElement Delete_Account = driver.findElement(By.xpath("//a[normalize-space()='Delete Account']"));
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
