package Extent_Reporting;

import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport_By_Manoj {
	
	public static ExtentReports ext;
	public static ExtentReports getReport() {
		if(ext==null) {
			Date d=new Date();
			String FN=d.toString().replace(" ", "_").replace(":", "_")+".html";
			ext=new ExtentReports("D://report//"+FN,true,DisplayOrder.NEWEST_FIRST);
			ext.addSystemInfo("QA Manoj Kushwaha", "172.19.7.194 Testing");
		}
		return ext;
	}

}
