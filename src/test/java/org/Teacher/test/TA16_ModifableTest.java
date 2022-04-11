package org.Teacher.test;

import org.Base.Base;
import org.Teacher.pages.Chapter;
import org.Teacher.pages.Common;
import org.Teacher.pages.Menu;
import org.Teacher.pages.Module;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TA16_ModifableTest extends Module {
   /**
    * Verify that the Teacher can give permission to edit and view the Module for all teachers
    *
    * 1.Ensure that the check-box "Module is modifiable for all teachers" and "Module is Viewable for all teachers" is visible and clickable.
    * 2.Ensure that the other teachers can access the edit the module page.
    */
    Menu menu = new Menu();
   Common c = new Common();
   String[] data = {"test_module_14","TS11","III","English","Test Module Description"};
   String[] UpdateData = {"test_module_up_13","TS1","III","English","Test update Module Description"};
    String[] link = {"https://youtu.be/CH50zuS8DD0","http://www.africau.edu/images/default/sample.pdf","http://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3",
            "https://en.wikipedia.org/wiki/Bird"};

   @Test
   public void modifiabilityCheck(){


       quickTeacherLogin();

       menu.moduleClick();

       moduleCreationClick();
       moduleName(data[0]);

       moduleStandard(data[2]);
       moduleSubject(data[3]);
       moduleDescription(data[4]);
       moduleShortName();
       contentClick();
       modulePdf(link[1]);
       sleep(2000);
       screenShot("moduleSave", moduleSave());
       menu.logout();









      c.quickTeacherLogin2();

      menu.moduleClick();


     applyFilter("filter.name",data[0]);

       wait.until(ExpectedConditions.elementToBeClickable(locator(css("filter.all"))));
     locator(css("filter.all")).click();
       sleep(2000);
       screenShot("moduleSearchCheck()",moduleSearchCheck());
       locator(css("filter.firstSearchItem")).click();
       sleep(1000);
      moduleName(UpdateData[0]);

      moduleStandard(data[2]);
      moduleSubject(data[3]);
      moduleDescription(data[4]);
       clickCss(css("module.shortName"));
      sleep(2000);

      screenShot("moduleUpdateSave", moduleSave());
       extentPass("ModuleModifableTest", "Module canbe modifable by other teacher");


   }

private boolean moduleSearchCheck(){
       String actualName = locator(css("filter.firstSearchItem")).getText();

       return actualName.equals(data[0]);
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
        return propertyRead("Teacher/module.properties",key);
    }


}
