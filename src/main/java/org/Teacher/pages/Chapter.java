package org.Teacher.pages;

import org.Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Chapter extends Base {

Common c = new Common();


//visibility section-------------------------------------------------------------------------------------------------------------------------------

// Checks the visibility of the filter
public boolean filterCheck(){
    String[] filter ={filterCss("filter.name"),filterCss("filter.curriculum"),filterCss("filter.standard"),filterCss("filter.subject"),
            css("filter.createdBy"), css("filterButton"), css("filter.applyFilter")};
clickCss(filterCss("filterButton"));
    int k = 0;
    boolean flag= false;
    for (int i =0;i<filter.length;i++) {

        if (locator(filter[i]).isDisplayed()) {
            k++;
        }
        if (k == filter.length) {
            System.out.println("i :"+i+"k :"+ k);
            flag = true;
        }
    }

    return flag;
}


// visibility of create button
public boolean chapterBtnCheck(){

    return locator( css("createChapterBtn")).isDisplayed();
}


//check whether export button visible or not and also its tooltip
    public boolean downloadCheck(){

       WebElement download= locator( css("chapter.download"));

        c.mousePoint("chapter.download");
        //getting tooltip from download
        WebElement downloadTooltip =locator(css("chapter.exportTooltip"));

        sleep(6000);

        return download.isDisplayed()&&  downloadTooltip.getText().equals(css("exportTooltip"));
    }


    // check number of matched chapters
    public boolean nthMathCheck(){
        return locator(css("chapter.nthMatch")).isDisplayed();
    }

//home button visibility
    public boolean homeBtnCheck(){

   boolean home = locator(css("chapter.home")).isDisplayed();
     clickCss("chapter.home");
     String expectedUrl = propertyRead("Base/base.properties","dashBoardUrl");
     return (driver.getCurrentUrl().equals(expectedUrl)&&home);
    }


//--------------------------------------------------------------------------------------------------------------------
    // chapter creation




    // click create chapter
    public void createChapterClick(){
    clickCss(css("createChapterBtn"));
    }



     //name
     public void chapterName(String name){
    elementInput(css("chapter.name"),name);
 }
    //shortname
    public void chapterShortName(String shortname){
        elementInput(css("chapter.shortName"),shortname);
    }

    //description
    public void chapterDescription(String descript){
        elementInput(css("chapter.description"),descript);
    }

    //standard
    public void chapterStandard(String standard){
       dropdown(locator(css("chapter.standard")),standard);
    }

    //subject
    public void chapterSubject(String subject){
        dropdown(locator(css("chapter.subject")),subject);
    }












//add module
    public void addModule(){
        if(locator(css("chapter.moduleTab")).getAttribute("class").equals(css("disableBtnCss"))){
         c.mousePoint("chapter.saveDisable");
            screenShot("addModuleFail"+"failed");
            extentFail("addModuleFail"+" Test", "addModuleFail"+" "+driver.getCurrentUrl(), "addModuleFail"+"failed");

        }
        else{
            clickCss(css("chapter.moduleTab"));
            clickCss(css("chapter.addModuleBtn"));
            clickCss(css("chapter.moduleSelector"));
            clickCss(css("chapter.addModuleListBtn"));
        }
    }














    //buttons on the pages-------------------------------------------------------------------------------------------------------------------------------


    public boolean chapterSave(){
boolean flag = false;
try {
    if(driver.findElement(By.cssSelector(css("chapter.save"))).isDisplayed())
    {
        clickCss(css("chapter.save"));
        clickCss(css("chapter.okToSave"));
        flag= true ;



    }
}catch (Exception e){

       c.mousePoint("chapter.saveDisable");
        screenShot("savetooltipFailed");
        extentFail("SaveChapter","chapter save failed reasons are described int the toolTip-Image","savetooltipFailed");

}

    return flag;
    }



    public boolean chapterCancel(){
        boolean flag = false;
        try {
            if(driver.findElement(By.cssSelector(css("chapter.cancel"))).isDisplayed())
            {
                clickCss(css("chapter.cancel"));
                clickCss(css("chapter.okToSave"));
               sleep(2000);
               flag= pageCheck(menuCss("chapterLink"),menuCss("breadcrumbs.chapter"));
            }
        }catch (Exception e){

            c.mousePoint("chapter.cancel");
            screenShot("canceltooltipFailed");
            extentFail("CancelChapter","chapter cancel failed reasons are described int the toolTip-Image","canceltooltipFailed");

        }

        return flag;
    }




    public boolean chapterClear(){
        boolean flag = false;
        try {
            if(driver.findElement(By.cssSelector(css("chapter.clear"))).isDisplayed())
            {
                clickCss(css("chapter.clear"));

                clickCss(css("chapter.okToSave"));

                  flag= c.clearCheck();
              }



        }catch (Exception e){

            c.mousePoint("chapter.clearDisable");
            screenShot("cleartooltipFailed");
            extentFail("ClearChapter","chapter clear failed reasons are described int the toolTip-Image","chapter.clearDisable");

        }

        return flag;
    }














// private functions-------------------------------------------------------------------------------------------------------------------------------------------------------------


    private String css(String key){
    return propertyRead("Teacher/chapter.properties",key);
    }



    private String filterCss(String key){
        return propertyRead("Teacher/commonFile.properties",key);
    }


    private boolean pageCheck(String url ,String expectedBreadcrumbs){

        return (c.urlCheck(url)&&c.breadCrumpsCheck(expectedBreadcrumbs));
    }

    private String menuCss(String key){
        return propertyRead("Teacher/menu.properties",key);
    }



}



