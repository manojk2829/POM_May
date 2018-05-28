package TestCases_POM;

import java.util.Hashtable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import Excel_reader_Util.ExcelReader_data;
import base_POM.Base_POM;
import ui_Actions_pages.Login_Page;
import ui_Actions_pages.Welcome_Home_page;

public class Welcome_Test extends Base_POM{
	
	public Login_Page login;
	public Welcome_Home_page welcome_page;
	String Test_Case_sheetName="data";
	String Test_Case_Name="MMS_Login_Test";
	
	@Test(dataProvider="getdata", priority=1)
	public void Orange_Admin_Tab(Hashtable<String,String> TC) {
		test=ext.startTest("Test Start Login");
		openBro(pro.getProperty("Browser"));
		navigate(pro.getProperty("appurl"));
		login=new Login_Page(dr, test);
		login.doLogin(pro.getProperty("userName"), TC.get("password"));
		screenshot();
		welcome_page=new Welcome_Home_page(dr, test);
		welcome_page.verifyAdmin_tab();
		test.log(LogStatus.PASS,"Admin Tab displayed....");
	}
	
	@Test(dataProvider="getdata", priority=2)
	public void Orange_Admin_button_Open_Admin_User_Grid(Hashtable<String,String> TC) {
		test=ext.startTest("Test Start Login");
		openBro(pro.getProperty("Browser"));
		navigate(pro.getProperty("appurl"));
		login=new Login_Page(dr, test);
		login.doLogin(pro.getProperty("userName"), TC.get("password"));
		screenshot();
		welcome_page=new Welcome_Home_page(dr, test);
		welcome_page.verify_Admin_Grid_OpenOrNot();
		test.log(LogStatus.PASS,"Admin Search page getting opened.....");
	}
	
	
	@DataProvider
	public Object[][] getdata(){
		return ExcelReader_data.getExcelData(xls, Test_Case_sheetName, Test_Case_Name);
	}
	
	@AfterMethod
	public void tearDown() {
		ext.endTest(test);
		ext.flush();
		quit_Browser(3);
	}

}
