package org.Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TimeBombSkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base implements UatBase{

    private String  nameChanger= dateTime();
	public static WebDriver driver;
    public static Properties prop = new Properties();
    public static FileReader fr;


    // String URL = "https://release.ilearningengines.com/login";
    // String URL = "https://www.google.com";

    // wait
    public static WebDriverWait wait;


    public static ExtentReports extent;

    @BeforeSuite
    public void htmlReport() {
        // blank html
        ExtentSparkReporter reporter = new ExtentSparkReporter("./testReport"+nameChanger+".html");

        // report
        extent = new ExtentReports();

        // attaching html to report
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void startApp() throws FileNotFoundException {
        /*
         * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
         * driver.manage().window().maximize(); wait = new WebDriverWait(driver,
         * Duration.ofSeconds(10));
         * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
         * driver.get(URL);
         */

        if (driver == null) {

            FileReader fr = new FileReader(
                    "./configFiles/Base/base.properties");
            try {
                prop.load(fr);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            System.out.println(prop.getProperty("browser"));
            driver = new ChromeDriver();
            driver.get(prop.getProperty("login.url"));
            driver.manage().window().maximize();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            System.out.println(prop.getProperty("browser"));
            driver = new FirefoxDriver();
            driver.get(prop.getProperty("url"));
            driver.manage().window().maximize();

        }


    }

    @AfterMethod
    public void tearDown() {


        driver.quit();

    }

    @AfterSuite
    public void afterHtmlReport() {
        extent.flush();
    }

    //Wait method using css
    public void waitMethod(String path) {
        try{
            wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
        }/**catch (TimeoutException e){
            screenShot("ElementNotFound");
            extentFail(Thread.currentThread()

                    .getStackTrace()[3]
                    .getMethodName(),"Failed to locate element{--"+ path+"-- } :<br> Please check css or else element you are looking is not in the DOM <br>"+e,"ElementNotFound");

        }**/
        catch (Exception e){
            //extentWarning();
            e.printStackTrace();
            screenShot("ElementNotFound");
            extentFail(Thread.currentThread()

                    .getStackTrace()[3]
                    .getMethodName(),"Failed to locate element{--"+ path+"-- } :<br> Please check css or else element you are looking is not in the DOM <br>"+e,"ElementNotFound");
        }

    }

    //Wait using xpath
    public void xpathWaitMethod(String path) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
    }



    //Locator using css
    public WebElement locator(String path) {

   waitMethod(path);

        return  driver.findElement(By.cssSelector(path));
    }

    //Loctaor using xpath
    public WebElement xpathLocator(String path) {
        xpathWaitMethod(path);
        return driver.findElement(By.xpath(path));
    }

    // Click functionality
    public void click(WebElement ele) {

        ele.click();
    }

    //click by css
    public void clickCss(String css) {
        WebElement element = locator(css);
        element.click();
    }

    public boolean isDisplayed(WebElement ele) {
        // WebElement element = webElementWait(ele);
        return ele.isDisplayed();
    }

    public void visibilityCheck(WebElement ele) {

        Assert.assertTrue(ele.isDisplayed());
        System.out.println("Element is visible");
    }

    //visibility check
    public void visibilityCheck(WebElement ele, String msg) {

        Assert.assertTrue(ele.isDisplayed(), msg + " Element is not displayed");
        System.out.print("Element is displayed " + msg);
    }

    //sleep
    public void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Screen shot
    public void screenShot(String fileName) {
        File firstSource = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenShot/" + fileName +nameChanger+ ".png");
        try {
            FileHandler.copy(firstSource, dest);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //Screenshot of a particular element
    public void screenShotElement(String elementPath,String fileName){
        WebElement element = locator(elementPath);
// capture screenshot with getScreenshotAs()
        File f = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f, new File("./screenShot/" + fileName+nameChanger + ".png"));
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Send keys using web-element
    public void elementInput(WebElement ele, String input) {
        ele.clear();
        ele.sendKeys(input);
    }

    //Send keys using direct path
    public void elementInput(String path, String input) {
        WebElement element = locator(path);
        element.clear();
        element.sendKeys(input);
    }

    public void xpathElementInput(String path, String input) {
        WebElement element = xpathLocator(path);
        element.clear();
        element.sendKeys(input);
    }

    //Assert check
    public void assertEqualCheck(String actual, String expected) {

        try {
            Assert.assertEquals(actual, expected);
            ExtentTest test = extent.createTest("PositiveLoginTest");
            test.pass("Positive Login Test with assert");
            test.assignAuthor("Yethin");
        } catch (Exception e) {
            ExtentTest test = extent.createTest("PositiveLoginTest");
            test.fail("Positive Login Test with assert");
            test.assignAuthor("Yethin");
        }

    }

    // Get Title
    public String getTitle() {

        return driver.getTitle();
    }

    //Config properties
    public String propertyRead(String path, String key) {
        try {
            FileReader fr = new FileReader("./configFiles/" + path);
            try {
                prop.load(fr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String keyValue = prop.getProperty(key);
        return keyValue;

    }

    /*public void extentPass(String testName, String text, String author) {
        ExtentTest test = extent.createTest(testName);
        test.pass(text);
        test.assignAuthor(author);
    }*/

    /*public void extentFail(String testName, String text, String author) {
        ExtentTest test = extent.createTest(testName);
        test.fail(text);
        test.assignAuthor(author);
    }*/

    //For one sheet
    /*public static String[][] excelFileRead(String fileName) {
        /*
         * XSSFSheet  for  selecting sheet from excel-sheet index of 0 indicate first sheet
         * sheet-getLastRoeNum(); for getting lastRowNum
         *
         * for loop for iterating row and cell
         * first loop for row
         * second loop for cell
         *
         */
        /*XSSFWorkbook wbook = null;
        try {
            wbook = new XSSFWorkbook("./TestData/" + fileName + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = wbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        short lastCellNum = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[lastRowNum][lastCellNum];

        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter dft = new DataFormatter();
                dft.formatCellValue(cell);
                String value = cell.getStringCellValue();
                data[i - 1][j] = value;
            }
        }
        try {
            wbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;


    }*/

    public void quickTeacherLogin() {

        //Username input
        String userName = propertyRead("Common/login.properties", "validUsername");
        String usernamePath = propertyRead("Common/login.properties", "userName");
        elementInput(usernamePath, userName);

        //Password input
        String userPassword = propertyRead("Common/login.properties", "validPassword");
        String passwordPath = propertyRead("Common/login.properties", "userPassword");
        elementInput(passwordPath, userPassword);
        //login click
        String login = propertyRead("Common/login.properties", "loginButton");
        clickCss(login);

    }

    /**
     * @param fileName:   name of the Excel file
     * @param sheetNumber : sheet number of the Excel
     * @return 2dimensional array of string
     */

    public static String[][] excelFileRead(String fileName, int sheetNumber) {
        /*
         * XSSFSheet  for  selecting sheet from excel-sheet index of 0 indicate first sheet
         * sheet-getLastRoeNum(); for getting lastRowNum
         *
         * for loop for iterating row and cell
         * first loop for row
         * second loop for cell
         *
         */
        XSSFWorkbook wbook = null;
        try {
            wbook = new XSSFWorkbook("./TestData/" + fileName + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = wbook.getSheetAt(sheetNumber - 1);

        int lastRowNum = sheet.getLastRowNum();
        short lastCellNum = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[lastRowNum][lastCellNum];

        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter dft = new DataFormatter();
                dft.formatCellValue(cell);
                String value = cell.getStringCellValue();
                data[i - 1][j] = value;
            }
        }
        try {
            wbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;


    }

    /**
     * This method is used to select dynamic dropdown
     *
     * @param sub    : web-element of the dropdown
     * @param option : dropdown option you want to select
     */
    public void dropdown(WebElement sub, String option) {

        Actions keyDown = new Actions(driver);
        String actualOption = "";
        for (int i = 0; i < 100; i++) {
            try{
                actualOption = sub.getText().replace(" ", "");
                System.out.println(actualOption);
            }catch (Exception e){
                actualOption = sub.getText().replace(" ", "");
                System.out.println(actualOption);
            }
            if (actualOption.equalsIgnoreCase(option.replace(" ", ""))) {
                break;
            }
            else if (i>60) {
              extentFail("dropdown","no option found ");
              assertEqualCheck(sub.getText(),option);
                break;
            }

            sleep(10);
            sub.click();
            keyDown.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER)).perform();
            System.out.println("." + sub.getText() + "/" + option);


        }
    }

    /**
     * Function for extent-report- pass
     *
     * @param testName : name for the test
     * @param text     : message for passing test
     */

    public void extentPass(String testName, String text) {
        ExtentTest test = extent.createTest(testName);
        test.pass(text + " " + propertyRead("Common/extent-report.properties", "msg"));
        test.assignAuthor(propertyRead("Common/extent-report.properties", "author"));
    }

    /**
     * Function for extent-report - fail
     *
     * @param testName : name for the test
     * @param text     : message for passing test
     */


    public void extentFail(String testName, String text) {
        ExtentTest test = extent.createTest(testName);
        test.fail(text);
        test.assignAuthor(propertyRead("Common/extent-report.properties", "author"));
    }

    /**
     * Function for extent-report- pass with screenshot Addition
     *
     * @param testName             : name for the test
     * @param text                 : message for passing test
     * @param screenshot_file_name : name of screenshot
     */
    public void extentPass(String testName, String text, String screenshot_file_name) {
        ExtentTest test = extent.createTest(testName);
        System.out.println("inside pass");
        test.pass(text + " " + propertyRead("Common/extent-report.properties", "msg"));
        test.assignAuthor(propertyRead("Common/extent-report.properties", "author"));
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath("./screenShot/" + screenshot_file_name +nameChanger+ ".png").build());

    }

    //Extent warning
    public void extentWarning(String testName, String text, String screenshot_file_name){
        ExtentTest test = extent.createTest(testName);
        test.warning(text + " " + propertyRead("Common/extent-report.properties", "msgWarning"));
        test.assignAuthor(propertyRead("Common/extent-report.properties","author"));
        test.warning(MediaEntityBuilder.createScreenCaptureFromPath("./screenShot/" + screenshot_file_name+nameChanger + ".png").build());
    }

    /**
     * Function for extent-report- fail with screenshot Addition
     *
     * @param testName             :  name for the test
     * @param text                 :  message for fail test
     * @param screenshot_file_name : name of screenshot
     */
    public void extentFail(String testName, String text, String screenshot_file_name) {
        ExtentTest test = extent.createTest(testName);
        test.fail(text + " " + propertyRead("Common/extent-report.properties", "msgFail"));
        test.assignAuthor(propertyRead("Common/extent-report.properties", "author"));
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath("./screenShot/" + screenshot_file_name + nameChanger+".png").build());
    }
    //Previous report date and time
    public String dateTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-hh_mm_ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    /**
     *
     * @param propertyName: name of the filterBoc inside property file
     * @param searchContent : input need to search
     */
    public void applyFilter(String propertyName,String searchContent){

        String filterElement = propertyRead("Module.properties", "moduleFilter");
        sleep(2000);
        clickCss(filterElement);

        String applySearch = propertyRead("Module.properties", propertyName);
        //Entering search content
        elementInput(applySearch, searchContent);
        String applyFilter = propertyRead("Module.properties", "applyFilter");
        clickCss(applyFilter);


    }


}
