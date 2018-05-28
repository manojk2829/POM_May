package ui_Actions_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import base_POM.Base_POM;
import junit.framework.Assert;

public class Login_Page extends Base_POM{
     @FindBy(id="txtUsername")
     WebElement userLogin;
     
     @FindBy(id="txtPassword")
     WebElement Pass;
     
     @FindBy(xpath="//*[@src='/webres_5786257bd7c8a5.72130757/themes/default/images/login/logo.png']")
     WebElement Logo_Image_Test;
     
     @FindBy(xpath=".//*[@id='welcome']")
     WebElement Home_Page_Welcome_Level_Test;

     public Login_Page(WebDriver dr,ExtentTest test) {
    	 super(dr,test);
    	 PageFactory.initElements(dr, this);
     }
     
     public boolean LOGO_verification() {
    	 if(Logo_Image_Test.isDisplayed()) {
    		test.log(LogStatus.PASS, "Application Logo displayed.....");
    		log.info("Application Logo displayed.....");
    		return true;
    	 }else {
    		 test.log(LogStatus.FAIL, "Application Logo not displayed.....");
     		 log.info("Application Logo not displayed....."); 
     		return false;
    	 }
     }
     
     public void doLogin(String u,String p) {
    	 userLogin.sendKeys(u);
    	 Pass.sendKeys(p);
    	 screenshot();
    	 wait(1);
    	 test.log(LogStatus.INFO, "User name and Password Enter succesfully....");
    	 Pass.sendKeys(Keys.ENTER);
    	 wait(1);
     }
     
     public boolean Welcome_HomePage_verification(){
    	 if(Home_Page_Welcome_Level_Test.isDisplayed()) {
    		 test.log(LogStatus.PASS, "Login User successfully Login...... ");
    		 log.info("Login Done Welcome Page displayed successfully......");
    		 return true;
    	 }else {
    		 test.log(LogStatus.FAIL, "Login User Unable to successfully Login...... ");
    		 log.info("Login Not Done Welcome Page Not Opened......");
    		 return false;
    	 }
     }
     
     public String Login_Page_Title_Verification() {
    	 String Login_Page_Title=dr.getTitle();
    	 //Assert.assertEquals(Login_Page_Title, "");
    	 System.out.println(Login_Page_Title);
    	 return Login_Page_Title;
     }
     
     
     
     public Welcome_Home_page verify_Login_Successfully_Done() {
    	 	 Home_Page_Welcome_Level_Test.isDisplayed();
    		 test.log(LogStatus.PASS, "Login User successfully Login...... ");
    		 log.info("Login Done Welcome Page displayed successfully......");
    		 return new Welcome_Home_page(dr, test);
    	 }
    
}
