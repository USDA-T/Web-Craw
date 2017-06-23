/**
 * Created by deepa on 5/15/2017.
 */
package gov.sba.pageObjetcs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static gov.sba.automation.CommonApplicationMethods.*;

public class vendor_Dashboard_Page {
    private static final Logger logger = LogManager.getLogger(vendor_Dashboard_Page.class.getName());

    public static List<WebElement> verify_Row_In_A_Table_And_Return(WebDriver webDriver, String[] values_To_Check) throws Exception {



            List<WebElement> table_Rows = find_Elements(webDriver,"SBA_Table_My_Certifications_All_Rows", true);
            for(int i=0;i<table_Rows.size();i++){
                int c1 = 0;
                int c2 = 0;

                WebElement single_Row = table_Rows.get(i);
                List<WebElement> all_Cells=single_Row.findElements(By.xpath("td"));
                for(int j=0;j<all_Cells.size();j++){
                    WebElement cell = all_Cells.get(j);
                    if (values_To_Check[j].length() > 0){
                        c1 = c1+1;
                        if(cell.getText().equals(values_To_Check[j])){
                            c2= c2+1;
                        }
                    }
                } //Second loop: Finish for all cells

                if(c1==c2){
                    return all_Cells;
                }
            }//Frist loop : Finish for all rows
            return null;

    }
}
