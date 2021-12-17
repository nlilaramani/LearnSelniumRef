/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author itexps
 */
public class GoogleTest {
    
    WebDriver driver;
    
    public GoogleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver","c:\\qa\\drivers\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver","c:\\qa\\drivers\\geckodriver.exe");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        driver=new ChromeDriver();
        //driver=new FirefoxDriver();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testSearch() throws InterruptedException{
        driver.get("http://www.google.com");
        WebElement e=driver.findElement(By.name("q"));
        e.sendKeys("Selenium jobs");
        e.submit();
       System.out.println(driver.getTitle());
       File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     srcFile.renameTo(new File("c:\\qa\\screenshot.png"));
       System.out.println(srcFile.getAbsoluteFile());
        assertTrue(driver.getTitle().contains("Selenium jobs"));
    }
    
    @Test
    public void testFacebookLogin() throws InterruptedException{
        driver.get("http://www.facebook.com");
        driver.findElement(By.name("email")).sendKeys("joe@email.com");
        driver.findElement(By.id("pass")).sendKeys("abc");
        driver.findElement(By.name("login")).click();
        driver.wait(10000);
        driver.close();
    }
    
    @Test
    public void testHotmailLogin() throws InterruptedException{
        driver.get("http://www.hotmail.com"); //Got to hotmail home page
        driver.findElement(By.partialLinkText("Sign in")).click(); // click on signin link
        driver.findElement(By.id("i0116")).sendKeys("SelSample@hotmail.com");
        driver.findElement(By.id("idSIButton9")).click();
        Thread.currentThread().sleep(5000);
        //driver.wait(5000);
        WebElement e=driver.findElement(By.name("passwd"));
        e.clear();
        e.sendKeys("Sel@123!");
        e.submit();
    }
    
    @Test
    public void testUserprofile(){
        driver.get("https://nlilaramani.github.io/");
        driver.findElement(By.partialLinkText("User Registration")).click();
        driver.findElement(By.id("fname")).sendKeys("Robert");
        driver.findElement(By.name("lname")).sendKeys("Illiffe");
        //driver.findElement(By.id("username")).sendKeys("test");
        driver.findElement(By.cssSelector("#username")).sendKeys("test");
        //driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.className("pwd")).sendKeys("password");
        driver.findElements(By.name("g")).get(0).click();
        driver.findElement(By.tagName("select")).sendKeys("Bachelors");
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
       
        //driver.get("http://www.ibm.com");
        
        
    }
     @Test
    public void testPopups() throws InterruptedException{
        driver.get("https://nlilaramani.github.io/frame.html");
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
    }
    
    @Test
    public void testNewTabs(){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs=new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        //driver.switchTo().frame("iframe_1");
        driver.get("http://www.ibm.com");
    }
    
    @Test
    public void testDataDrivenLogin() throws FileNotFoundException, IOException, InterruptedException{
        FileInputStream fs=new FileInputStream(new File("c:\\qa\\aa.xlsx"));
        Workbook wb=new XSSFWorkbook(fs);
        Sheet sh=wb.getSheetAt(0);
        driver.get("http://advantageonlineshopping.com/#/");
        Thread.sleep(5000);
        for(int i=1;i<=2;i++){
        driver.findElement(By.id("hrefUserIcon")).click();
        String uname=sh.getRow(i).getCell(0).getStringCellValue();
        String pwd=sh.getRow(i).getCell(1).getStringCellValue();
        driver.findElement(By.name("username")).sendKeys(uname);
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.id("sign_in_btnundefined")).click();
        WebDriverWait wait=new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuUserLink")));

        driver.findElement(By.xpath("//div[@id='loginMiniTitle']/label[3]")).click();
        Thread.sleep(5000);
        }
        fs.close();
    }
    
}
