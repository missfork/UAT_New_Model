package org.Teacher.test;

import org.Teacher.pages.Menu;
import org.Teacher.pages.Module;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA14 extends Module {
    Menu menu = new Menu();

    @Test
    public void visibilityCheck(){
        quickTeacherLogin();

        menu.moduleClick();
       screenShot("Module_FilterCheck",filterCheck(),"screenshot.filter");
       screenShot("Module_DownloadCheck",downloadCheck());
      screenShot("Module_NthMatchCheck",nthMathCheck(),"module.nthMatch");
      screenShot("moduleCreationClick", moduleCreationClick());

    }






    //private functions
    private void screenShot(String fileName, boolean flag,String keyForElement){
        if (flag) {
            screenShotElement(css(keyForElement),fileName+"passed");
            extentPass(fileName+" Test", fileName, fileName+"passed");
        } else {
            screenShot(fileName+"failed");
            extentFail(fileName+" Test", fileName, fileName+"failed");

        }
        Assert.assertTrue(flag);
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


    private String css(String key){
        return propertyRead("Teacher/chapter.properties",key);
    }
}
