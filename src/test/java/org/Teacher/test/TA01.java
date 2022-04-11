package org.Teacher.test;


import org.Teacher.pages.DashBoard;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA01 extends DashBoard

{

    // Verify whether the user is able to log in as Teacher role

    /**
     * User should be able to log in as Teacher his privilege
     *Should be able to view all the menu buttons
     */


TA02LoginTest log = new TA02LoginTest();

 @Test
public void VisibilityCheck(){
    log.loginTest();
    screenShot(menuVisibility(),"menuVisibility");
    screenShot(memberCheck(),"memberCheck");
     screenShot(libraryCheck(),"libraryCheck");
     screenShot(assesmentCheck(),"assesmentCheck");
     screenShot(visualClassRoomCheck(),"visualClassRoomCheck");
    // screenShot(KnowledgeShelfCheck(),"KnowledgeShelfCheck()");


}



    private void screenShot( boolean flag,String fileName){
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
