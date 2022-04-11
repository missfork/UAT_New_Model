package org.Teacher.test;

import org.Teacher.pages.Chapter;
import org.Teacher.pages.Menu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA10 extends Chapter {

    //Verify the functionality whether the  role Teacher able to view the "Chapters" page on clicking on chapter menu from library tab
    /*
     * 1.Display the Chapter page along with "Create Module" and "Download"buttons and also a filter option.
     * Inside the filter option contains dropdowns  of(Curriculum,Standard,Subject,Created By) ,
     * also a name textBox and "apply filter" button
     *
     * 2. Export button on mouse over should display "Download as CSV"
     *
     * 3." N Matches Found" should be displayed
     *
     * 4Home icon Should be visible and functional
     */

    Menu menu = new Menu();

@Test
 public void visibilityCheck(){
     quickTeacherLogin();

     menu.chapterClick();
     screenShot("filterCheck",filterCheck(),"screenshot.filter");
    screenShot("downloadCheck",downloadCheck());
    screenShot("nthMatchCheck",nthMathCheck(),"chapter.nthMatch");
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
