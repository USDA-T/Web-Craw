// TS created by Deepa Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.accept_Alert;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class fillApplCreatePages {
    private static final Logger logger = LogManager.getLogger(fillApplCreatePages.class.getName());

    public static void pageCaseOverviewFillup(WebDriver webDriver, String review_Type, String curr_Reviewer,
            String owner, String supervisor) throws Exception {

        try {
            click_Element(webDriver, "SBA_Case_Overview_Select_Link");

            if (review_Type.length() > 0) {
                Select dropdown1 = new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Review_Type"));
                dropdown1.selectByVisibleText(review_Type);
            }

            if (curr_Reviewer.length() > 0) {
                Select dropdown1 = new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Reviewer_Id"));
                dropdown1.selectByVisibleText(curr_Reviewer);
            }

            if (owner.length() > 0) {
                Select dropdown1 = new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Owner_Id"));
                dropdown1.selectByVisibleText(owner);
            }

            if (supervisor.length() > 0) {
                Select dropdown1 = new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Supervisor_Id"));
                dropdown1.selectByVisibleText(supervisor);
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void answer_Finance_Questions(WebDriver webDriver, String answer_01_Some_Desc,
            String answer_02_Some_Desc, String answer_03_Some_Desc, String answer_04_Some_Desc,
            String answer_05_Some_Desc) throws Exception {
        try {
            if (answer_01_Some_Desc.length() > 0) {
                if (answer_01_Some_Desc == "Yes") {
                    click_Element(webDriver, "Answer_Element_Yes");
                } else {
                    click_Element(webDriver, "Answer_Element_No");
                }
            }

            if (answer_02_Some_Desc.length() > 0) {
                if (answer_02_Some_Desc == "Yes") {
                    click_Element(webDriver, "Answer_Element_02_Yes");
                } else {
                    click_Element(webDriver, "Answer_Element_02_No");
                }
            }

            if (answer_03_Some_Desc.length() > 0) {
                if (answer_03_Some_Desc == "Yes") {
                    click_Element(webDriver, "Answer_Element_03_Yes");
                } else {
                    click_Element(webDriver, "Answer_Element_03_No");
                }
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }

    }

    public static void pageQuestionReviewFillup(WebDriver webDriver) throws Exception {
        click_Element(webDriver, "SBA_Question_Review_Fill_Up");
        click_Element(webDriver, "SBA_Question_Review_Save_Continue");
    }

    public static void pageSignatureReviewFillup(WebDriver webDriver) throws Exception {
        click_Element(webDriver, "SBA_Signature_Review_Fill_Up");
        click_Element(webDriver, "SBA_Signature_Review_Save_Continue");
    }

    public static void page8aFillUp(WebDriver webDriver, String answer01, String path) throws Exception {
        try {
            if (answer01.toUpperCase().contains("YES")) {

                for (int i = 0; i < 10; i++) {
                    try {
                        click_Element(webDriver, "All_Answers_Yes");
                        i = 20;
                    } catch (Exception e) {
                        logger.info(e.toString());
                    }
                }

                new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                click_Element(webDriver, "Application_Common_Submit_Button");
                Thread.sleep(3000);
                click_Element(webDriver, "Application_Common_Submit_Button");

                accept_Alert(webDriver, 14);
                logger.info("Doc has been uploaded and accepted");
            } else {
                try {
                    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_Yes");
                } catch (Exception e1) {
                    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_247_Yes");
                }
                click_Element(webDriver, "Application_Common_Submit_Button");
            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void genericUploadDoc(WebDriver webDriver, String answer01, String path) throws Exception {
        try {
            if (answer01.toUpperCase().contains("YES")) {
                for (int i = 0; i < 10; i++) {
                    try {
                        click_Element(webDriver, "All_Answers_Yes");
                    } catch (Exception e) {
                        logger.info(e.toString());
                        i = 20;
                    }
                }

                new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                accept_Alert(webDriver, 20);
                logger.info("Doc has been uploaded and accepted");

            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void page8aFillUpDunsNo(WebDriver webDriver, String answer01, String path, String duns_No_Given)
            throws Exception {
        try {

            if (answer01.toUpperCase().contains("YES")) {

                for (int i = 0; i < 7; i++) {
                    try {
                        click_Element(webDriver, "All_Answers_Yes");
                    } catch (Exception e) {
                        logger.info(e.toString());
                        i = 20;
                    }
                }

                new newMppUploadDocumentPageDeepa(webDriver).deepaUploadMppDocument(path);

                click_Element(webDriver, "Application_Common_Submit_Button");
                setText_Element(webDriver, "SBA_8a_Duns_Confrm_Text", duns_No_Given);
                click_Element(webDriver, "Search_Duns_No");

                for (int i = 0; i < 3; i++) {
                    if (webDriver.getPageSource().indexOf("You must confirm ") > 0) {
                        click_Element(webDriver, "Search_Duns_No");
                        accept_Alert(webDriver, 10);
                        accept_Alert(webDriver, 10);
                        i = 9999;
                    }
                }

                click_Element(webDriver, "Application_Common_Submit_Button");
                Thread.sleep(1500);
                click_Element(webDriver, "Review_Application");
                Thread.sleep(1500);
                click_Element(webDriver, "Application_Common_Submit_Button");

                accept_Alert(webDriver, 10);
                logger.info("Doc has been uploaded and accepted");
                accept_Alert(webDriver, 10);

            } else {
                try {
                    click_Element(webDriver, "General_Answer_Page_8A_117");
                } catch (Exception e1) {
                    click_Element(webDriver, "General_Answer_Page_8A_228");
                }
                click_Element(webDriver, "Application_Common_Submit_Button");
            }
        } catch (Exception e) {
            logger.info(e.toString());
            take_ScreenShot_TestCaseName(webDriver, new String[] { "page8aFillUpDunsNo" });
            throw e;
        }
    }

    public static void finalSignatureSubmit(WebDriver webDriver) throws Exception {
        try {
            click_Element(webDriver, "SBA_Sig_Submit_Legal_0");
            click_Element(webDriver, "SBA_Sig_Submit_Legal_1");
            click_Element(webDriver, "SBA_Sig_Submit_Legal_2");
            click_Element(webDriver, "SBA_Sig_Submit_Legal_3");
            click_Element(webDriver, "SBA_Sig_Submit_Legal_4");
            click_Element(webDriver, "SBA_Sig_Submit_Legal_5");
            accept_Alert(webDriver, 14);
            click_Element(webDriver, "Application_Common_Submit_Button");
            accept_Alert(webDriver, 8);
        } catch (Exception e) {
            logger.info(e.toString());
            take_ScreenShot_TestCaseName(webDriver, new String[] { "finalSignatureSubmit" });
            throw e;
        }
    }

    public static void finalSignatureSubmit8A(WebDriver webDriver) throws Exception {
        try {
            click_Element(webDriver, "SBA_Sig_Submit_Legal_0");
            accept_Alert(webDriver, 14);
            click_Element(webDriver, "Application_Common_Submit_Button");
            accept_Alert(webDriver, 14);
        } catch (Exception e) {
            logger.info(e.toString());
            take_ScreenShot_TestCaseName(webDriver, new String[] { "finalSignatureSubmit8A" });
            throw e;
        }
    }

}
