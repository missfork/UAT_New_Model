package org.Teacher.pages;


import org.Base.Base;

public class Course extends Base {

    public void courseCreate (){
        clickCss(css("createCourseBtn"));
    }




    public void courseName(String name){

        elementInput(css("course.name"),name);

    }


    public void courseSubject(String option){

    dropdown(locator(css("course.subject")),option);

    }


    public void courseStandard(String option){
dropdown(locator(css("course.standard")),option);

    }








    //--------------------------_________________-Private-_____________--------------------------------------------------//
    private String css(String key){
        return propertyRead("Teacher/course.properties",key);
    }

}
