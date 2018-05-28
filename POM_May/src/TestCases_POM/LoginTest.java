package TestCases_POM;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Excel_reader_Util.ExcelReader_data;
import base_POM.Base_POM;
import ui_Actions_pages.Login_Page;

public class LoginTest extends Base_POM{
	public Login_Page login;
	String Test_Case_sheetName="data";
	String Test_Case_Name="MMS_Login_Test";
	
	@Test(dataProvider="getdata")
	public void loginTest_Orange(Hashtable<String,String> TC) {
		test=ext.startTest("Test Start Login");
		openBro(pro.getProperty("Browser"));
		navigate(pro.getProperty("appurl"));
		login=new Login_Page(dr, test);
		login.LOGO_verification();
		
		login.Login_Page_Title_Verification();
		login.doLogin(pro.getProperty("userName"), TC.get("password"));
		screenshot();
		test.log(LogStatus.INFO, "User name - " + pro.getProperty("userName") + " and Password -- " + pro.getProperty("Password"));
		login.verify_Login_Successfully_Done();
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
