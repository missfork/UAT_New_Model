package org.Teacher.test;

import org.Teacher.pages.Menu;
import org.Teacher.pages.Module;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA15 extends Module {


    String[] data = {"test_module_1","TS1","III","English","Test Module Description"};
    String[] link = {"https://youtu.be/CH50zuS8DD0","http://www.africau.edu/images/default/sample.pdf","http://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3",
            "https://en.wikipedia.org/wiki/Bird"};


    Menu menu = new Menu();
    @Test
    public void moduleCreationTest(){
        quickTeacherLogin();

        menu.moduleClick();

        moduleCreationClick();
        moduleName(data[0]);
        moduleShortName(data[1]);
        moduleStandard(data[2]);
        moduleSubject(data[3]);
        moduleDescription(data[4]);
        screenShot("moduleSave", moduleSave());

        //modulePdf(link[1]);
       // sleep(2000);
       // screenShot("moduleSave", moduleSave());

    }


    @Test
    public void moduleClearTest(){
        quickTeacherLogin();

        menu.moduleClick();

        moduleCreationClick();
        moduleName(data[0]);
        moduleShortName(data[1]);
        moduleStandard(data[2]);
        moduleSubject(data[3]);
        moduleDescription(data[4]);
        screenShot("clearBtnText", moduleClear());


    }
    @Test
    public void moduleCancelTest(){
        quickTeacherLogin();

        menu.moduleClick();

        moduleCreationClick();
        moduleName(data[0]);
        moduleShortName(data[1]);
        moduleStandard(data[2]);
        moduleSubject(data[3]);
        moduleDescription(data[4]);
        screenShot("cancelBtnText",moduleCancel());

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
            extentPass(fileName+" Test", fileName+"passed");
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
