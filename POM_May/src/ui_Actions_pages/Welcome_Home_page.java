package ui_Actions_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import base_POM.Base_POM;

public class Welcome_Home_page extends Base_POM{
	
	@FindBy(xpath="//a[@id='menu_admin_viewAdminModule']")
	WebElement Admin_tab;	
	
	@FindBy(xpath="//a[@id='menu_admin_viewAdminModule']")
	WebElement Admin_tab_Clickable;
	
	@FindBy(xpath="//a[@class='toggle tiptip']")
	WebElement Admin_tab_Grid_Varification;
	
	@FindBy(id="btnSrch")
	WebElement SearchButton;
	
	@FindBy(id="//a[@id='menu_recruitment_viewRecruitmentModule']")
	WebElement RecruitmentButton;
	
	
	public Welcome_Home_page(WebDriver dr,ExtentTest test) {
		super(dr,test);
		PageFactory.initElements(dr, this);
	}
	
	public boolean verifyAdmin_tab(){
		 if(Admin_tab.isDisplayed()) {
			 screenshot();
				test.log(LogStatus.PASS,"User is able to click and User grid has been opened");
				log.info("User is able to click and User grid has been opened");
			 return true;	
		}else {
			test.log(LogStatus.FAIL,"User not able to click.....");
			log.info("User is not able to click.");
			screenshot_Fail();
			return false;
		}
		
	}
	
	public void verify_Admin_Grid_OpenOrNot() {
		
		if(Admin_tab.isDisplayed()) {
			Admin_tab_Clickable.click();
			test.log(LogStatus.PASS,"User is able to click and User grid has been opened");
			log.info("User is able to click and User grid has been opened");
			screenshot();
		}else {
			test.log(LogStatus.FAIL,"User not able to click.....");
			log.info("User is not able to click.");
			screenshot_Fail();
		}
	}
	
	public boolean Verify_Admin_Grid_Displayed() {
		if(Admin_tab_Grid_Varification.isDisplayed()) {
			test.log(LogStatus.PASS,"Verify_Admin_Grid_Displayed");
			log.info("User is able to click Verify_Admin_Grid_Displayed");
			return true;
		}else {
			test.log(LogStatus.FAIL,"Not Displayed");
			log.info("Not Displayed ........");
			return false;
		}
	}
	
	public boolean SerchButton_Is_Displayed() {
		if(SearchButton.isDisplayed()) {
			test.log(LogStatus.PASS,"Verify_Search Button");
			log.info("User is able to click Verify_Admin_Grid_Displayed");
			return true;
		}else {
			test.log(LogStatus.FAIL,"Verify_Search Button not Displayed");
			log.info("Not Displayed ........");
			return false;
		}
	}
	
	public Candidates_Page SerchButton_Click(){
		  RecruitmentButton.click();
		  test.log(LogStatus.FAIL,"Verify Search Button Open New Candidates Grid.");
		  log.info("Displayed ........");
		  return new Candidates_Page();
	}
	
	
}
