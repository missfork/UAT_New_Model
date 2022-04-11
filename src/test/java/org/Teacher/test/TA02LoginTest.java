package org.Teacher.test;

import org.Teacher.pages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA02LoginTest extends Login {


    //Verify the navigation to iLearnings home page when logged in as Teacher
    /*
     * iLearning login page should be displayed
     *iLearning  home page should be displayed
     */

   @Test
 public void loginTest(){
       loginUrl();
userName(data("validUsername"));
password(data("validPassword"));
screenShot("loginPage",loginUrl());
login();
sleep(3000);

screenShot("dashboard",dashBoardUrl());
screenShot("logoutButton",logOutBtn());

   }





 // private functions




private String data(String key){
    return propertyRead("Common/login.properties", key);
}


private void screenShot(String fileName, boolean flag){
    if (flag) {
        screenShot(fileName+"passed");
        extentPass(fileName+" Test", fileName, fileName+"passed");
    } else {
        screenShot(fileName+"failed");
        extentFail(fileName+" Test", fileName, fileName+"failed");

    }
    Assert.assertTrue(flag);
}

}
