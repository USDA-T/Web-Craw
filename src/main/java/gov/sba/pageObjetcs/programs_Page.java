/**
 * Created by deepa on 5/15/2017.
 */
package gov.sba.pageObjetcs;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import org.openqa.selenium.WebDriver;

public class programs_Page {

    public void select_MyCertifications(WebDriver webDriver, String which_Cert) throws Exception {

        if (!which_Cert.equals(null) && !which_Cert.equals("")) {
            switch (which_Cert) {
                case "8a_Initial_Program":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Create_App_8a_Inital");
                    break;
                case "EDWOSB_Self_Cert":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Create_App_EDWOSB");
                    break;
                case "WOSB_Self_Cert":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Create_App_WOSB");
                    break;
                case "MPP_Program":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Create_App_MPP");
                    break;
                case "8a_Upload":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Create_App_8a_upload");
                    break;
                case "Delete_8a_Initial_Draft":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Delete_App_8a_Inital_Delete");
                    break;
                case "Delete_EDWOSB_Draft":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Delete_App_EDWOSB_Delete");
                    break;
                case "Delete_WOSB_Draft":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Delete_App_WOSB_Delete");
                    break;
                case "Delete_MPP_Draft":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Delete_App_MPP_Delete");
                    break;
                case "Delete_8a_UpD_Draft":
                    click_Element(webDriver, "SBA_My_Cont_Pgm_Delete_App_8a_upload_Delete");
                    break;
            }
        }



    }

}
