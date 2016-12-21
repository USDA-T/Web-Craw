package gov.sba.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit. * ;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import junit.framework.TestCase;

public class TestApp170LinkDunsNo extends TestCase {
	// Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp170LinkDunsNo = LogManager.getLogger(TestApp170LinkDunsNo.class.getName());
    int get_The_Row_From_Login_Data;
    @Before
    public void setUp() throws Exception
    {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 11;

    }

    @Test
    public void testMainTest() throws Exception
    {
        try
        {
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);
            //Click on Case Link on main navigator
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            String duns_Number = "159165917";
            String typ_App_Passed = "WOSB";
            String xpath_Value = "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + 
        			duns_Number + "')]	and " + "td[position()=3 and contains(text(), '" + typ_App_Passed + "')]" + "]";            
            //All cases page 
            TestApp170LinkDunsNo.info(xpath_Value);
            List<WebElement> current_Row = webDriver.findElements(By.xpath(xpath_Value));
            TestApp170LinkDunsNo.info(current_Row.size());
              current_Row.get(0).findElement(By.xpath("//"
              		+ "td[2]/a")).click();
              WebElement aset_Exist = webDriver.findElement(By.xpath("//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]"));
              //Vendor Overview page 
              assertEquals(aset_Exist.getText(), "DUNS:" + duns_Number);           
        }
        catch(Exception e) {
        	TestApp170LinkDunsNo.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        //webDriver.quit();
    }
}
