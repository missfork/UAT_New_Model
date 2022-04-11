package org.Teacher.pages;

import org.Base.Base;

public class DashBoard extends Base {


    public boolean menuVisibility() {


        String[] menu = {css("homeBtn"), css("memberBtn"), css("classBtn"), css("libraryBtn"), css("assessmentBtn"),
                css("virtualClassroomBtn"), css("trainingZoneBtn"),  css("profileSettingsBtn")};
        int k = 0;
        boolean flag= false;
        for(int i = 0; i < menu.length; i++) {

            if (locator(menu[i]).isDisplayed()) {
                k++;
            }
            if (k == menu.length) {
                flag = true;
            }
        }
       return flag;
    }



public Boolean memberCheck(){
clickCss(css("memberBtn"));
    String[] Membermenu = {css("studentBtn")};
    int k = 0;
    boolean flag= false;
    for(int i = 0; i < Membermenu.length; i++) {

        if (locator(Membermenu[i]).isDisplayed()) {
            k++;
        }
        if (k == Membermenu.length) {
            flag = true;
        }
    }
    clickCss(css("memberBtn"));
    return flag;
}


    public Boolean libraryCheck(){
        clickCss(css("libraryBtn"));
        String[] library_Menu = { css("moduleBtn"), css("chapterBtn"), css("courseBtn"), css("coursePublishBtn")};
        int k = 0;
        boolean flag= false;
        for(int i = 0; i < library_Menu.length; i++) {

            if (locator(library_Menu[i]).isDisplayed()) {
                k++;
            }
            if (k == library_Menu.length) {
                flag = true;
            }
        }
        clickCss(css("libraryBtn"));
        return flag;
    }

    public Boolean assesmentCheck(){
        clickCss(css("assessmentBtn"));
        String[] assesment_Menu = {css("createTestBtn"), css("questionBank"), css("publishTest")};
        int k = 0;
        boolean flag= false;
        for(int i = 0; i < assesment_Menu.length; i++) {

            if (locator(assesment_Menu[i]).isDisplayed()) {
                k++;
            }
            if (k == assesment_Menu.length) {
                flag = true;
            }
        }
        clickCss(css("assessmentBtn"));
        return flag;
    }

    public Boolean visualClassRoomCheck(){
        clickCss(css("virtualClassroomBtn"));
        String[] assesment_Menu = {css("roomsBtn"), css("sessionBtn"), css("attendanceBtn")};
        int k = 0;
        boolean flag= false;
        for(int i = 0; i < assesment_Menu.length; i++) {

            if (locator(assesment_Menu[i]).isDisplayed()) {
                k++;
            }
            if (k == assesment_Menu.length) {
                flag = true;
            }
        }
        clickCss(css("virtualClassroomBtn"));
        return flag;
    }


    /*public Boolean KnowledgeShelfCheck(){
        clickCss(css("knowledgeShelfBtn"));
        String[] assesment_Menu = {css("createShelfBtn"), css("viewShelfBtn")};
        int k = 0;
        boolean flag= false;
        for(int i = 0; i < assesment_Menu.length; i++) {

            if (locator(assesment_Menu[i]).isDisplayed()) {
                k++;
            }
            if (k == assesment_Menu.length) {
                flag = true;
            }
        }
        clickCss(css("knowledgeShelfBtn"));
        return flag;
    }*/


private String css(String key){
  String  element = propertyRead("Teacher/menu.properties",key);
  return element;
}




}
