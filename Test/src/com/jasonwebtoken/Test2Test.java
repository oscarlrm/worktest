package com.jasonwebtoken;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Test2Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  public static void main(String args[]) {
	  
	  
      //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\oscar\\OneDrive\\Documents\\WebDrivers\\Chrome\\chromedriver.exe");
      	  System.setProperty("webdriver.gecko.driver","C:\\Users\\oscar\\OneDrive\\Documents\\WebDrivers\\FireFox\\geckodriver.exe");
	  
	  JUnitCore junit = new JUnitCore();
	  junit.addListener(new TextListener(System.out));
	  Result result = junit.run(Test2Test.class); 
	  if (result.getFailureCount() > 0) {
	    System.out.println("Test failed.");
	    System.exit(1);
	  } else {
	    System.out.println("Test finished successfully.");
	    System.exit(0);
	  }
	}
  
  
  @Before
  public void setUp() {
    //driver = new ChromeDriver();
	driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test2() throws InterruptedException {
	  
	//Open web site 1 
    driver.get("https://www.jsonwebtoken.io/");
    driver.manage().window().setSize(new Dimension(974, 1040));
    //Clear and write secret field
    driver.findElement(By.name("jwtKey")).clear();
    driver.findElement(By.name("jwtKey")).sendKeys("tlcN1D66yOs61shkfxWoVKCXC3qPgqzzx-nt_MhLUQETsZmrpcNwFh0HELxbcP7Y");
    Thread.sleep(5000);
    //Select and overwrite payload field 
    driver.findElement(By.cssSelector(".col-sm-8 > .panel")).click();
    driver.findElement(By.cssSelector(".CodeMirror-focused textarea")).sendKeys(Keys.CONTROL + "a");
    driver.findElement(By.cssSelector(".CodeMirror-focused textarea")).sendKeys("{\n" + 
    		" \"sub\": \"collage|143\",\n" + 
    		" \"name\": \"Johna Doe\",\n" + 
    		" \"email\": \"jarv078@gmail.com\",\n" + 
    		" \"admin\": true,\n" + 
    		" \"jti\": \"7e967728-e206-45de-9475-a755b9e9268f\",\n" + 
    		" \"iat\": 1608169369,\n" + 
    		" \"exp\": 1608173605\n" + 
    		"}");
    Thread.sleep(1000);
    //Get JWT field text
    String element = driver.findElement(By.id("jwtInput")).getAttribute("value");
    System.out.print(element);
    //Go to web site 2
    driver.get("https://live-staging.verb.tech/login/auth0?token=" + element);
    Thread.sleep(10000);
  }
  
  
}
