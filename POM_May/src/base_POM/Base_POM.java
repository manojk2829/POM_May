package base_POM;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Excel_reader_Util.Xls_Reader;
import Extent_Reporting.ExtentReport_By_Manoj;

public class Base_POM {
	public Properties pro;
	public WebDriver dr;
	public ExtentReports ext=ExtentReport_By_Manoj.getReport();
	public ExtentTest test;
	public Xls_Reader xls;
	public String sheetName = "data";
	public String TestCase_Name = "MMS_Login_Test";
	public Logger log=Logger.getLogger(Base_POM.class.getName());
			
	
	public Base_POM() {
		init();
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\log_Property\\log4j.properties");
		xls=new Xls_Reader(pro.getProperty("ExcelPath"));
	}
	
	public Base_POM(WebDriver dr,ExtentTest test) {
         this.dr=dr;
         this.test=test;
	}
	
	public void init(){
		pro=new Properties();
		try{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\property_package\\or.properties");
		    pro.load(fis);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(pro.getProperty("appurl"));
	}
	
	public void openBro(String b) {
		if(b.equalsIgnoreCase("chrome")) {
			 System.setProperty("webdriver.chrome.driver", pro.getProperty("Chrome_Browser_exe_Path"));
			 dr=new ChromeDriver();
		}else {
			 System.setProperty("webdriver.gecko.driver", pro.getProperty("Firefox_Browser_exe_Path"));
			 dr=new FirefoxDriver();
		}
		 dr.manage().window().maximize();
		 dr.manage().deleteAllCookies();
		 dr.manage().timeouts().implicitlyWait(19, TimeUnit.SECONDS);
		 log.info("Browser open---- " + b);
	}
	
	public void navigate(String url) {
		dr.get(url);
		test.log(LogStatus.INFO, "URL enter successfully...."+ url);
		 log.info("Browser open---- " + url);
		
	}
	
	public void input_Type(String locater,String d) {
		getElement(locater).sendKeys(d);
		test.log(LogStatus.INFO, "Enter the Text value in text box...."+ d);
	}
	
	public void input_Type_Click(String locater) {
		getElement(locater).sendKeys(Keys.ENTER);
	}
	
	public void Click_BTN_LINK(String locater){
		getElement(locater).click();
		test.log(LogStatus.INFO, "Click on Button or Link... ");
	}
	
	public void select_DropDownValue(String locater,String data) {
		Select dropDown_value = new Select(getElement(locater));
		dropDown_value.selectByVisibleText(data);
		test.log(LogStatus.INFO, "Select value by Visible Text from dropdown list.");
	}
	
	public void select_DropDown(String locater,int Index){
		Select dropValue_By_Index = new Select(getElement(locater));
		dropValue_By_Index.selectByIndex(Index);
		test.log(LogStatus.INFO, "Select value by Index from dropdown list.");
	}
	
	public void radioBTN(String locater) {
		WebElement radio_btn_click=getElement(locater);
		if(!radio_btn_click.isSelected()) {
			radio_btn_click.click();
			test.log(LogStatus.INFO, "Radio button not Selected -- Now click to select");
		}
	}
	
	public void login_Orange_App() {
		 openBro(pro.getProperty("Browser"));
		 log.info("Browser open successfully.... " + pro.getProperty("Browser"));
		 navigate(pro.getProperty("appurl"));
		 getTitle_AfterLogin();
		 input_Type("userName_name", pro.getProperty("User_name"));
		 input_Type("userPass_name", pro.getProperty("User_Password"));
		 Click_BTN_LINK("Login_id");
		 wait(2);
		 screenshot();
	}
	
	public void getTitle_AfterLogin() {
		dr.getTitle();
	}
	
	public WebElement getElement(String loc) {
		WebElement we=null;
		try{
			if(loc.endsWith("_id")) {
			we=dr.findElement(By.id(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by ID");
		}else if(loc.endsWith("_name")) {
			we=dr.findElement(By.name(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by NAME");
		}else if(loc.endsWith("_xpath")) {
			we=dr.findElement(By.xpath(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by XPATH");
		}else {
			System.out.println("Locater not found");
			test.log(LogStatus.FAIL, "Locater Not found on Page....");
			reportfail("Reporting Fail");
		  }
	  } catch(Exception ex) {
		System.out.println(ex.getMessage());
	  }
		return we;
    }
	
	public boolean Is_Element_Present(String loc) {
		List<WebElement> we=null;		
		try{
			if(loc.endsWith("_id")) {
			we=dr.findElements(By.id(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by ID");
		}else if(loc.endsWith("_name")) {
			we=dr.findElements(By.name(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by NAME");
		}else if(loc.endsWith("_xpath")) {
			we=dr.findElements(By.xpath(pro.getProperty(loc)));
			test.log(LogStatus.INFO, "WebElement Found by XPATH");
		}else {
			System.out.println("Locater not found");
			test.log(LogStatus.FAIL, "Locater Not found on Page....");
			reportfail("Reporting Fail");
		  }
	  } catch(Exception ex) {
		System.out.println(ex.getMessage());
	  }
		if(we.size()!=0) {
			System.out.println("Element found on Page..... ");
			test.log(LogStatus.INFO, "WebElement persent on Page.");
			return true;
		}else {
			System.out.println("Element not found ..... ");
			test.log(LogStatus.INFO, "WebElement is not persent on Page.");			
			return false;
		}
    }
	
	public void reportfail(String msg) {
		screenshot_Fail();
		test.log(LogStatus.FAIL, "Reporting Fail.... Method.");
	}

	public void wait(int s) {
		try {
			Thread.sleep(s*1000);
			test.log(LogStatus.INFO, "WebDriver wait for " + s + " sec.");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
	   }
	}
	
	public void quit_Browser(int s) {
		try {
			Thread.sleep(s*1000);
			test.log(LogStatus.INFO, "WebDriver wait for " + s + "Sec to Quit the Browser.");
			dr.quit();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
	   }
	}
	
	public void screenshot() {
		 Date d=new Date();
		 String FN=d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		 File srcFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		 try{
			 FileHandler.copy(srcFile, new File(System.getProperty("user.dir")+"//report//"+FN));
		 }catch(Exception ex) {
			 System.out.println(ex.getMessage());
		 }
		 test.log(LogStatus.INFO,"Take Screenshot -- > " + test.addScreenCapture(System.getProperty("user.dir")+"//report//"+FN));
		 log.info("Screen shot taken..... Save in D drive. ");
	}
	
	public void screenshot_Fail() {
		 Date d=new Date();
		 String FN=d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		 File srcFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		 try{
			 FileHandler.copy(srcFile, new File(System.getProperty("user.dir")+"//report_Fail//"+FN));
		 }catch(Exception ex) {
			 System.out.println(ex.getMessage());
		 }
		 test.log(LogStatus.INFO,"Take Screenshot -- > " + test.addScreenCapture(System.getProperty("user.dir")+"//report_fail//"+FN));
	}
}
