package org.Teacher.pages;


import org.Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



import java.util.List;


public class Menu extends Base {
    Common c= new Common();

    public void homeClick(){
        String home = css("homeBtn");
        clickCss(home);

    }
    public boolean home(){
        locator(css("logoutBtn"));
        boolean flag = false;
        if(driver.getCurrentUrl().equals(css("homeLink")) )
        {

          String homeTitle = locator(css("home.titleHeading")).getText();
          String profileName = locator(css("home.profileName")).getText() ;
         flag = homeTitle.substring(homeTitle.length()-profileName.length(),homeTitle.length()).equals(profileName);
            System.out.println(homeTitle.substring(homeTitle.length()-profileName.length(),homeTitle.length()));
            System.out.println("profile : "+profileName);
        }
return flag;
    }



public  void memberClick(){
    String member = css("memberBtn");
    clickCss(member);
}

    public boolean studentClick(){
        memberClick();
        String student = css("studentBtn");
        clickCss(student);
        return pageCheck("student");
    }


    public boolean classClick(){
        String classBtn = css("classBtn");
        clickCss(classBtn);
        return pageCheck("class");
    }


    public void libraryClick(){

        String libraryBtn = css("libraryBtn");
        clickCss(libraryBtn);
    }


    public boolean classListClick(){
        String member = css("memberBtn");
         clickCss(member);
        String classList = css("classList");
        clickCss(classList);
        return pageCheck("classList");
    }


    public boolean subjectClick(){
        libraryClick();
        String subject = css("subjectBtn");
        clickCss(subject);
        return pageCheck("subject");
    }

    public boolean moduleClick(){
        libraryClick();
        String module = css("moduleBtn");
        clickCss(module);
        return pageCheck("module");
    }


    public boolean chapterClick(){
        libraryClick();
        String chapter = css("chapterBtn");
        clickCss(chapter);
        return pageCheck("chapter");

    }


    public boolean courseClick(){
        libraryClick();
        String course = css("courseBtn");
        clickCss(course);
        return pageCheck("course");
    }


    public boolean coursePublishClick(){
        libraryClick();
        String coursePublish = css("coursePublishBtn");
        clickCss(coursePublish);
        return pageCheck("coursePublish");
    }


    public void assessment(){
        String assessmentBtn = css("assessmentBtn");
        clickCss(assessmentBtn);
    }

    public boolean createTestClick(){
         assessment();
        String createTest = css("createTestBtn");
        clickCss(createTest);
        return pageCheck("createTest");
    }


    public boolean questionBankClick(){
        assessment();
        String qBank = css("questionBank");
        clickCss(qBank);
        return pageCheck("questionBank");
    }


    public boolean publishTestClick(){
        assessment();
        String publishTest = css("publishTest");
        clickCss(publishTest);
        return pageCheck("questionBank");
    }

    public void virtualRoom(){
        String vRoom = css("virtualClassroomBtn");
        clickCss(vRoom);

    }




    public boolean roomsClick(){
        virtualRoom();
        String Room = css("roomsBtn");

        clickCss(Room);
        return pageCheck("rooms");
    }


    public boolean sessionClick(){
        virtualRoom();
        String Session = css("sessionBtn");

        clickCss(Session);
        return pageCheck("session");

    }

    public boolean attendanceClick(){
        virtualRoom();
        String attandance = css("attendanceBtn");
        clickCss(attandance);
        return pageCheck("attendance");
    }

    public boolean playbackClick(){
        virtualRoom();
        String playback = css("playbackBtn");
        clickCss(playback);
        return pageCheck("playback");
    }

    public boolean trainingZoneClick(){

        String tZone = css("trainingZoneBtn");
        clickCss(tZone);
        return pageCheck("trainingZone");
    }



    public  void knowledgeShelf(){
        String kShelf = css("knowledgeShelfBtn");

        clickCss(kShelf);
    }


    public boolean createShelfClick(){
        knowledgeShelf();
        String createShelf = css("createShelfBtn");
        clickCss(createShelf);
        return pageCheck("createShelf");
    }

    public boolean viewShelfClick(){
        knowledgeShelf();
        String viewShelf = css("viewShelfBtn");
        clickCss(viewShelf);
        return pageCheck("viewShelf");
    }

    public boolean reportClick(){
        String reportBtn = css("reportsBtn");
        clickCss(reportBtn);
        return pageCheck("report");
    }

    public boolean templatesClick(){
        String templatesBtn = css("templatesBtn");
        clickCss(templatesBtn);
        return pageCheck("templates");

    }

    public boolean calendarClick(){
        String calendarBtn = css("calendarBtn");
        clickCss(calendarBtn);
        return pageCheck("calendar");


    }

    public boolean profileSettingsClick(){
        String profileBtn = css("profileSettingsBtn");
        clickCss(profileBtn);
        return pageCheck("profileSettings");
    }


    public void logout(){
        String logout = css("logoutBtn");
        clickCss(logout);
        clickCss(css("okToLogout"));
        clickCss(css("loggedOut"));
    }



    //additional functions

    private String css(String key){

        return propertyRead("Teacher/menu.properties", key);
    }


private boolean pageCheck(String key){

   /* *
   *replace whitespaces in betweens
  * a.replace(" ", "").equalsIgnoreCase(b.replace(" ", ""))

   */



        boolean flag = false;
        String bread="";
      String name= dateTime();
        if(driver.getCurrentUrl().equals(css(key+"Link")) ){
            System.out.println("first inside if = "+flag);
            // Assigning bread-crumbs to list
            List<WebElement> crumps = driver.findElements(By.cssSelector(propertyRead("Teacher/commonFile.properties","common.breadcrumbs")));
// iterating through list
            /**
             * crumps is the name of the list
             */

            for(WebElement e : crumps) {
                // bread is the value of the breadcrump
                sleep(500);
                   bread =e.getText().replace(" ", "");

                System.out.println("flag inside if = "+bread);
                // check whether actual value of the breadcrump is same a the expexted value
                if(bread.equalsIgnoreCase(propertyRead("Teacher/menu.properties","breadcrumbs."+key).replace(" ", "")))
                {
                    flag= true;
                    System.out.println("flag inside if = "+bread);
                }


            }


        }
    if (!flag) {

            extentFail(key+"Test","Breadcrumbs fails: <br>"+"expected_crumbs : "+ propertyRead("Teacher/menu.properties","breadcrumbs."+key) +"<br>"+ "actual_crumbs : "+bread);

    }
    System.out.println("flag = "+flag);
    System.out.println("name outside if = "+name);
    System.out.println("========================================================================================================================================================================================================================================");
    return flag;
}






}

