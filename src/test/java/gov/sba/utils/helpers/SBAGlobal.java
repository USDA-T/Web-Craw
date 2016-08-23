package gov.sba.utils.helpers;

import java.util.Map;
//__ Java Utils
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.UswdsEdwosbScorpTest;
//__ Selenium
import gov.sba.utils.helpers.Load_Yaml;


public class SBAGlobal {
	
	static WebDriver Current_Driver;
	
	public static void set_Driver(WebDriver Driver_Passed){
		Current_Driver = Driver_Passed;
	}
	
	public  WebDriver Current_Browser(){
		return Current_Driver;
		
	}
	
	public Map<?, ?> Load_Given_Yaml_File(String File_Name) throws Exception {
		Logger logger = LogManager.getLogger(UswdsEdwosbScorpTest.class.getName());
		Map<?, ?> SBA_Elements = null;

		try{
			Load_Yaml ss = new Load_Yaml(File_Name);			
			SBA_Elements = (Map<?, ?>)(ss.Load_Values());
			return SBA_Elements;
		}
		catch (Exception e) 
   		{
			logger.info("There are(is) Error in YALML File" + e.toString());
			return SBA_Elements;
		}
	}
}
