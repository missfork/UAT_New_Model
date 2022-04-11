package org.Teacher.test;

import org.Teacher.pages.Chapter;
import org.Teacher.pages.Menu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA11 extends Chapter {
    String[]data = {"automationChapter1","AC1","III","English-I","description for chapter"};
//Verify the functionality whether the Role  Teacher able  create a new Chapter entry

    /**
     * 1.On clicking on "Create Chapter" button should display the Create Chapter page
     *.2.Enter the Mandatory data with * symbol and click on "Save Chapters" button
     *3.Data should be saved in the DB
     *
     */

    @Test
    public void createChapter(){
        Menu menu = new Menu();
        quickTeacherLogin();

        menu.chapterClick();



        createChapterClick();
        chapterName(data[0]);
        chapterShortName(data[1]);
        chapterStandard(data[2]);
        chapterSubject(data[3]);
        chapterDescription(data[4]);
        sleep(3000);
        addModule();
     screenShot("chapterSave",chapterSave());
    }




@Test
    public void chapterClearTest(){
        Menu menu = new Menu();
        quickTeacherLogin();
        menu.chapterClick();

        createChapterClick();
        chapterName(data[0]);
        chapterShortName(data[1]);
        chapterStandard(data[2]);
        chapterSubject(data[3]);
        chapterDescription(data[4]);
        screenShot("clearBtnText", chapterClear());


    }
@Test
public void chapterCancelTest(){
    System.out.println("module cancel");
    Menu menu = new Menu();
    quickTeacherLogin();
    menu.chapterClick();

    createChapterClick();
    chapterName(data[0]);
    chapterShortName(data[1]);
    chapterStandard(data[2]);
    chapterSubject(data[3]);
    chapterDescription(data[4]);
    screenShot("cancelBtnText",chapterCancel());

}




//private functions


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



