package org.Teacher.pages;

import org.Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Module extends Base {
Common c = new Common();



   // /visibility section-------------------------------------------------------------------------------------------------------------------------------

    // Checks the visibility of the filter
    public boolean filterCheck(){
        String[] filter ={css("filter.name"),css("filter.curriculum"),css("filter.standard"),css("filter.subject"),
                css("filter.createdBy"), css("filterButton"), css("filter.applyFilter"),css("filter.to"),css("filter.from"),css("filter.shortName")};
        clickCss(css("filterButton"));
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
    public boolean moduleBtnCheck(){

        return locator( css("createModuleBtn")).isDisplayed();
    }



    //check whether export button visible or not and also its tooltip
    public boolean downloadCheck(){

        WebElement download= locator( css("module.download"));

        c.mousePoint("module.download");
        //getting tooltip from download
        WebElement downloadTooltip =locator(css("module.exportTooltip"));


        return download.isDisplayed()&&  downloadTooltip.getText().equals(css("exportTooltip"));
    }




    // check number of matched chapters
    public boolean nthMathCheck(){
String nth = locator(css("module.nthMatch")).getText();
String nthExpected = css("nthModuleText");
        return (nth.substring(nth.length()-nthExpected.length(),nth.length()).equals(nthExpected));
    }



    //home button visibility
    public boolean homeBtnCheck(){

        boolean home = locator(css("chapter.home")).isDisplayed();
        clickCss("chapter.home");
        String expectedUrl = propertyRead("Base/base.properties","dashBoardUrl");
        return (driver.getCurrentUrl().equals(expectedUrl)&&home);
    }

//========================================================================================================================================================
//=====================================Module_Creation====================================================================================================
public boolean moduleCreationClick(){

clickCss(css("createModuleBtn"));
sleep(2000);
return pageCheck(menuCss("createModuleLink"),css("breadcrumbs.module.createModule"));

}



    //name
    public void moduleName(String name){
        elementInput(css("module.name"),name);
    }
    //shortname
    public void moduleShortName(String shortname){
        elementInput(css("module.shortName"),shortname);
    }
    public void moduleShortName(){
        clickCss(css("module.shortName"));
    }

    //description
    public void moduleDescription(String descript){
        elementInput(css("module.description"),descript);
    }

    //standard
    public void moduleStandard(String standard){
        dropdown(locator(css("module.standard")),standard);
    }

    //subject
    public void moduleSubject(String subject){
        dropdown(locator(css("module.subject")),subject);
    }






public void contentClick(){
        clickCss(css("content"));
}

// content addition

    //name
    public void moduleVideo(String link){
        clickCss(css("video"));
        elementInput(css("externalUrl"),link);
    }
    //shortname
    public void modulePdf(String link){
       // clickCss(css("pdf"));
        elementInput(css("externalUrl"),link);
    }

    //description
    public void moduleAudio(String link){
        clickCss(css("audio"));
        elementInput(css("externalUrl"),link);
    }

    public void moduleHTML(String link){
        clickCss(css("htmlTab"));
        elementInput(css("externalUrl"),link);
    }






    //buttons on the pages-------------------------------------------------------------------------------------------------------------------------------


    public boolean moduleSave(){
        boolean flag = false;
        try {
            if(driver.findElement(By.cssSelector(css("module.save"))).isDisplayed())
            {
                clickCss(css("module.save"));


                String expected = css("expMsg");
                String Uexpected = css("updateExpMsg");
                String actual = locator(css("updateMsgFrame")).getText();
                clickCss(css("module.okToSave"));
                System.out.println("expected ="+expected+" "+"actual ="+actual);
                flag= expected.equals(actual)||Uexpected.equals(actual) ;



            }
        }catch (Exception e){

            c.mousePoint("module.saveDisable");
            screenShot("savetooltipFailed");
            extentFail("SaveChapter","module save failed reasons are described int the toolTip-Image","savetooltipFailed");

        }
        if (!flag){ c.mousePoint("module.saveDisable");
            screenShot("savetooltipFailed");
            extentFail("SaveChapter","module save failed reasons are described int the toolTip-Image","savetooltipFailed");}

        return flag;
    }

//cancel=====================================================================================================================================
public boolean moduleCancel(){
    boolean flag = false;
    try {
        if(driver.findElement(By.cssSelector(css("module.cancel"))).isDisplayed())
        {
            clickCss(css("module.cancel"));

            clickCss(css("module.okToSave"));

            flag= pageCheck(menuCss("moduleLink"),menuCss("breadcrumbs.module"));
        }
    }catch (Exception e){

        c.mousePoint("module.cancel");
        screenShot("canceltooltipFailed");
        extentFail("CancelModule","module cancel failed reasons are described int the toolTip-Image","canceltooltipFailed");

    }

    return flag;
}


// clear ==============================================================================================================================


    public boolean moduleClear(){
        boolean flag = false;
        try {
            if(driver.findElement(By.cssSelector(css("module.clear"))).isDisplayed())
            {
                clickCss(css("module.clear"));

                clickCss(css("module.okToSave"));

                flag= clearCheck();
            }



        }catch (Exception e){

            c.mousePoint("module.clearDisable");
            screenShot("cleartooltipFailed");
            extentFail("ClearModule","module clear failed reasons are described int the toolTip-Image","chapter.clearDisable");

        }

        return flag;
    }











//==================================================Private===================================================================================================

    private String css(String key){
        return propertyRead("Teacher/module.properties",key);
    }



    private String menuCss(String key){
        return propertyRead("Teacher/menu.properties",key);
    }

    private boolean pageCheck(String url ,String expectedBreadcrumbs){

        return (c.urlCheck(url)&&c.breadCrumpsCheck(expectedBreadcrumbs));
    }





private boolean clearCheck(){
        boolean flag = false;
        if (empty(css("module.name"))&&empty(css("module.shortName"))&&empty(css("module.description"))) {
            String std = locator(css("module.standard")).getText();
            String sub = locator(css("module.subject")).getText();

            flag= (std.equals(css("standardInnerText"))&&sub.equals(css("subjectInnerText")));

        }
        else {
            screenShot("clearBtnFailed");
            extentFail("clearBtnDisabled","clear button failed ","clearBtnFailed");
        }
        return flag;


    }



    private boolean empty(String css){

        return locator(css).getText().isEmpty();
    }




  public void applyFilter(String propertyName,String searchContent){

        String filterElement = propertyRead("Teacher/module.properties", "moduleFilter");
        sleep(2000);
        clickCss(filterElement);

        String applySearch = propertyRead("Teacher/module.properties", propertyName);
        //Entering search content
        elementInput(applySearch, searchContent);
        String applyFilter = propertyRead("Teacher/module.properties", "applyFilter");
        clickCss(applyFilter);


    }




}
