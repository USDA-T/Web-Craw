package gov.sba.utils.helpers;


import static org.junit.Assert.assertEquals;import static org.junit.Assert.assertTrue;
//__Assertions
import java.lang.reflect.Field;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;
//__ Java Utils
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
//__ Logger
import org.junit.After; import org.junit.Before; import org.junit.Test;
//__ JUnit
import org.openqa.selenium.By; import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;
//__ Selenium
import gov.sba.utils.helpers.Load_Yaml; import gov.sba.utils.helpers.LoginHelpers;import gov.sba.utils.helpers.SBAGlobal;
//_ Project Helpers


public class Find_Selenium  {
	WebElement found_Element;
	
	
	public WebElement Find_Element(String Type_Of_Find, String Element_Identifier, String Find_Or_Not_Find){

        switch (Type_Of_Find.toUpperCase()) {
            case "XPATH" :  
            	 found_Element = SBAGlobal.Current_Driver.findElement(By.xpath(Element_Identifier));
                break;
            case "ID" :  
           	 	 found_Element = SBAGlobal.Current_Driver.findElement(By.id(Element_Identifier));
               break;
            case "TAGNAME" :  
           	 	found_Element = SBAGlobal.Current_Driver.findElement(By.tagName(Element_Identifier));
               break;               
            default: 
            	assertEquals("Given Type of find", "Is NOt Matching");
                break;
        }
		return found_Element;
	}
	
}
