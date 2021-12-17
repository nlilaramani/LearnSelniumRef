/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.sel;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author itexps
 */
public class GoogleTest {
    
    public GoogleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    // instance  variable for storing driver
    WebDriver driver;
    @Before
    public void setUp() {
        // setup chrome driver
        System.setProperty("webdriver.chrome.driver", "c:\\qa\\drivers\\chromedriver.exe");
        // launch browser
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        // Default timeout to 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }    
    @After
    public void tearDown() {
        // Close the browser window after the test
        //driver.quit();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testSearch() throws InterruptedException{
        driver.get("http://www.google.com/");
        // Find element with a name property which has value=q
        // Wait for 5 seconds
        
        
        WebElement e=driver.findElement(By.name("q"));
        
        // type in the text box
        e.sendKeys("selenium jobs");
        // submit the information
        e.submit();
        // Store screenshot
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        src.renameTo(new File("c:\\qa\\searchimage.png"));
        // Verify the results page displayed
        assertTrue(driver.getTitle().contains("selenium jobs"));
    }
    @Test
    public void testNavigation(){
        // Goto site using navigation object
        driver.navigate().to("http://www.google.com");
        driver.navigate().to("http://www.ibm.com");
        // Load previous site
        driver.navigate().back();
        // Load next site
        driver.navigate().forward();
        //Refresh current page
        driver.navigate().refresh();
        
    }
    @Test
    public void testLocators(){
        driver.get("https://nlilaramani.github.io/");
        driver.findElement(By.partialLinkText("User Registration")).click();
        driver.findElement(By.id("fname")).sendKeys("Robert");
        driver.findElement(By.id("fname")).sendKeys("Robert");
        driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Robert");
        driver.findElement(By.name("lname")).sendKeys("Daley");
        driver.findElement(By.cssSelector("#username")).sendKeys("test");
        driver.findElement(By.className("pwd")).sendKeys("testpwd");
        //driver.findElements(By.name("g")).get(1).click();
        //driver.findElements(By.name("g")).get(1).click();
        driver.findElements(By.xpath("//input[@name='g']")).get(1).click();
        if(!driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected()){
            driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        }
        WebElement e=driver.findElement(By.tagName("select"));
        Select dropdown=new Select(e);
        dropdown.selectByIndex(1);
        
        //driver.findElement(By.tagName("select")).sendKeys("Bachelors");
        //driver.findElement(By.xpath("//input[@type='submit']")).click();
        
    }
    @Test
    public void testCss(){
        driver.get("https://www.facebook.com");
        driver.findElement(By.cssSelector("input.inputtext")).sendKeys("softwate");
    }
    @Test
    public void testHotmail(){
        driver.get("http://www.hotmail.com"); //Got to hotmail home page
        driver.findElement(By.partialLinkText("Sign in")).click(); // click on signin link
        driver.findElement(By.id("i0116")).sendKeys("SelSample@hotmail.com");
        WebDriverWait wait=new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        driver.findElement(By.id("idSIButton9")).click();
        wait=new WebDriverWait(driver,15);
        //WebElement e=driver.findElement(By.name("passwd"));
        //wait.until(ExpectedConditions.stalenessOf(e));
        //wait.until(ExpectedConditions.visibilityOf(e));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("passwd")));
        WebElement e=driver.findElement(By.name("passwd"));
        
        e.clear();
        e.sendKeys("Sel@123!");
    }
    
    @Test
    public void testAlert() throws InterruptedException{
        driver.get("https://nlilaramani.github.io/frame.html");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
    }
}
