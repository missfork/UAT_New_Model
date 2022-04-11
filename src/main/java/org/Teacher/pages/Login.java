package org.Teacher.pages;



import org.Base.Base;
import org.testng.annotations.Test;


public class Login extends Base {

    public boolean loginUrl(){
        boolean flag =false;
        String actual =driver.getCurrentUrl();
        String expected = "https://production.ilearningengines.com/login";
        if(actual.equals(expected)){
            flag= true;
        }
        return flag;

    }

    public boolean dashBoardUrl(){
        boolean flag =false;
        String actual =driver.getCurrentUrl();
        String expected =propertyRead("Base/base.properties","dashBoardUrl");
        if(actual.equals(expected)){
            flag= true;
        }
        return flag;

    }

    // visibility of logout button to ensure we are logged in
    public boolean logOutBtn(){
        boolean flag =false;

        if(locator(css("logoutButton")).isDisplayed()){
            flag= true;
        }
        return flag;

    }







    public void userName(String userName) {

          String userElement = css("userName");
         elementInput(userElement, userName);
     }

     public void password(String userPassword) {


          String passwordElement = css("userPassword");
        elementInput(passwordElement, userPassword);

        }

      public void login() {
        String loginButton = propertyRead("Common/login.properties", "loginButton");
        clickCss(loginButton);

        }

     private String css(String key){

        return propertyRead("Common/login.properties", key);
     }


}