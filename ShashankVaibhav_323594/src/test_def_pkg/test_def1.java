package test_def_pkg;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class test_def1 {
	WebDriver dr;
	WebDriverWait wait;
	Logger log;
	String valid_email = "abcz450@gmail.com";
	String invalid_email = "abcz45@gmail.com";
	
	@Given("^Launched the browser$")
	public void launched_the_browser() throws Throwable {
	    System.setProperty("webdriver.chrome.driver", "chromedriver_version_75.exe");
	    dr = new ChromeDriver();
	    dr.manage().window().maximize();
	    log = log.getLogger("devpinoyLogger");
	    log.info("TCID: launched_the_browser()");
	}

	@Given("^DemoWebShop page is displayed$")
	public void demowebshop_page_is_displayed() throws Throwable {
		dr.get("http://demowebshop.tricentis.com/");
		log.info("WebPage launched");
		log.info("TCID: demowebshop_page_is_displayed()");
	}

	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		wait = new WebDriverWait(dr,20);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Log in')]")));
	    dr.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
	    log.info("clicked on Login");
	    log.info("TCID: click_on_Login()");
	}

	@When("^provide valid login credentials$")
	public void provide_valid_login_credentials() throws Throwable {
	    dr.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(valid_email);
	    dr.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("abcdef");
	    wait = new WebDriverWait(dr,20);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value=\"Log in\"]")));
	    dr.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
	    log.info("Login Credential is provided");
	    log.info("TCID: provide_valid_login_credentials()");
	}
	
	@Then("^Verify the expected email$")
	public void verify_the_expected_email() throws Throwable {
		String actual = dr.findElement(By.xpath("//a[contains(text(),'"+ valid_email +"')]")).getText();
	    if(actual.equals(valid_email)) {
	    	log.info("\nExpected Result :"+valid_email+"\nACTUAL RESULT :"+actual+"\nTEST_RESULT : PASS");
	    }else{
	    	log.info("\nExpected Result :"+valid_email+"\nACTUAL RESULT :"+actual+"\nTEST_RESULT : FAIL");
	    }
	    log.info("TCID: verify_the_expected_email()\n");
	}

	@When("^provide invalid login credentials$")
	public void provide_invalid_login_credentials() throws Throwable {
		dr.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(invalid_email);
	    dr.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("abcdef");
	    dr.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
	    log.info("Login Credential is provided");
	    log.info("TCID: provide_invalid_login_credentials()");
	}

	@Then("^Verify the expected error$")
	public void verify_the_expected_error() throws Throwable {
	    String actual = dr.findElement(By.xpath("//div[@class = \"validation-summary-errors\"]")).getText();
	    String expected = "Login was unsuccessful. Please correct the errors and try again.\n" + 
	    		"No customer account found";
	    //System.out.println(actual+"\n"+expected);
	    if(actual.equals(expected)) {
	    	log.info("\nExpected Result :"+expected+"\nACTUAL RESULT :"+actual+"\nTEST_RESULT : PASS");
	    }
	    else{
	    	log.info("\nExpected Result :"+expected+"\nACTUAL RESULT :"+actual+"\nTEST_RESULT : FAIL");
	    }
	    log.info("TCID: verify_the_expected_error()\n");
	}
}
