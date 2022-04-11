package org.Teacher.test;


import org.Teacher.pages.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TA03 extends Menu {

    /*menus not present in production are:
    * classList
    * subject
    * calendar
    * knowledge shelf
    * playback
    *report
    * */

@BeforeMethod
        public void Blogin(){
            quickTeacherLogin();
        }



        @Test
        public void menuTest() {

            screenShot("home.page", home());

        }

        @Test
        public void studentClickTest() {

            screenShot("student.page",studentClick());

        }
       /* @Test
        public void classListClickTest() {
            quickTeacherLogin();
            screenShot("classList.page",classListClick());


        }*/
        @Test
        public void classClickTest() {
            screenShot("class.page",classClick());


        }
      /*  @Test
        public void subjectClickTest() {

            screenShot("subject.page",subjectClick());


        }*/

        @Test
        public void moduleClickTest() {
            screenShot("module.page",moduleClick());

        }

        @Test
        public void chapterClickTest() {

            screenShot("chapter.page",chapterClick());

        }

        @Test
        public void courseClickTest() {
            screenShot("course.page",courseClick());

        }


        @Test
        public void coursePublishClickTest() {

            screenShot("coursePublish.page",coursePublishClick());


        }


        @Test
        public void createTestClickTest() {

            screenShot("createTest.page",createTestClick());


        }


        @Test
        public void questionBankClickTest() {

            screenShot("questionBank.page",questionBankClick());


        }


        @Test
        public void roomsClickTest() {

            screenShot("rooms.page",roomsClick());


        }


        @Test
        public void sessionClickTest() {

            screenShot("session.page",sessionClick());


        }

       /* @Test
        public void playbackClickTest() {
            screenShot("playback.page",playbackClick());



        }*/



        @Test
        public void trainingZoneClickTest() {

            screenShot("trainingZone.page",trainingZoneClick());

        }

       /* //
        public void createShelfClickTest() {

            screenShot("createShelf.page",createShelfClick());


        }


        //
        public void viewShelfClickTest() {

            screenShot("viewShelf.page",viewShelfClick());


        }
*/

        /*@Test
        public void reportClickTest() {

            screenShot("report.page",reportClick());
            // clickCss(".main_main__2Paq_ svg");

        }*/




     /*   @Test
        public void templatesClickTest() {

            screenShot("templates.page",templatesClick());

        }
*/


       /* @Test
        public void calendarClickTest() {

            screenShot("calendar.page",calendarClick());
        }*/

        @Test
        public void profileSettingsClickTest() {

            screenShot("profileSettings.page",profileSettingsClick());

        }









//private function---------------------------------------------------------------------------------------------------------------------
private void screenShot(String fileName, boolean flag){
    if (flag) {
        screenShot(fileName+"passed");
        extentPass(fileName+" Test", fileName, fileName+"passed");
    } else {
        screenShot(fileName+"failed");
        extentFail(fileName+" Test", fileName+" "+driver.getCurrentUrl(), fileName+"failed");

    }
    Assert.assertTrue(flag);
}


    private String css(String key){
        return propertyRead("Teacher/chapter.properties",key);
    }
}




