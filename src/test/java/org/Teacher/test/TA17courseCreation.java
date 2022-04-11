package org.Teacher.test;

import org.Teacher.pages.Course;
import org.Teacher.pages.Menu;
import org.testng.annotations.Test;

public class TA17courseCreation extends Course {
    Menu m = new Menu();
    String[] data = {"courseTest1","III","English"};
    @Test
    public void courseCreation(){
       quickTeacherLogin();
       m.courseClick();
       courseCreate();
       courseName(data[0]);


       sleep(2000);
       courseSubject(data[2]);
        sleep(2000);
        courseStandard(data[1]);



    }



}
