package TestRunner.loginSteps;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepository.LoginObject;
import ObjectRepository.SignupObject;
import TestRunner.SetupClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import webApp.PerformAction;

public class Email_Signin_paidUser_stepDefinition extends SetupClass {
//git change
	
	PerformAction wait = new PerformAction();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Random rad = new Random();

	// Open web site URl
	@Given("^Open application URL$")
	public void navigates_to_website_url() throws InterruptedException {
		// Maximize Windows
		driver.get("https://www.slideteam.net");
		Thread.sleep(5000);
		try {
			WebElement App_url= driver.findElement(SignupObject.close_add);
			App_url.click();
			Thread.sleep(5000);
			log.info("It's opening the website URL");
		} catch (NoSuchElementException popup) {
		}

		try {
			WebElement logout = driver.findElement(By.cssSelector(".signin-link[title='Sign Out']"));
			if (logout.isEnabled()) {
				logout.click();
				Thread.sleep(8000);
				driver.navigate().refresh();
				Thread.sleep(2000);
			}
		} catch (NoSuchElementException Ext) {

		}
	}

	
	@Then("^Enter a user email$")
	public void enter_user_email_address_as() throws Throwable {
                try {
		WebElement Email_add= driver.findElement(By.xpath("//*[@id='email']"));
		Email_add.click();
		Thread.sleep(5000);
		//wait.implictywait(driver);
		Email_add.clear();
		Thread.sleep(5000);
		//wait.implictywait(driver);
		Email_add.sendKeys("sakshi.pathania@slideteam.in");
		//wait.implictywait(driver);
                Thread.sleep(5000);
	        } catch (NoSuchElementException Ext) {

		}
	}

	@Then("^Enter the user password$")
	public void enter_user_password_as() throws Throwable {
		try {
		WebElement user_pass = driver.findElement(By.cssSelector("div.field:nth-child(3) > div:nth-child(2) > input:nth-child(1)"));
		user_pass.click();
		Thread.sleep(5000);
		//wait.implictywait(driver);
		user_pass.clear();
		Thread.sleep(5000);
		//wait.implictywait(driver);
		user_pass.sendKeys("Sakshi@098");
		//wait.implictywait(driver);
		Thread.sleep(5000);
		 } catch (NoSuchElementException Ext) {

		}

	}

	@Then("^click on Login cta$")
	
	public void click_on_Login_button() throws Throwable {
		try {
		WebElement Login_button= driver.findElement(By.xpath("//*[@id='send2']"));
		//wait.implictywait(driver);
		Thread.sleep(5000);
		Login_button.click();
		//wait.implictywait(driver);
		Thread.sleep(5000);
		}
                   catch (NoSuchElementException Ext) {
			
			}
		try {
			WebElement close = driver.findElement(By.cssSelector(".fancybox-item.fancybox-close"));
			//wait.implictywait(driver);
			close.click();
			Thread.sleep(500);
		} catch (NoSuchElementException nonpop) {

		}
	}
        
	@Then("^user is navigate to complete deck from account dashboard page$")
         public void user_is_navigating_to_complete_deck_from_account_dashboard_page() throws InterruptedException  {
    
	 driver.get("https://www.slideteam.net/complete-powerpoint-decks-presentations/all-powerpoint-complete-decks.html");
	 Thread.sleep(3000);
	 
	// WebElement select_product= driver.findElement(By.cssSelector("li.product:nth-child(1) > div:nth-child(1) > div:nth-child(2) > strong:nth-child(1) > span:nth-child(1) > a:nth-child(1)"));
	  //Thread.sleep(5000);   
	 //select_product.click();
	   // Thread.sleep(5000);
          }
	
	@Then ("^chat window option\\.$")
	public void close_chat_window() throws InterruptedException {
		try {
			WebElement iframe = driver.findElement(By.id("livechat-full-view"));
			if(iframe.isDisplayed()) {
				driver.switchTo().frame(iframe);   
				 Actions act = new Actions(driver);
				 act.moveToElement(driver.findElement(By.cssSelector("#title .icon-minimize"))).build().perform();
				 Thread.sleep(2000);
					WebElement chat1=driver.findElement(By.cssSelector("#title .icon-minimize"));
					 Thread.sleep(1000);
						chat1.click();
						 Thread.sleep(1000);
						 driver.switchTo().defaultContent();
						 Thread.sleep(1000);
						 driver.switchTo().parentFrame();
					 Thread.sleep(1000);
			}
			else {
				

			System.out.println("chat window does not open");
			}
		}
				catch(NoSuchElementException NCP) {
					
				}
			}	

	@Then("^the download this presenetion link is appeared$")
	public void verify_uesr_validation_message_for_Email_Address() throws Throwable {
                driver.get("https://www.slideteam.net/one-page-strategy-vision-goals-strategies-tactics.html");
		try {
			String Email_test = driver.findElement(SignupObject.Downloaded).getText();
			//wait.implictywait(driver);
			Thread.sleep(5000);
			String Expected_Cta = "Download this presentation";
			Assert.assertEquals(Expected_Cta, Email_test);
			//wait.implictywait(driver);
			Thread.sleep(1000);
		} catch (NoSuchElementException er) {

		}
	}
	
        @Then("^Click on download this presentation link$")
	public void click_on_Download_this_presentation_link() throws InterruptedException {
		WebElement presentation= driver.findElement(By.cssSelector("#clicking"));
		//wait.implictywait(driver);
		Thread.sleep(5000);
		presentation.click();
		//wait.implictywait(driver);
		Thread.sleep(8000);
	}
}

