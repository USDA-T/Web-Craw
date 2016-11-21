package gov.sba.utils.WorkflowPages;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.utils.LoginPageWithReference;

public class commonApplicationMethods {

	public static Boolean checkApplicationExists(WebDriver webDriver, String type_Of_App,	String status_Of_App) throws Exception {
		// It should be in Vendor Dashboard
		switch(	type_Of_App.toLowerCase() + status_Of_App.toLowerCase()	) {
	        case "edwosbactive":		    		
	    			List<WebElement> listOfActiveEDWOSB = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'ctive')]) and (td/a[position()=1 and contains(text(),'EDWOSB')]) ]"));
	    			if (listOfActiveEDWOSB.size()	> 0){
	    				return true;	
	    			}
	    			return false;
	        case "wosbactive":		    		
	    			List<WebElement> listOfActiveWOSB = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr[  (td[position()=4 and contains(text(),'ctive')]) and (td/a[position()=1 and contains(text(),'WOSB')]) ]"));
	    			if (listOfActiveWOSB.size()	> 0){
	    				return true;	
	    			}
	    			return false;			

	        default:
	        		return false;
		}
	}
	
	public static void deleteApplication(WebDriver	webDriver, String type_Of_App, String status_Of_App)  throws Exception{
		
		switch (type_Of_App.toLowerCase() + status_Of_App.toLowerCase()) {
			case "edwosbdraft":
				List<WebElement> deleteElem = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB')]) and ( td[ position()=4 and contains(text(),'Draft') ] )  ]/td[ position()=6 ]/a[ contains(text(),'Delete') ]  " ) );
				if (deleteElem.size()>0){
					deleteElem.get(0).click();
					Thread.sleep(1000);
					webDriver.switchTo().alert().accept();
				}
				break;
			case "wosbdraft":
				List<WebElement> deleteElem_01 = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB')]) and ( td[ position()=4 and contains(text(),'Draft') ] )  ]/td[ position()=6 ]/a[ contains(text(),'Delete') ]  " ) );
				if (deleteElem_01.size()>0){
					deleteElem_01.get(0).click();
					webDriver.switchTo().alert().accept();
				}
				break;
			case "mppdraft":
				List<WebElement> deleteElem_02 = webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'MPP')]) and ( td[ position()=4 and contains(text(),'Draft') ] )  ]/td[ position()=6 ]/a[ contains(text(),'Delete') ]  " ) );
				if (deleteElem_02.size()>0){
					deleteElem_02.get(0).click();
					webDriver.switchTo().alert().accept();
				}
				break;
		}
		

				
	}

	public static void returnApplicationToVendorMethd( WebDriver webDriver, int which_Loginto_ReturnApp , String duns_Number, String type_Of_App, String status_Of_App, int which_Log_BackAgain )  throws Exception {
			// Login provided should be Analyst
			commonApplicationMethods.navigationMenuClick(webDriver, "Logout");

			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, which_Loginto_ReturnApp);
			login_Data.Login_With_Reference();		
			webDriver.findElement(By.id("query")).sendKeys(duns_Number);
			webDriver.findElement(By.xpath("//*[@id='analyst-search']/div/button[ span[contains(text(),'Search')] ]")).click();
			webDriver.findElement(By.xpath("//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a")).click();
			switch(	type_Of_App.toLowerCase() + status_Of_App.toLowerCase()	) {
				case "edwosbactive":
					webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'EDWOSB') ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]")).click();
					webDriver.switchTo().alert().accept();
					break;
				case "wosbactive":
					webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'WOSB') ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]")).click();
					webDriver.switchTo().alert().accept();
					break;
				case "mppactive":
					webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr[ (td[position()=1]/a[contains(text(),'MPP') ]) and ( td[ position()=4 and contains(text(),'Active') ] ) ]/td[position()=6]/a[contains(text(),'Return to Vendor')]")).click();
					webDriver.switchTo().alert().accept();
					break;
			}

			commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
			login_Data = new LoginPageWithReference(webDriver, which_Log_BackAgain);
    		login_Data.Login_With_Reference();
		
	}
	
	public static void createApplication( WebDriver webDriver,	String type_Of_App ) throws Exception {
		webDriver.findElement(By.xpath("//*[@id='js-navigation-menu']/li/a[contains(text(),'Programs')]")).click();
		switch (type_Of_App.toUpperCase()) {
		case "EDWOSB":
			webDriver.findElement(By.id("certificate_type_edwosb")).click();
			break;
		case "WOSB":
			webDriver.findElement(By.id("certificate_type_wosb")).click();
			break;
		case "MPP":
			webDriver.findElement(By.id("certificate_type_mpp")).click();
			break;
		default:
			Assert.assertEquals("Edwosb or WOSB or MPP", "Not Found");
		}
		webDriver.findElement(By.id("add_certification")).click();
		webDriver.findElement(By.className("accept_button")).click();
	}

	public static void navigationMenuClick( WebDriver webDriver, String which_Button ) throws Exception {
		String part_01 = "//nav[@role='navigation']/ul[@id='js-navigation-menu']/li/a[contains(text(),'";
		String part_03 = "')]";

		switch (which_Button.toUpperCase()) {
			case "LOGOUT":
				webDriver.findElement(By.xpath(part_01 + "Logout" + part_03)).click();
				break;
			case "HELP":
				webDriver.findElement(By.xpath(part_01 + "Help" + part_03)).click();
				break;
			case "CASES":
				webDriver.findElement(By.xpath(part_01 + "ases" + part_03)).click();
				break;
			case "PROGRAMS":
				webDriver.findElement(By.xpath(part_01 + "rograms" + part_03)).click();
				break;
			case "DASHBOARD":
				webDriver.findElement(By.xpath(part_01 + "Dashboard" + part_03)).click();
				break;
			case "BUSINESS":
				webDriver.findElement(By.xpath(part_01 + "Business" + part_03)).click();
				break;
			case "DOCUMENTS":
				webDriver.findElement(By.xpath(part_01 + "Documents" + part_03)).click();
				break;
			default:
				Assert.assertEquals("Navigation Menu Not correct", "among present Options");
		}
	}

}












































