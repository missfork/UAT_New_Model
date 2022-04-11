package org.Teacher.pages;

import org.Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Common extends Base {
    // pageLoad
    // Javascript executor to return value


    public boolean clearCheck(){
        boolean flag = false;
        if (empty(css("chapter.name"))&&empty(css("chapter.shortName"))&&empty(css("chapter.description"))) {
        String std = locator(css("chapter.standard")).getText();
        String sub = locator(css("chapter.subject")).getText();

       flag= (std.equals(css("standardInnerText"))&&sub.equals(css("subjectInnerText")));

     }
else {
         screenShot("clearBtnFailed");
         extentFail("clearBtnDisabled","clear button failed ","clearBtnFailed");
     }
return flag;


    }

    public boolean urlCheck(String url){
        return driver.getCurrentUrl().equals(url);
    }

  public boolean breadCrumpsCheck(String expected){

        boolean flag=false;
      String bread="";

      List<WebElement> crumps = driver.findElements(By.cssSelector(propertyRead("Teacher/commonFile.properties","common.breadcrumbs")));
      for(WebElement e : crumps) {
          // bread is the value of the breadcrump
          bread =e.getText();
          System.out.println("Actual = "+bread);

          // check whether actual value of the breadcrump is same a the expexted value
          if(bread.equals(expected))
          {
              flag= true;
              System.out.println("flag inside if = "+flag);
          }


      }
      if (!flag) {

          extentFail("breadCrumpsCheckTest","Breadcrumbs fails: <br>"+"expected_crumbs : "+expected +"<br>"+ "actual_crumbs : "+bread);

      }
      System.out.println("flag = "+flag);

      System.out.println("========================================================================================================================================================================================================================================");
      return flag;
  }







    public void quickTeacherLogin2() {

        //Username input
        String userName = propertyRead("Common/login.properties", "validUsername2");
        String usernamePath = propertyRead("Common/login.properties", "userName");
        elementInput(usernamePath, userName);

        //Password input
        String userPassword = propertyRead("Common/login.properties", "validPassword2");
        String passwordPath = propertyRead("Common/login.properties", "userPassword");
        elementInput(passwordPath, userPassword);
        //login click
        String login = propertyRead("Common/login.properties", "loginButton");
        clickCss(login);

    }










    // moving cursor to the element
  public void mousePoint(String cssText){

      Actions actionProvider = new Actions(driver);
      actionProvider.moveToElement(locator(css(cssText))).build().perform();
  }





// return string
    private String css(String key){

        return propertyRead("Teacher/chapter.properties",key);
    }




    // return true if it is empty text
    private boolean empty(String css){

       return locator(css).getText().isEmpty();
    }






    //return true if two String are equals

    private boolean textCheck(String actual,String expected){
        return actual.equals(expected);
    }

}
