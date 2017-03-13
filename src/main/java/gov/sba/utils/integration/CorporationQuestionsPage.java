package gov.sba.utils.integration;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CorporationQuestionsPage {
    private static final Logger logger = LogManager.getLogger(CorporationQuestionsPage.class.getName());
    WebDriver webDriver;

    public CorporationQuestionsPage(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void CorporationQuestions() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        // Locate the accept button at the bottom of the EDWOSB agreement and
        // click on it to continue.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Locate the 8(a) question and select No and continue.
        String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text1 = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
        assertEquals(actual_Text1, expected_Text1);
        // Verify the More detail meaning for the 8(A) question.
        String actual_Text2 = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[2]")).getText();
        String expected_Text2 = "If the qualifying individual is not currently an 8(a) BD Program Participant, please select “No”. If the qualifying individual was already approved by the 8(a) BD Program and at least 51% of the business is held by women, you are eligible for the WOSB Program as an EDWOSB and you will skip forward to the “Review” section of this application. Please upload your original 8(a) Acceptance Letter and your most recent Annual Review Letter.";
        assertEquals(actual_Text2, expected_Text2);
        String actual_Text21 = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[3]")).getText();
        String expected_Text21 = "If the qualifying individual is both 8(a) and Third-Party Certified, upload the documentation for both certifications.";
        assertEquals(actual_Text21, expected_Text21);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        logger.info("  8(a) question has been answered");
        // Locate the Third Party Certification, question1 and select No and
        // continue.
        String actual_Text3 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text3 = "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
        assertEquals(actual_Text3, expected_Text3);
        // Verify the detail meaning for the third party question.
        String actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_tpc1_q1']/fieldset/p[2]")).getText();
        String expected_Text = "You may self-certify for the WOSB Program through this website or you may elect to use the services of a Third-Party Certifier to demonstrate eligibility. There is no requirement to use a Third-Party Certifier. However, if you have worked with an SBA-approved Third-Party Certifier to review your business information, please upload the current Third-Party Certifier Certificate.";
        assertEquals(actual_Text, expected_Text);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Locate the Non-qualification question,Verify,select No and continue.
        String actual_Text4 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text4 = "Has an SBA-approved Third-Party Certifier declined WOSB or EDWOSB certification for the qualifying individual(s)?";
        assertEquals(actual_Text4, expected_Text4);
        // Verify the more detail for the Non-qualification question.
        String actual_Text5 = webDriver.findElement(By.xpath("//div[@id='answers_tpc3_q1']/fieldset/p[2]")).getText();
        String expected_Text5 = "If yes, please upload the denial letter. Being denied eligibility by one of the SBA-approved certifiers does not necessarily prevent you from qualifying for a self-certification if circumstances have changed. Any business determined not to be qualified may request that SBA review its eligibility once it believes in good faith that it satisfies all of the requirements. Reference: 13 C.F.R. 127.305";
        assertEquals(actual_Text5, expected_Text5);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        logger.info("  Third Party questions have been answered");

        // Locate the Three Business Corporation(Stocks) question
        // 1,2and2, Verify select N/A and continue.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Do the corporation’s stock ledger and stock certificates show that the qualifying individual(s) own at least 51% of all outstanding stock?";
        assertEquals(Actual_Text, Expected_Text);
        // Verify the more detail meaning for the Corporation questions.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_corp1_q1']/fieldset/p[2]")).getText();
        Expected_Text = "If yes, please upload the front and back pages of all stock certificates and ledgers.";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_corp1_q3']/fieldset/p[2]")).getText();
        Expected_Text = "Do not consider unexercised stock options that are held by the qualifying individual(s). If you answered “yes” to this question, please return to the questions above and revise your answers.";
        assertEquals(Actual_Text, Expected_Text);
        Thread.sleep(2000);
        webDriver.findElement(By.id("answers_251_value_no")).click();
        webDriver.findElement(By.xpath(".//*[@id='answers_251_comment']")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        webDriver.findElement(By.id("answers_252_value_no")).click();
        Actions act3 = new Actions(webDriver);
        act3.doubleClick(webDriver.findElement(By.id("answers_253_value_no"))).build().perform();
        webDriver.findElement(By.id("answers_254_value_no")).click();
        webDriver.findElement(By.id("answers_255_value_no")).click();
        webDriver.findElement(By.id("answers_256_value_no")).click();
        webDriver.findElement(By.id("answers_257_value_no")).click();
        webDriver.findElement(By.xpath(".//*[@id='answers_257_comment']")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        logger.info("  Business questions have been answered");
        // Locate the Corporation Ownership question 1,Verify, select No and
        // continue.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Do the corporation’s stock ledger and stock certificates show that the qualifying individual(s) own at least 51% of all outstanding stock?";
        assertEquals(Actual_Text, Expected_Text);
        // Verify the detail meaning for the Corporation Ownership question.
        String actual_Text9 = webDriver.findElement(By.xpath("//div[@id='answers_corp3_q1']/fieldset/p[2]")).getText();
        String expected_Text9 = "In determining unconditional ownership of the business, any unexercised stock options or similar agreements held by a woman will be disregarded. However, any unexercised stock option or other agreement, including the right to convert non-voting stock or debentures into voting stock, held by any other individual or entity will be treated as having been exercised. Reference: 13 C.F.R. 127.201(f)";
        assertEquals(actual_Text9, expected_Text9);
        // Locate the Ownership & Control question, Verify, select No and
        // continue.
        Thread.sleep(2000);
        // Verify the more detail meaning for the Ownership & Control question.
        String actual_Text12 = webDriver.findElement(By.cssSelector("#answers_corp5_q1 > fieldset > h4")).getText();
        String expected_Text12 = "Do the Articles of Incorporation and By-laws show that the qualifying individual(s) control the Board of Directors?";
        assertEquals(actual_Text12, expected_Text12);
        // Detail meaning.
        String actual_Text11 = webDriver.findElement(By.xpath("//div[@id='answers_corp5_q1']/fieldset/p[2]")).getText();
        String expected_Text11 = "If yes, please upload Articles of Incorporation and any amendments; By-laws and any amendments; all stock certificates issued, including the front and back copies signed in accordance with the By-laws (this also applies to all cancelled stock certificates); and the Joint Venture Agreement, if applicable. Women are considered to control the Board of Directors when either: (1) one or more women own at least 51% of all voting stock of the business, are on the Board of Directors and have the percentage of voting stock necessary to overcome any super majority voting requirements; or (2) women comprise the majority of voting directors through actual numbers or, where permitted by state law, through weighted voting. Reference: 13 C.F.R. 127.202(f)";
        assertEquals(actual_Text11, expected_Text11);
        //webDriver.findElement(By.xpath(".//*[@id='answers_238_comment']")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        webDriver.findElement(By.name("commit")).click();

        
        
     // Locate the Citizenship & Ownership question 1and2, Verify,select No
        // and continue.
        String actual_Text121 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text121 = "Do the birth certificates, naturalization papers, or passports show the qualifying individual(s) are U.S. citizens?";
        assertEquals(actual_Text121, expected_Text121);
        // Verify the more detail meaning for the Citizenship & Ownership
        // questions.
        String actual_Text13 = webDriver.findElement(By.xpath("//div[@id='answers_oper1_q1']/fieldset/p[2]")).getText();
        String expected_Text13 = "If yes, please upload birth certificates, naturalization papers, or current, unexpired U.S. passports for all qualifying individual(s).";
        assertEquals(actual_Text13, expected_Text13);
        String actual_Text131 = webDriver.findElement(By.xpath("//div[@id='answers_oper1_q1']/fieldset/p[3]"))
                .getText();
        String expected_Text131 = "A Citizen means a person born or naturalized in the United States. Resident aliens and green card holders of permanent visas are not considered to be citizens. Reference: 13 C.F.R. 127.102";
        assertEquals(actual_Text131, expected_Text131);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Locate the Businesses & Trusts questions,Verify, select No for both
        // and continue.
        String actual_Text14 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text14 = "Is the following statement true? The qualifying individual(s) is not subject to any conditions, executory agreements, voting trusts, or other arrangements that cause or potentially cause ownership benefits to go to another person.";
        assertEquals(actual_Text14, expected_Text14);
        // Verify the more detail meaning for the Businesses & Trusts questions
        String actual_Text15 = webDriver.findElement(By.xpath("//div[@id='answers_oper2_q1']/fieldset/p[2]")).getText();
        String expected_Text15 = "Under the WOSB Program, the 51% ownership must be direct and not through another business entity or a trust (including employee stock ownership plan). Companies which attain 51% ownership by a qualifying individual(s) through a trust or other arrangement that is owned and controlled by women are generally not eligible for the program. Reference: 13 CFR 127.201(b)";
        assertEquals(actual_Text15, expected_Text15);
        // 2nd question
        String actual_Text16 = webDriver.findElement(By.cssSelector("#answers_oper2_q2 > fieldset > h4")).getText();
        String expected_Text16 = "If the 51% ownership is held through a trust, is the trust revocable, and does it designate the qualifying individual(s) as the grantor, the trustee, and the sole current beneficiary?";
        assertEquals(actual_Text16, expected_Text16);
        // 2nd question meaning
        String actual_Text17 = webDriver.findElement(By.xpath("//div[@id='answers_oper2_q2']/fieldset/p[2]")).getText();
        String expected_Text17 = "If the ownership is not held through a trust, select N/A. SBA will treat ownership by a trust, such as a living trust, as the functional equivalent of ownership by the qualifying individual where the trust is revocable, and the qualifying individual is the grantor, the trustee, and the sole current beneficiary of the trust. Reference: 13 C.F.R. 127.201(c)";
        assertEquals(actual_Text17, expected_Text17);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        webDriver.findElement(By.id("answers_264_value_no")).click();
        webDriver.findElement(By.id("answers_265_value_no")).click();
        webDriver.findElement(By.id("answers_265_comment")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        
        // Locate the Operations & Management questions, Verify, select No for
        // both and continue.
        // 1st question
        //String actual_Text18 = webDriver.findElement(By.cssSelector("h4")).getText();
        //String expected_Text18 = "Are the management and daily operations of the business controlled by the qualifying individual(s)?";
        //assertEquals(actual_Text18, expected_Text18);
        // 1st question meaning.
        //String actual_Text19 = webDriver.findElement(By.xpath("//div[@id='answers_oper3_q1']/fieldset/p[2]")).getText();
        //String expected_Text19 = "Control means that both the long-term decision making and the day-to-day management and administration of the business operations are conducted by the qualifying individuals. Reference: 13 C.F.R. 127.202(a)";
        //assertEquals(actual_Text19, expected_Text19);
        //webDriver.findElement(By.cssSelector("label.no.last")).click();
        // 2nd question.
        //String actual_Text20 = webDriver.findElement(By.cssSelector("#answers_oper3_q2 > fieldset > h4")).getText();
        //String expected_Text20 = "Does the qualifying individual(s) hold the highest officer position in the business and does she have the managerial experience needed to run the business?";
        //assertEquals(actual_Text20, expected_Text20);
        // 2nd question meaning.
        //String actual_Text22 = webDriver.findElement(By.xpath("//div[@id='answers_oper3_q2']/fieldset/p[2]")).getText();
        //String expected_Text22 = "If yes, please upload a resume to show managerial experience. The woman must have managerial experience of the extent and complexity needed to run the business. Reference: 13 C.F.R. 127.202(b)";
        //assertEquals(actual_Text22, expected_Text22);
        //3rd question.
        //String actual_Text201 = webDriver.findElement(By.cssSelector("#answers_oper4_q1 > fieldset > h4")).getText();
        //String expected_Text201 = "Does the qualifying individual(s) have ultimate managerial and supervisory control over those who possess the required licenses or technical expertise for the business? The qualifying individual(s) herself may have the technical expertise or possess the required license for the business.";
        //assertEquals(actual_Text201, expected_Text201);
        //3rd question meaning.
        //String actual_Text221 = webDriver.findElement(By.xpath("//div[@id='answers_oper4_q1']/fieldset/p[2]")).getText();
        //String expected_Text221 = "The woman manager does not need to have the technical expertise or possess the required license to be found to control the business if she can demonstrate that she has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise. Reference: 13 C.F.R. 127.202(b)";
        //assertEquals(actual_Text221, expected_Text221);
        //webDriver.findElement(By.cssSelector("label.no.last")).click();
        //webDriver.findElement(By.id("answers_244_value_no")).click();
        //webDriver.findElement(By.id("answers_245_value_no")).click();
        //webDriver.findElement(By.id("answers_246_value_no")).click();
        //webDriver.findElement(By.id("answers_246_comment")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        
       //webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Locate the Expertise & Employment questions, Verify, select No for
        // both and continue.
        // 1st question.
        String actual_Text23 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text23 = "Are the management and daily operations of the business controlled by the qualifying individual(s)?";
        assertEquals(actual_Text23, expected_Text23);
        // 1st question meaning.
        String actual_Text24 = webDriver.findElement(By.xpath("//div[@id='answers_oper3_q1']/fieldset/p[2]")).getText();
        String expected_Text24 = "Control means that both the long-term decision making and the day-to-day management and administration of the business operations are conducted by the qualifying individuals. Reference: 13 C.F.R. 127.202(a)";
        assertEquals(actual_Text24, expected_Text24);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        // 2nd question.
        String actual_Text25 = webDriver.findElement(By.cssSelector("#answers_oper3_q2 > fieldset > h4")).getText();
        String expected_Text25 = "Does the qualifying individual(s) hold the highest officer position in the business and does she have the managerial experience needed to run the business?";
        assertEquals(actual_Text25, expected_Text25);
        // 2nd question meaning.
        String actual_Text26 = webDriver.findElement(By.xpath("//div[@id='answers_oper3_q2']/fieldset/p[2]")).getText();
        String expected_Text26 = "If yes, please upload a resume to show managerial experience. The woman must have managerial experience of the extent and complexity needed to run the business. Reference: 13 C.F.R. 127.202(b)";
        assertEquals(actual_Text26, expected_Text26);
        webDriver.findElement(By.id("answers_267_value_no")).click();
        //3rd question.
        String actual_Text251 = webDriver.findElement(By.cssSelector("#answers_oper4_q1 > fieldset > h4")).getText();
        String expected_Text251 = "Does the qualifying individual(s) have ultimate managerial and supervisory control over those who possess the required licenses or technical expertise for the business? The qualifying individual(s) herself may have the technical expertise or possess the required license for the business.";
        assertEquals(actual_Text251, expected_Text251);
        //3rd question meaning.
        String actual_Text261 = webDriver.findElement(By.xpath("//div[@id='answers_oper4_q1']/fieldset/p[2]")).getText();
        String expected_Text261 = "The woman manager does not need to have the technical expertise or possess the required license to be found to control the business if she can demonstrate that she has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise. Reference: 13 C.F.R. 127.202(b)";
        assertEquals(actual_Text261, expected_Text261);
        webDriver.findElement(By.id("answers_268_value_no")).click();
        //4th question.
        String actual_Text252 = webDriver.findElement(By.cssSelector("#answers_oper4_q2 > fieldset > h4")).getText();
        String expected_Text252 = "Does the qualifying individual(s) who holds the highest officer position manage the business on a full-time basis and devote full-time attention to the business during the normal working hours of similar businesses?";
        assertEquals(actual_Text252, expected_Text252);
        //4th question meaning.
        String actual_Text262 = webDriver.findElement(By.xpath("//div[@id='answers_oper4_q2']/fieldset/p[2]")).getText();
        String expected_Text262 = "The qualifying individual(s) may not engage in outside employment that prevents her from devoting sufficient time and attention to the daily affairs or the business. Reference: 13 C.F.R. 127.202(c)";
        assertEquals(actual_Text262, expected_Text262);
        webDriver.findElement(By.id("answers_269_value_no")).click();
        //5th question.
        String actual_Text211 = webDriver.findElement(By.cssSelector("#answers_oper5_q2 > fieldset > h4")).getText();
        String expected_Text211 = "Does the qualifying individual(s) fully control the business, that is, no one else has actual control or has the power to control the business?";
        assertEquals(actual_Text211, expected_Text211);
        //5th question meaning.
        String actual_Text311 = webDriver.findElement(By.xpath("//div[@id='answers_oper5_q2']/fieldset/p[2]")).getText();
        String expected_Text311 = "Men or other entities may be involved in the management of the business and may be stockholders, partners or limited liability members of the business, provided that no males or other entity exercise actual control or have the power to control the business. Reference: 13 C.F.R. 127.202(g)";
        assertEquals(actual_Text311, expected_Text311);
        webDriver.findElement(By.id("answers_270_value_no")).click();
        //6th question meaning.
        String actual_Text331 = webDriver.findElement(By.cssSelector("#answers_oper6_q2 > fieldset > h4")).getText();
        String expected_Text331 = "Is the qualifying individual(s) in control of long-term decision making and day-to-day operations?";
        assertEquals(actual_Text331, expected_Text331);
        webDriver.findElement(By.id("answers_271_value_no")).click();
        webDriver.findElement(By.id("answers_271_comment")).sendKeys(
            "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        logger.info("  Operations questions have been answered");
        // Locate the Highest Officer & Control questions,Verify, select No for
        // both and continue.
        // 1st question.
        String actual_Text27 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text27 = "Is the following statement true? The qualifying individual(s) has not received a decision from the SBA – in connection to an examination or protest – finding that the business does not qualify as a WOSB or an EDWOSB.";
        assertEquals(actual_Text27, expected_Text27);
        // 1st question meaning.
        String actual_Text28 = webDriver.findElement(By.xpath("//div[@id='answers_oper6_q1']/fieldset/p[2]")).getText();
        String expected_Text28 = "Any business that SBA found to be ineligible for the WOSB Program may request that SBA re- examine its WOSB or EDWOSB eligibility at any time if it believes in good faith that it has cured the reason(s) for its ineligibility. Reference: 13 C.F.R. 127.405(g)";
        assertEquals(actual_Text28, expected_Text28);
        webDriver.findElement(By.cssSelector("label.no.last")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        //Net Worth 1st question.
        String actual_Text29 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text29 = "Can the qualifying individual(s) show that her personal net worth (assets – liabilities) is less than $750,000, excluding her ownership interest in the business and her equity interest in her primary personal residence?";
        assertEquals(actual_Text29, expected_Text29);
        // 1st question meaning.
        String actual_Text30 = webDriver.findElement(By.xpath("//div[@id='answers_demonstrate_less_than_750k']/fieldset/p[2]")).getText();
        String expected_Text30 = "In order to be considered economically disadvantaged, the woman's personal net worth must be less than $750,000, excluding her ownership interest in the business and her equity interest in her primary personal residence. Other exclusions include business income reinvested in the business or received for purposes of paying taxes and retirement funds not available until retirement age without a significant penalty. The qualifying individual(s) must provide information on the business income and retirement funds in the Financial Data section to claim exclusions. Reference: 13 C.F.R. Part 127.203(b)";
        assertEquals(actual_Text30, expected_Text30);
        webDriver.findElement(By.id("answers_273_value_no")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Adjusted Gross Income Section
        // for both and continue.
        // 1st question.
        String actual_Text31 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text31 = "Is the qualifying individual’s adjusted gross income averaged over the previous three years at or less than $350,000?";
        assertEquals(actual_Text31, expected_Text31);
        // 1st question meaning.
        String actual_Text32 = webDriver.findElement(By.xpath("//div[@id='answers_agi_3_year_less_than_350k']/fieldset/p[2]")).getText();
        String expected_Text32 = "In answering this question, you may consider the adjusted gross income (AGI) on your Federal income tax return forms (Line 37 on Form 1040; Line 4 on Form 040EZ; or Line 21 on Form 1040(A). Please note that this is rough guidance and should not be construed as the official SBA’s position on calculating the AGI. You will be asked to provide information on your AGI in the Financial Data section.";
        assertEquals(actual_Text32, expected_Text32);
        webDriver.findElement(By.id("answers_274_value_no")).click();
        // 2nd question.
        String actual_Text33 = webDriver.findElement(By.cssSelector("#answers_agi_3_year_exceeds_but_uncommon > fieldset > h4")).getText();
        String expected_Text33 = "Does the adjusted gross income of the qualifying individual(s) averaged over the three years preceding the certification exceed $350,000; however, the woman can show that (1) this income level was unusual and not likely to occur in the future; (2) that losses commensurate with and directly related to the earnings were suffered; or (3) that the income is not indicative of lack of economic disadvantage?";
        assertEquals(actual_Text33, expected_Text33);
        // 2nd question meaning
        String actual_Text34 = webDriver.findElement(By.xpath("//div[@id='answers_agi_3_year_exceeds_but_uncommon']/fieldset/p[2]")).getText();
        String expected_Text34 = "If this situation does not apply, select N/A.";
        assertEquals(actual_Text34, expected_Text34);
        webDriver.findElement(By.id("answers_275_value_no")).click();
        //webDriver.findElement(By.id("answers_256_comment")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Locate the Assets questions,Verify, select No for both and
        // continue.
        // 1st question.
        String actual_Text35 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text35 = "Is the fair market value of all the assets of the qualifying individual(s) at or less than $6 million?";
        assertEquals(actual_Text35, expected_Text35);
        // 1st question meaning.
        String actual_Text352 = webDriver
            .findElement(By.xpath("//div[@id='answers_woman_assets_less_than_6m']/fieldset/p[2]")).getText();
        String expected_Text352 = "Assets include her primary residence and the value of the business.";
        assertEquals(actual_Text352, expected_Text352);
        String actual_Text351 = webDriver
                .findElement(By.xpath("//div[@id='answers_woman_assets_less_than_6m']/fieldset/p[3]")).getText();
        String expected_Text351 = "Funds invested in an Individual Retirement Account (IRA) or other official retirement account that are unavailable until retirement age without a significant penalty will not be considered in determining the qualifying individual’s assets. Reference: 13 C.F.R. 127.203(c)(4)";
        assertEquals(actual_Text351, expected_Text351);
        webDriver.findElement(By.id("answers_276_value_no")).click();
        // 2nd question.
        String actual_Text37 = webDriver
                .findElement(By.cssSelector("#answers_woman_has_not_transferred_assets > fieldset > h4")).getText();
        String expected_Text37 = "Can the qualifying individual(s) confirm that no assets were transferred within two years of the date of EDWOSB certification?";
        assertEquals(actual_Text37, expected_Text37);
        // 2nd question meaning.
        String actual_Text38 = webDriver
                .findElement(By.xpath("//div[@id='answers_woman_has_not_transferred_assets']/fieldset/p[2]")).getText();
        String expected_Text38 = "Assets that a qualifying individual(s) transferred within two years of the date of the concern's certification will be attributed to the qualifying individual(s) if the assets were transferred to an immediate family member, or to a trust that has as a beneficiary an immediate family member. The transferred assets within the two-year period will not be attributed to the woman if the transfer was:";
        assertEquals(actual_Text38, expected_Text38);
        String actual_Text381 = webDriver
                .findElement(By.xpath("//ol/li")).getText();
        String expected_Text381 = "To or on behalf of an immediate family member for that individual's education, medical expenses, or some other form of essential support; or";
        assertEquals(actual_Text381, expected_Text381);
        webDriver.findElement(By.id("answers_277_value_no")).click();
        //3rd qestion.
        String actual_Text371 = webDriver
            .findElement(By.cssSelector("#answers_woman_asset_transfer_excusable > fieldset > h4")).getText();
        String expected_Text371 = "If the qualifying individual(s) transferred assets within two years of the date of the certification, can she confirm that the assets were transferred: (1) to or on behalf of an immediate family member for that individual’s education, medical expenses, or some other form of essential support; or (2) to an immediate family member in recognition of a special occasion, such as a birthday, graduation, anniversary, or retirement?";
        assertEquals(actual_Text371, expected_Text371);
        // 2nd question meaning.
        String actual_Text334 = webDriver
            .findElement(By.xpath("//div[@id='answers_woman_has_not_transferred_assets']/fieldset/p[2]")).getText();
        String expected_Text334 = "Assets that a qualifying individual(s) transferred within two years of the date of the concern's certification will be attributed to the qualifying individual(s) if the assets were transferred to an immediate family member, or to a trust that has as a beneficiary an immediate family member. The transferred assets within the two-year period will not be attributed to the woman if the transfer was:";
        assertEquals(actual_Text334, expected_Text334);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        //Assets
        
        String actual_Text43 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text43 = "Is the fair market value of all the assets of the qualifying individual(s) at or less than $6 million?";
        assertEquals(actual_Text43, expected_Text43);
        // Verify meaning for the Fair Market Value questions.
        String actual_Text44 = webDriver
                .findElement(By.xpath("//div[@id='answers_woman_assets_less_than_6m']/fieldset/p[2]")).getText();
        String expected_Text44 = "Assets include her primary residence and the value of the business.";
        assertEquals(actual_Text44, expected_Text44);
        webDriver.findElement(By.id("answers_278_value_no")).click();
        Thread.sleep(2000);
        String actual_Text45 = webDriver.findElement(By.cssSelector("#answers_woman_has_not_transferred_assets > fieldset > h4")).getText();
        String expected_Text45 = "Can the qualifying individual(s) confirm that no assets were transferred within two years of the date of EDWOSB certification?";
        assertEquals(actual_Text45, expected_Text45);
        // 2nd question meaning.
        String actual_Text46 = webDriver.findElement(By.xpath("//div[@id='answers_woman_has_not_transferred_assets']/fieldset/p[2]")).getText();
        String expected_Text46 = "Assets that a qualifying individual(s) transferred within two years of the date of the concern's certification will be attributed to the qualifying individual(s) if the assets were transferred to an immediate family member, or to a trust that has as a beneficiary an immediate family member. The transferred assets within the two-year period will not be attributed to the woman if the transfer was:";
        assertEquals(actual_Text46, expected_Text46);
        webDriver.findElement(By.id("answers_279_value_no")).click();
        // 2nd question.
        String actual_Text47 = webDriver
                .findElement(By.cssSelector("#answers_woman_asset_transfer_excusable > fieldset > h4")).getText();
        String expected_Text47 = "If the qualifying individual(s) transferred assets within two years of the date of the certification, can she confirm that the assets were transferred: (1) to or on behalf of an immediate family member for that individual’s education, medical expenses, or some other form of essential support; or (2) to an immediate family member in recognition of a special occasion, such as a birthday, graduation, anniversary, or retirement?";
        assertEquals(actual_Text47, expected_Text47);
        // 2nd question meaning.
        String actual_Text48 = webDriver
                .findElement(By.xpath("//div[@id='answers_woman_asset_transfer_excusable']/fieldset/p[2]")).getText();
        String expected_Text48 = "If this situation does not apply, select N/A.";
        assertEquals(actual_Text48, expected_Text48);
        webDriver.findElement(By.id("answers_280_value_no")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        //Economic Disadvantage Section.
        String actual_Text491 = webDriver.findElement(By.xpath("//h4")).getText();
        String expected_Text491 = "Do the financial records of the qualifying individual(s) show that she is economically disadvantaged?";
        assertEquals(actual_Text491, expected_Text491);
        //Detail section.
        String actual_Text521 = webDriver.findElement(By.xpath("//div[@id='answers_woman_financial_condition']/fieldset/p[2]")).getText();
        String expected_Text521 = "Please provide the last three (3) Federal Tax Returns Form 1040 (pages 1 & 2 only), schedules, W-2s, and completed IRS FORM 4506-T for the qualifying individual(s) and their spouses.";
        assertEquals(actual_Text521, expected_Text521);
        String actual_Text5211 = webDriver.findElement(By.xpath("//div[@id='answers_woman_financial_condition']/fieldset/p[3]")).getText();
        String expected_Text5211 = "The personal financial condition of the woman claiming economic disadvantage, including her personal net worth, her adjusted gross income for the past three years (including bonuses, and the value of company stock given in lieu of cash), and the fair market value of all of her assets, whether encumbered or not, will be considered in determining whether she is economically disadvantaged.";
        assertEquals(actual_Text5211, expected_Text5211);
        webDriver.findElement(By.id("answers_281_value_no")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        logger.info("EDWOSB application questions have been answered");
        
        // Validate that user successfully navigated to the Financial Data
        // section.
        String actual_Text49 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text49 = "Financial Data";
        assertEquals(actual_Text49, expected_Text49);
        String actual_Text52 = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        String expected_Text52 = "This section must be completed by each individual claiming economic disadvantage in connection with the 8(a) Program and/or the Women-Owned Small Business Federal Contract Program. If married, the spouse must complete this section, except when the individual and the spouse are legally separated. If separated, provide copy of separation document.";
        assertEquals(actual_Text52, expected_Text52);
        // Validate the Personal Information.
        webDriver.findElement(By.id("answers_282_value_new_button")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("div.DTED_Lightbox_Close")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("answers_282_value_new_button")).click();
        Thread.sleep(2000);
        // Verify that the section to Create new record is been seen by user and
        // enter record2.
        String actual_Text511 = webDriver.findElement(By.className("DTE_Header_Content")).getText();
        String expected_Text511 = "Create new record";
        assertEquals(actual_Text511, expected_Text511);
        logger.info("the page to Create and Add new Record is Present, PASS");
        webDriver.findElement(By.id("DTE_Field_first_name")).sendKeys("Denzel");
        webDriver.findElement(By.id("DTE_Field_last_name")).sendKeys("Washington");
        webDriver.findElement(By.id("DTE_Field_title")).click();
        webDriver.findElement(By.xpath("//option[@value='President']")).click();
        webDriver.findElement(By.id("DTE_Field_ssn")).sendKeys("187669987");
        webDriver.findElement(By.id("DTE_Field_address")).sendKeys("8765 Weems dr");
        webDriver.findElement(By.id("DTE_Field_city")).sendKeys("Manassas");
        webDriver.findElement(By.id("DTE_Field_state")).sendKeys("Virginia");
        webDriver.findElement(By.id("DTE_Field_postal_code")).sendKeys("28776");
        webDriver.findElement(By.id("DTE_Field_country")).sendKeys("United State");
        webDriver.findElement(By.id("DTE_Field_home_phone")).sendKeys("7024762987");
        webDriver.findElement(By.id("DTE_Field_business_phone")).sendKeys("7023764876");
        webDriver.findElement(By.id("DTE_Field_email")).sendKeys("DWashington@mailinator.com");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[3]/button")).click();
        Thread.sleep(3000);
        // Select No for question Is anyone listed above divorced? If yes,
        // please provide separation documents.
        Actions act4 = new Actions(webDriver);
        act4.doubleClick(webDriver.findElement(By.id("answers_283_value_no"))).build().perform();
        // Locate the Continue Button and click on it to continue.
        Thread.sleep(3000);
        webDriver.findElement(By.className("usa-button")).click();
    }
}
