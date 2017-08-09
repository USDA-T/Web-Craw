/* Created BY Deepa Patri*/
package gov.sba.pageObjetcs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import static gov.sba.pageObjetcs.ProgramsPage.generic_file_Upld;

public class EDWOSBCooperationQuestionnairePage {
    private static final Logger logger = LogManager.getLogger(EDWOSBCooperationQuestionnairePage.class.getName());
       public static void Edwosb_Questionnaire_8a_Page(WebDriver webDriver, String  yesno) throws Exception{
    /* Elements tag: 8a_Questionnaire */
        try {
            if (!yesno.equals(null) && !yesno.equals("")) {
                switch (yesno.toLowerCase()) {
                    case "yes":
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_389_Y");
                        generic_file_Upld(webDriver);
                        break;
                    case "no":
                       click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_389_N");
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
                       break;

                }
             }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
     }
    public static void Edwosb_Questionnaire_ThridParty_Page(WebDriver webDriver, String  yesno) throws Exception{
    /* Elements tag: Third Party Certification */
    try{
        if (!yesno.equals(null) && !yesno.equals("")) {
            switch (yesno.toLowerCase()) {
                case "yes":
                    click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_390_Y");
                    generic_file_Upld(webDriver);
                    break;
                case "no":
                    click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_390_N");
                    click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
                    break;
            }
        }

      } catch (Exception e) {
        logger.info(e.toString());
        throw e;
    }
    }
    public static void Edwosb_Questionnaire_ChangeEligiblity_Page(WebDriver webDriver, String  yesno) throws Exception{
    /* Elements tag:  Changes in Eligiblity*/
        try{
            if (!yesno.equals(null) && !yesno.equals("")) {
                switch (yesno.toLowerCase()) {
                    case "yes":
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_391_Y");
                        break;
                    case "no":
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_391_N");
                        break;
                }
                click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }
       // Verify the more detail for the Non-qualification question
        public static void Edwosb_Questionnaire_NonQualification_Page(WebDriver webDriver, String  yesno) throws Exception{
        /* Elements tag: Non-Qualification */
        try{
          if (!yesno.equals(null) && !yesno.equals("")) {
          switch (yesno.toLowerCase()) {
          case "yes":
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_392_Y");
            generic_file_Upld(webDriver);
          break;
         case "no":
          click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_392_N");
          click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
          break;
         }
         }

        } catch (Exception e) {
        logger.info(e.toString());
        throw e;
         }
        }
      public static void EDWOSB_Questionnaire_cooperation_Scorp_Page(WebDriver webDriver,
            String stockYesNo, String votingyesno, String voting51yesno, String unexercisedyesno ,
            String unexercised1yesno, String unexercised2yesno) throws Exception {
            try {
                // Elements tag: @Scorp questions beings
                if (!stockYesNo.equals(null) && !stockYesNo.equals("")) {
                    switch (stockYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_393_Y");
                            generic_file_Upld(webDriver);
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_393_N");
                            setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_393_setText", "QA testing");
                            break;
                    }
                }
               if (!votingyesno.equals(null) && !votingyesno.equals("")) {
                    switch (votingyesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_394_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_394_N");
                            break;
                    }
                }
               if (!voting51yesno.equals(null) && !voting51yesno.equals("")) {
                    switch (voting51yesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_395_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_395_N");
                            break;
                    }
                }
               if (!unexercisedyesno.equals(null) && !unexercisedyesno.equals("")) {
                    switch (unexercisedyesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_396_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_396_N");
                            break;
                    }
                }

                if (!unexercised1yesno.equals(null) && !unexercised1yesno.equals("")) {
                    switch (unexercised1yesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_397_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_397_N");
                            break;
                    }
                }
             if (!unexercised2yesno.equals(null) && !unexercised2yesno.equals("")) {
                switch (unexercised2yesno.toLowerCase()) {
                    case "yes":
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_398_Y");
                        break;
                    case "no":
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_398_N");
                        break;
                }
            }
            if (!unexercised2yesno.equals(null) && !unexercised2yesno.equals("")) {
            switch (unexercised2yesno.toLowerCase()) {
                case "yes":
                    click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_399_Y");
                    generic_file_Upld(webDriver,"EDWOSB_financial_page_Ans_Attahment_All");
                    break;
                case "no":
                    click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_399_N");
                    setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_399_setText", "QA testing");
                    click_Element(webDriver, "Application_Common_Submit_Button");
                    break;
            }
        }

        /* Scorp questions ends */
        } catch (Exception e) {
        logger.info(e.toString());
        throw e;
      }
        }
        public static void Edwosb_Questionnaire_CitizenShip_Page(WebDriver webDriver, String  yesno) throws Exception{
        /* Elements tag:  // CitizenShip */
            try{
                if (!yesno.equals(null) && !yesno.equals("")) {
                    switch (yesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_404_Y");
                            generic_file_Upld(webDriver);
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_404_N");
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
                            break;
                    }
                }

            } catch (Exception e) {
                logger.info(e.toString());
                throw e;
            }
        }
        public static void EDWOSB_Questionnaire_cooperation_Ownership_Page(WebDriver webDriver,
            String ownershipbenfitYesNo, String businessentityYesNo, String ownershipbeneficiaryYesNoNA) throws Exception {
            try {
                // Elements tag: @--// OwnerShip
                if (!ownershipbenfitYesNo.equals(null) && !ownershipbenfitYesNo.equals("")) {
                    switch (ownershipbenfitYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_405_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_405_N");
                            break;
                    }
                }
                if (!businessentityYesNo.equals(null) && !businessentityYesNo.equals("")) {
                    switch (businessentityYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_406_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_406_N");
                            break;
                    }
                  }
                    if (!ownershipbeneficiaryYesNoNA.equals(null) && !ownershipbeneficiaryYesNoNA.equals("")) {
                        switch (ownershipbeneficiaryYesNoNA.toLowerCase()) {
                            case "yes":
                                click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_407_Y");
                                break;
                            case "no":
                                click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_407_N");
                                setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_407_setText", "QA testing");
                                break;
                            case "na":
                                click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_407_NA");
                                break;
                        }
                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
                    }

                } catch (Exception e) {
                    logger.info(e.toString());
                    throw e;
                }
              }
        // Management
        public static void EDWOSB_Questionnaire_cooperation_Management_Page(WebDriver webDriver,
            String managementYesNo, String businessYesNo, String managerialYesNo, String fulltimeYesNo,String controlYesNo,String LongtermYesNo) throws Exception {
            try {
                // Elements tag: @--// Management
                if (!managementYesNo.equals(null) && !managementYesNo.equals("")) {
                    switch (managementYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_408_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_408_N");
                            break;
                    }
                }
                if (!businessYesNo.equals(null) && !businessYesNo.equals("")) {
                    switch (businessYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_409_Y");
                            generic_file_Upld(webDriver);
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_409_N");
                            break;
                    }
                }
                if (!managerialYesNo.equals(null) && !managerialYesNo.equals("")) {
                    switch (managerialYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_410_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_410_N");
                    }
                }
                if (!fulltimeYesNo.equals(null) && !fulltimeYesNo.equals("")) {
                    switch (fulltimeYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_411_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_411_N");
                            break;
                    }
                }
                if (!controlYesNo.equals(null) && !controlYesNo.equals("")) {
                                switch (controlYesNo.toLowerCase()) {
                                    case "yes":
                                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_412_Y");
                                        break;
                                    case "no":
                                        click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_412_N");
                                        break;
                    }
                }
                if (!LongtermYesNo.equals(null) && !LongtermYesNo.equals("")) {
                    switch (LongtermYesNo.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_413_Y");
                             break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_413_N");
                            setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_403_setText", "QA testing");
                            break;
                    }
                }
             click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            } catch (Exception e) {
                logger.info(e.toString());
                throw e;
            }
        }
        public static void Edwosb_Questionnaire_SBAExam_Page(WebDriver webDriver, String  yesno) throws Exception{
        /* Elements tag:  /* SBA EXAM */
            try{
                if (!yesno.equals(null) && !yesno.equals("")) {
                    switch (yesno.toLowerCase()) {
                        case "yes":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_414_Y");
                            break;
                        case "no":
                            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_414_N");
                            break;
                    }
                }
                click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            } catch (Exception e) {
                logger.info(e.toString());
                throw e;
            }
        }
       public static void Edwosb_Questionnaire_NetWorth_Page(WebDriver webDriver, String  yesno) throws Exception{
        /* Elements tag:  /* Net Worth */
           try{
               if (!yesno.equals(null) && !yesno.equals("")) {
                   switch (yesno.toLowerCase()) {
                       case "yes":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_415_Y");
                           break;
                       case "no":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_415_N");
                           break;
                   }
               }
               click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
           } catch (Exception e) {
               logger.info(e.toString());
               throw e;
           }
       }
       public static void Edwosb_Questionnaire_AdjustedGrossIncome_Page(WebDriver webDriver, String  yesno,String  agiexYesNoNa) throws Exception{
        /* Elements tag:  /* Adjusted Gross Income */
           try{
               if (!yesno.equals(null) && !yesno.equals("")) {
                   switch (yesno.toLowerCase()) {
                       case "yes":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_416_Y");
                           break;
                       case "no":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_416_N");
                           break;
                   }
               }
               if (!agiexYesNoNa.equals(null) && !agiexYesNoNa.equals("")) {
                   switch (agiexYesNoNa.toLowerCase()) {
                       case "yes":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_417_Y");
                           setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_417_setText","QA testing");
                           break;
                       case "no":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_417_N");
                           break;
                       case "na":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_417_NA");
                           break;
                   }
               }
               click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
           } catch (Exception e) {
               logger.info(e.toString());
               throw e;
           }
       }
          // Assets
          public static void Edwosb_Questionnaire_Assets_Page(WebDriver webDriver, String  fairmarketyesno,
              String  noassetYesNoNa, String  asset1YesNo) throws Exception{
        /* Elements tag:  /* Adjusted Gross Income */
              try{
                  if (!fairmarketyesno.equals(null) && !fairmarketyesno.equals("")) {
                      switch (fairmarketyesno.toLowerCase()) {
                          case "yes":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_418_Y");
                              break;
                          case "no":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_418_N");
                              break;
                      }
                  }
                  if (!noassetYesNoNa.equals(null) && !noassetYesNoNa.equals("")) {
                      switch (noassetYesNoNa.toLowerCase()) {
                          case "yes":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_419_Y");
                              break;
                          case "no":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_419_N");
                              break;
                      }
                  }
                  if (!asset1YesNo.equals(null) && !asset1YesNo.equals("")) {
                      switch (asset1YesNo.toLowerCase()) {
                          case "yes":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_420_Y");
                              setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_420_setText","QA testing");
                              break;
                          case "no":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_420N");
                              break;
                          case "na":
                              click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_420_NA");
                              break;
                      }
                  }
                  click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
              } catch (Exception e) {
                  logger.info(e.toString());
                  throw e;
              }
          }
       public static void Edwosb_Questionnaire_EconomicDisadvantage_Page(WebDriver webDriver, String  yesno) throws Exception{
        /* Elements tag:  /* Economic Disadvantage */
           try{
               if (!yesno.equals(null) && !yesno.equals("")) {
                   switch (yesno.toLowerCase()) {
                       case "yes":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_421_Y");
                           generic_file_Upld(webDriver);
                           break;
                       case "no":
                           click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_421_N");
                           break;
                   }
               }
               click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
           } catch (Exception e) {
               logger.info(e.toString());
               throw e;
           }
       }
 }

