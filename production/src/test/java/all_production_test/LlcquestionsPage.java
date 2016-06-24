package all_production_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LlcquestionsPage {
	WebDriver mydriver;

	public LlcquestionsPage(WebDriver mydriver) {
		this.mydriver = mydriver;
	}

	public void Llcquestions() throws Exception {

		// Locate the accept button at the bottom of the EDWOSB agreement and
		// click on it to continue.
		mydriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();

		// Locate the 8(a) question and select No and continue.
		String actual_Text1 = mydriver.findElement(By.xpath(".//*[@id='answers_8aq1']/div/fieldset/legend")).getText();
		String expected_Text1 = "The concern is currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and the 51% owner is a woman or women, or an economically disadvantaged woman or women. ";
		assertEquals(actual_Text1, expected_Text1);
		// Verify the More detail meaning for the 8(A) question.
		String actual_Text2 = mydriver.findElement(By.xpath(".//*[@id='collapsible-65']/div/p")).getText();
		String expected_Text2 = "If your company has already been approved by the 8(a) Program and confirmed 51% ownership is a woman or an economically disadvantaged woman, you already qualify for the WOSB Program. Please upload or attach your original 8(a) Acceptance Letter and a copy of your most recent updated Annual 8(a) Letter, and click the �Save and Continue� button to complete the WOSB or EDWOSB self-certification process.";
		assertEquals(actual_Text2, expected_Text2);
		mydriver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  8(a) question has been answered");
		// Locate the Third Party Certification, question1 and select No and
		// continue.
		String actual_Text3 = mydriver.findElement(By.xpath(".//*[@id='answers_tpc1_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text3 = "The concern is certified as a WOSB or EDWOSB in accordance with Section 8(m) of the Small Business Act, by an SBA-approved third-party certifier.";
		assertEquals(actual_Text3, expected_Text3);
		// Verify the detail meaning for the third party question.
		String actual_Text = mydriver.findElement(By.xpath(".//*[@id='collapsible-66']/div/p[1]")).getText();
		String expected_Text = "A Women-owned small business and economically disadvantaged women-owned small business may self-certify for the WOSB Program through this website or they may elect to use the services of a Third Party Certifier (TPC) to demonstrate eligibility. There is no requirement to use a TPC. However, if you have engaged a SBA-approved TPC to review your company information, you need to provide a copy of the most recent Third Party Certificate provided by the TPC and the Annual TCP Certificate if applicable.\n\nA concern that seeks EDWOSB or WOSB Certification from an SBA-approved certifier must submit its application directly to the approved certifier in accordance with the specific application procedures of the particular certifier. Any interested party may obtain such certification information and application by contacting the approved certifier at the address provided on SBA�s list of approved certifiers.\n\nThe only SBA Approved Third Party Certifiers are:";
		assertEquals(actual_Text, expected_Text);
		mydriver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Non-qualification question,Verify,select No and continue.
		String actual_Text4 = mydriver.findElement(By.xpath(".//*[@id='answers_tpc3_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text4 = "The concern is in receipt of a decision by an SBA-approved third-party certifier that the concern does not qualify as a WOSB or EDWOSB in accordance with Section 8(m) of the Small Business Act.";
		assertEquals(actual_Text4, expected_Text4);
		// Verify the more detail for the Non-qualification question.
		String actual_Text5 = mydriver.findElement(By.xpath(".//*[@id='collapsible-68']/div/p")).getText();
		String expected_Text5 = "Being denied eligibility by one of the SBA approved TPCs does not necessarily prevent you from qualifying for a self-certification if the circumstances of denial have changed. Any concern determined not to be a qualified WOSB or EDWOSB may request that SBA conduct an examination to determine its WOSB or EDWOSB eligibility at any time once it believes in good faith that it satisfies all of the eligibility requirements to qualify as an WOSB or EDWOSB.";
		assertEquals(actual_Text5, expected_Text5);
		mydriver.findElement(By.xpath(".//*[@id='answers[68][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Third Party questions have been answered");

		// Locate the LLC two question,verify, select NO, Write comment and
		// continue.
		// 1st question.
		String actual_Text6 = mydriver.findElement(By.xpath(".//*[@id='answers_llc_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text6 = "If a limited liability company, the articles of organization and any amendments, and operating agreement and amendments, evidence that at least 51% of each class of member interest is unconditionally and directly owned by one or more women or economically disadvantaged women.";
		assertEquals(actual_Text6, expected_Text6);
		// 1st question meaning.
		String actual_Text7 = mydriver.findElement(By.xpath(".//*[@id='collapsible-78']/div/p")).getText();
		String expected_Text7 = "Provide evidence that at least 51% of each class of member interest is unconditionally and directly owned by one or more women or economically disadvantaged women.\n\nUpload Articles of Organization (also referred to as certificate of organization, or articles of formation) and any Amendments, Joint Venture (JV) Agreement, if applicable, Operating Agreement and any Amendments and the Assumed/Fictitious Name Certificate (Doing Business As (DBA)).";
		assertEquals(actual_Text7, expected_Text7);
		mydriver.findElement(By.xpath(".//*[@id='answers[78][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_78_comment']")).sendKeys("Testing");
		// 2nd question.
		String actual_Text8 = mydriver.findElement(By.xpath(".//*[@id='answers_llc_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text8 = "If a limited liability company, the articles of organization and any amendments, and operating agreement and amendments evidence that one or more women or economically disadvantaged women serve as management members, with control over all decisions of the limited liability company.";
		assertEquals(actual_Text8, expected_Text8);
		// 2nd question meaning.
		String actual_Text9 = mydriver.findElement(By.xpath(".//*[@id='collapsible-79']/div/p")).getText();
		String expected_Text9 = "Provide evidence that one or more women and/or economically disadvantaged women serve as management members, with control over all decisions.\n\nUpload Articles of Organization (also referred to as certificate of organization, or articles of formation) and any Amendments, Joint Venture (JV) Agreement, if applicable, Operating Agreement and any Amendments.";
		assertEquals(actual_Text9, expected_Text9);
		mydriver.findElement(By.xpath(".//*[@id='answers[79][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_79_comment']")).sendKeys("Testing");
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The LLC questions have been answered");

		// Locate the Citizenship & Ownership question 1and2, Verify,select No
		// and continue.
		String actual_Text12 = mydriver.findElement(By.xpath(".//*[@id='answers_oper1_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text12 = "The birth certificates, naturalization papers, or passports for owners who are women or economically disadvantaged women show that the business concern is at least 51% owned and controlled by women or economically disadvantaged women who are U.S. citizens.";
		assertEquals(actual_Text12, expected_Text12);
		String actual_Text0 = mydriver.findElement(By.xpath(".//*[@id='answers_oper1_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text0 = "The ownership by women or economically disadvantaged women is not subject to any conditions, executory agreements, voting trusts, or other arrangements that cause or potentially cause ownership benefits to go to another.";
		assertEquals(actual_Text0, expected_Text0);
		// Verify the more detail meaning for the Citizenship & Ownership
		// questions.
		String actual_Text13 = mydriver.findElement(By.xpath(".//*[@id='collapsible-80']/div/p")).getText();
		String expected_Text13 = "Upload a copy of birth certificates, naturalization papers, or current, un-expired U.S. passports for all women or economically disadvantaged women owner(s).\n\nA Citizen means a person born or naturalized in the United States. Resident aliens and green card holders of permanent visas are not considered to be citizens. 13 C.F.R. 127.102.";
		assertEquals(actual_Text13, expected_Text13);
		mydriver.findElement(By.xpath(".//*[@id='answers[80][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[81][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Businesses & Trusts questions,Verify, select No for both
		// and continue.
		String actual_Text14 = mydriver.findElement(By.xpath(".//*[@id='answers_oper2_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text14 = "The 51% ownership by women or economically disadvantaged women is not through another business entity (including employee stock ownership plan) that is, in turn, owned and controlled by one or more women.";
		assertEquals(actual_Text14, expected_Text14);
		// Verify the more detail meaning for the Businesses & Trusts questions
		String actual_Text15 = mydriver.findElement(By.xpath(".//*[@id='collapsible-82']/div/p")).getText();
		String expected_Text15 = "Under the WOSB Program the business must directly owned AND controlled by at least 51% women, or economically disadvantaged women.";
		assertEquals(actual_Text15, expected_Text15);
		// 2nd question
		String actual_Text16 = mydriver.findElement(By.xpath(".//*[@id='answers_oper2_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text16 = "The 51% ownership by women or economically disadvantaged women is held through a trust, the trust is revocable, and the woman or economically disadvantaged woman is the grantor, a trustee, and the sole current beneficiary of the trust.";
		assertEquals(actual_Text16, expected_Text16);
		// 2nd question meaning
		String actual_Text17 = mydriver.findElement(By.xpath(".//*[@id='collapsible-83']/div/p")).getText();
		String expected_Text17 = "SBA will treat ownership by a trust, such as a living trust, as the functional equivalent of ownership by a woman or economically disadvantaged woman where the trust is revocable, and the woman is the grantor, the trustee, and the sole current beneficiary of the trust.";
		assertEquals(actual_Text17, expected_Text17);
		mydriver.findElement(By.xpath(".//*[@id='answers[82][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[83][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Locate the Operations & Management questions, Verify, select No for
		// both and continue.
		// 1st question
		String actual_Text18 = mydriver.findElement(By.xpath(".//*[@id='answers_oper3_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text18 = "The management and daily business operations of the concern are controlled by one or more women, or economically disadvantaged women. Control means that both the long-term decision making and the day-to-day management and administration of the business operations are conducted by one or more women or economically disadvantaged women.";
		assertEquals(actual_Text18, expected_Text18);
		// 1st question meaning.
		String actual_Text19 = mydriver.findElement(By.xpath(".//*[@id='collapsible-84']/div/p")).getText();
		String expected_Text19 = "Management and daily business operations are controlled by one or more women or economically disadvantaged women.";
		assertEquals(actual_Text19, expected_Text19);
		mydriver.findElement(By.xpath(".//*[@id='answers[84][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text20 = mydriver.findElement(By.xpath(".//*[@id='answers_oper3_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text20 = "A woman or economically disadvantaged woman holds the highest officer position in the concern and her resume evidences that she has the managerial experience of the extent and complexity needed to run the concern.";
		assertEquals(actual_Text20, expected_Text20);
		// 2nd question meaning.
		String actual_Text22 = mydriver.findElement(By.xpath(".//*[@id='collapsible-85']/div/p")).getText();
		String expected_Text22 = "(Resume Optional) The woman must have managerial experience of the extent and complexity needed to run the concern. The woman manager need not have the technical expertise or possess the required license to be found to control the concern if she can demonstrate that she has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise. However, if a man possesses the required license and has an equity interest in the concern, he may be found to control the concern.";
		assertEquals(actual_Text22, expected_Text22);
		mydriver.findElement(By.xpath(".//*[@id='answers[85][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Expertise & Employment questions, Verify, select No for
		// both and continue.
		// 1st question.
		String actual_Text23 = mydriver.findElement(By.xpath(".//*[@id='answers_oper4_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text23 = "The woman or economically disadvantaged woman manager does not have the technical expertise or possess the required license for the business but has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise.";
		assertEquals(actual_Text23, expected_Text23);
		// 1st question meaning.
		String actual_Text24 = mydriver.findElement(By.xpath(".//*[@id='collapsible-86']/div/p")).getText();
		String expected_Text24 = "The woman or women or economically disadvantaged women must have managerial experience of the extent and complexity needed to run the concern. The woman manager need not have the technical expertise or possess the required license to be found to control the concern if she can demonstrate that she has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise. However, if a man possesses the required license and has an equity interest in the concern, he may be found to control the concern.";
		assertEquals(actual_Text24, expected_Text24);
		mydriver.findElement(By.xpath(".//*[@id='answers[86][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text25 = mydriver.findElement(By.xpath(".//*[@id='answers_oper4_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text25 = "The woman or economically disadvantaged woman who holds the highest officer position of the concern manages it on a full-time basis and devotes full-time to the business concern during the normal working hours of business concerns in the same or similar line of business.";
		assertEquals(actual_Text25, expected_Text25);
		// 2nd question meaning.
		String actual_Text26 = mydriver.findElement(By.xpath(".//*[@id='collapsible-87']/div/p")).getText();
		String expected_Text26 = "The woman or economically disadvantaged woman who holds the highest officer position of the concern must manage it on a fulltime basis and devote full-time to the business concern during the normal working hours of business concerns in the same or similar line of business.";
		assertEquals(actual_Text26, expected_Text26);
		mydriver.findElement(By.xpath(".//*[@id='answers[87][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Operations questions have been answered");

		// Locate the Highest Officer & Control questions,Verify, select No for
		// both and continue.
		// 1st question.
		String actual_Text27 = mydriver.findElement(By.xpath(".//*[@id='answers_oper5_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text27 = "The woman or economically disadvantaged woman who holds the highest officer position does not engage in outside employment that prevents her from devoting sufficient time and attention to the daily affairs of the concern to control its management and daily business operations.";
		assertEquals(actual_Text27, expected_Text27);
		// 1st question meaning.
		String actual_Text28 = mydriver.findElement(By.xpath(".//*[@id='collapsible-88']/div/p")).getText();
		String expected_Text28 = "The woman or economically disadvantaged woman who holds the highest officer position may not engage in outside employment that prevents her from devoting sufficient time and attention to the daily affairs of the concern to control its management and daily business operations.";
		assertEquals(actual_Text28, expected_Text28);
		mydriver.findElement(By.xpath(".//*[@id='answers[88][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text29 = mydriver.findElement(By.xpath(".//*[@id='answers_oper5_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text29 = "No males or other entity exercise actual control or have the power to control the concern.";
		assertEquals(actual_Text29, expected_Text29);
		// 2nd question meaning.
		String actual_Text30 = mydriver.findElement(By.xpath(".//*[@id='collapsible-89']/div/p")).getText();
		String expected_Text30 = "The management and daily business operations of the concern must be controlled by one or more women or economically disadvantaged women. Control means that both the long-term decision making and the day-to-day management and administration of the business operations must be conducted by one or more women or economically disadvantaged women.";
		assertEquals(actual_Text30, expected_Text30);
		mydriver.findElement(By.xpath(".//*[@id='answers[89][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the SBA Exam & Daily Operations questions,Verify, select No
		// for both and continue.
		// 1st question.
		String actual_Text31 = mydriver.findElement(By.xpath(".//*[@id='answers_oper6_q1']/div/fieldset/legend"))
				.getText();
		String expected_Text31 = "SBA, in connection with an examination or protest, has not issued a decision currently in effect finding that this business concern does not qualify as a WOSB or an EDWOSB.";
		assertEquals(actual_Text31, expected_Text31);
		// 1st question meaning.
		String actual_Text32 = mydriver.findElement(By.xpath(".//*[@id='collapsible-90']/div/p")).getText();
		String expected_Text32 = "SBA has not issued a decision that states that the firm is not currently eligible for WOSB or EDWOSB under the WOSB Program.\n\nBeing denied eligibility does not necessarily prevent you from qualifying for a self-certification if the circumstances of denial have changed. Any concern determined not to be a qualified WOSB or EDWOSB may request that SBA re- examination to determine its WOSB or EDWOSB eligibility at any time once it believes in good faith that it satisfies all of the eligibility requirements to qualify as a WOSB or EDWOSB under the WOSB Program.";
		assertEquals(actual_Text32, expected_Text32);
		mydriver.findElement(By.xpath(".//*[@id='answers[90][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text33 = mydriver.findElement(By.xpath(".//*[@id='answers_oper6_q2']/div/fieldset/legend"))
				.getText();
		String expected_Text33 = "Can the owner certify that they are in control of the day-to-day operations?";
		assertEquals(actual_Text33, expected_Text33);
		// 2nd question meaning
		String actual_Text34 = mydriver.findElement(By.xpath(".//*[@id='collapsible-91']/div/p")).getText();
		String expected_Text34 = "One or more women or economically disadvantaged women own at least 51% of all voting stock of the concern, are on the Board of Directors and have the percentage of voting stock necessary to overcome any super majority voting requirements; or women or economically disadvantaged women comprise the majority of voting directors through actual numbers or, where permitted by state law, through weighted voting.\n\nNOTE: Men or other entities may be involved in the management of the concern and may be stockholders, partners or limited liability members of the concern. However, no males or other entity may exercise actual control or have the power to control the concern.";
		assertEquals(actual_Text34, expected_Text34);
		mydriver.findElement(By.xpath(".//*[@id='answers[91][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_91_comment']")).sendKeys("Testing");
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Net Worth questions,Verify, select No for both and
		// continue.
		// 1st question.
		String actual_Text35 = mydriver
				.findElement(By.xpath(".//*[@id='answers_demonstrate_less_than_750k']/div/fieldset/legend")).getText();
		String expected_Text35 = "The economically disadvantaged woman upon whom eligibility is based has read the SBA's regulations defining economic disadvantage and can demonstrate that her personal net worth is less than $750,000, excluding her ownership interest in the concern and her equity interest in her primary personal residence";
		assertEquals(actual_Text35, expected_Text35);
		// 1st question meaning.
		String actual_Text36 = mydriver.findElement(By.xpath(".//*[@id='collapsible-92']/div/p")).getText();
		String expected_Text36 = "In order to be considered economically disadvantaged, the woman's personal net worth must be less than $750,000, excluding her ownership interest in the concern and her equity interest in her primary personal residence. (13 C.F.R. Part 127)\n\nThe personal financial condition of the woman claiming economic disadvantage, including her personal income for the past three years (including bonuses, and the value of company stock given in lieu of cash), her personal net worth and the fair market value of all of her assets, whether encumbered or not, will be considered in determining whether she is economically disadvantaged.\n\nSBA may consider a spouse's financial situation in determining a woman's access to credit and capital. When married, an individual claiming economic disadvantage must submit separate financial information for her spouse, unless the individual and the spouse are legally separated.";
		assertEquals(actual_Text36, expected_Text36);
		mydriver.findElement(By.xpath(".//*[@id='answers[92][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text37 = mydriver
				.findElement(By.xpath(".//*[@id='answers_woman_financial_condition']/div/fieldset/legend")).getText();
		String expected_Text37 = "The personal financial condition of the woman claiming economic disadvantage, including her personal income for the past three years (including bonuses, and the value of company stock given in lieu of cash), her personal net worth and the fair market value of all of her assets, whether encumbered or not, evidences that she is economically disadvantaged.";
		assertEquals(actual_Text37, expected_Text37);
		// 2nd question meaning.
		String actual_Text38 = mydriver.findElement(By.xpath(".//*[@id='collapsible-93']/div/p")).getText();
		String expected_Text38 = "Please provide the last three (3) Federal Tax Returns Form 1040 (pages 1 & 2 only), schedules, W-2s, and completed IRS FORM 4506-T for all women owners and their spouses.\n\nThe personal financial condition of the woman claiming economic disadvantage, including her personal income for the past three years (including bonuses, and the value of company stock given in lieu of cash), her personal net worth and the fair market value of all of her assets, whether encumbered or not, will be considered in determining whether she is economically disadvantaged";
		assertEquals(actual_Text38, expected_Text38);
		mydriver.findElement(By.xpath(".//*[@id='answers[93][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Adjusted Gross Income questions,verify, select No for both
		// and continue.
		// 1st question.
		String actual_Text39 = mydriver
				.findElement(By.xpath(".//*[@id='answers_agi_3_year_less_than_350k']/div/fieldset/legend")).getText();
		String expected_Text39 = "The adjusted gross income of the woman claiming economic disadvantage averaged over the three years preceding the certification does not exceed $350,000";
		assertEquals(actual_Text39, expected_Text39);
		// 1st question meaning.
		String actual_Text40 = mydriver.findElement(By.xpath(".//*[@id='collapsible-94']/div/p")).getText();
		String expected_Text40 = "When considering a woman's personal income, if the adjusted gross yearly income averaged over the three years preceding the certification exceeds $350,000, SBA will presume that she is not economically disadvantaged.\n\nNOTE: The presumption may be rebutted (determined invalid) by a showing that this income level was unusual and not likely to occur in the future; that losses commensurate with and directly related to the earnings were suffered, or by evidence that the income is not indicative of lack of economic disadvantage.";
		assertEquals(actual_Text40, expected_Text40);
		mydriver.findElement(By.xpath(".//*[@id='answers[94][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text41 = mydriver
				.findElement(By.xpath(".//*[@id='answers_agi_3_year_exceeds_but_uncommon']/div/fieldset/legend"))
				.getText();
		String expected_Text41 = "The adjusted gross income of the woman claiming economic disadvantage averaged over the three years preceding the certification exceeds $350,000; however, the woman can show that this income level was unusual and not likely to occur in the future, that losses commensurate with and directly related to the earnings were suffered, or that the income is not indicative of lack of economic disadvantage";
		assertEquals(actual_Text41, expected_Text41);
		// 2nd question meaning.
		String actual_Text42 = mydriver.findElement(By.xpath(".//*[@id='collapsible-95']/div/p")).getText();
		String expected_Text42 = "When considering a woman's personal income, if the adjusted gross yearly income averaged over the three years preceding the certification exceeds $350,000, SBA will presume that she is not economically disadvantaged.\n\nNOTE: The presumption may be rebutted (determined invalid) by a showing that this income level was unusual and not likely to occur in the future; that losses commensurate with and directly related to the earnings were suffered, or by evidence that the income is not indicative of lack of economic disadvantage.";
		assertEquals(actual_Text42, expected_Text42);
		mydriver.findElement(By.xpath(".//*[@id='answers[95][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Fair Market Value questions,verify select No and continue.
		String actual_Text43 = mydriver
				.findElement(By.xpath(".//*[@id='answers_woman_assets_less_than_6m']/div/fieldset/legend")).getText();
		String expected_Text43 = "The fair market value of all the assets (including her primary residence and the value of the business concern but excluding funds invested in an Individual Retirement Account or other official retirement account that are unavailable until retirement age without a significant penalty) of the woman claiming economic disadvantage does not exceed $6 million.";
		assertEquals(actual_Text43, expected_Text43);
		// Verify meaning for the Fair Market Value questions.
		String actual_Text44 = mydriver.findElement(By.xpath(".//*[@id='collapsible-96']/div/p")).getText();
		String expected_Text44 = "A woman will generally not be considered economically disadvantaged if the fair market value of all her assets (including her primary residence and the value of the business concern) exceeds $6 million.\n\nFunds invested in an Individual Retirement Account (IRA) or other official retirement account that are unavailable until retirement age without a significant penalty will not be considered in determining a woman's net worth. In order to properly assess whether funds invested in a retirement account may be excluded from a woman's net worth, she must provide information about the terms and restrictions of the account to SBA and certify that the retirement account is legitimate.";
		assertEquals(actual_Text44, expected_Text44);
		mydriver.findElement(By.xpath(".//*[@id='answers[96][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Assets questions,verify, select and No for both and
		// continue.
		// 1st question.
		String actual_Text45 = mydriver
				.findElement(By.xpath(".//*[@id='answers_woman_has_not_transferred_assets']/div/fieldset/legend"))
				.getText();
		String expected_Text45 = "The woman claiming economic disadvantage has not transferred any assets within two years of the date of the certification.";
		assertEquals(actual_Text45, expected_Text45);
		// 1st question meaning.
		String actual_Text46 = mydriver.findElement(By.xpath(".//*[@id='collapsible-97']/div/p[1]")).getText();
		String expected_Text46 = "Assets that a woman claiming economic disadvantage transferred within two years of the date of the concern's certification will be attributed to the woman claiming economic disadvantage if the assets were transferred to an immediate family member, or to a trust that has as a beneficiary an immediate family member. The transferred assets within the two-year period will not be attributed to the woman if the transfer was:";
		assertEquals(actual_Text46, expected_Text46);
		mydriver.findElement(By.xpath(".//*[@id='answers[97][value]']/label[2]")).click();
		// 2nd question.
		String actual_Text47 = mydriver
				.findElement(By.xpath(".//*[@id='answers_woman_asset_transfer_excusable']/div/fieldset/legend"))
				.getText();
		String expected_Text47 = "The woman claiming economic disadvantage has transferred assets within two years of the date of the certification. However, the transferred assets were: (1) to or on behalf of an immediate family member for that individual's education, medical expenses, or some other form of essential support; or (2) to an immediate family member in recognition of a special occasion, such as a birthday, graduation, anniversary, or retirement.";
		assertEquals(actual_Text47, expected_Text47);
		// 2nd question meaning.
		String actual_Text48 = mydriver.findElement(By.xpath(".//*[@id='collapsible-98']/div/p[1]")).getText();
		String expected_Text48 = "Assets that a woman claiming economic disadvantage transferred within two years of the date of the concern's certification will be attributed to the woman claiming economic disadvantage if the assets were transferred to an immediate family member, or to a trust that has as a beneficiary an immediate family member. The transferred assets within the two-year period will not be attributed to the woman if the transfer was:";
		assertEquals(actual_Text48, expected_Text48);
		mydriver.findElement(By.xpath(".//*[@id='answers[98][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("EDWOSB application questions have been answered");

		// Validate that user successfully navigated to the Financial Data
		// section.
		String actual_Text49 = mydriver.findElement(By.cssSelector("h2.usa-heading.ques-private")).getText();
		String expected_Text49 = "Financial Data";
		assertEquals(actual_Text49, expected_Text49);
		// Validate the Personal Information.
		// String
		// actual_Text50=mydriver.findElement(By.xpath(".//*[@id='answers_owners']/div/fieldset/p[1]")).getText();
		// String expected_Text50="This section must be completed by each
		// individual claiming economic disadvantage in connection with the 8(a)
		// Program and/or the Women-Owned Small Business Federal Contract
		// Program. If married, the spouse must complete this section, except
		// when the individual and the spouse are legally separated. If
		// separated, provide copy of separation document.";
		// assertEquals(actual_Text50, expected_Text50);
		// Locate the 'Add New Person' button at the bottom of the personal
		// information and click on it.
		mydriver.findElement(By.id("answers_99_value_new_button")).click();
		Thread.sleep(2000);
		// Verify that the section to Create new record is been seen by user and
		// enter record2.
		String actual_Text51 = mydriver.findElement(By.className("DTE_Header_Content")).getText();
		String expected_Text51 = "Create new record";
		assertEquals(actual_Text51, expected_Text51);
		System.out.println("the page to Create and Add new Record is Present, PASS");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys("Danzel");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys("Washington");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys("187669987");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys("8765 Weems dr");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys("Manassas");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys("Virginia");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys("28776");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys("United State");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys("7024762987");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys("7023764876");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys("DWashington@mailinator.com");
		mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		// Select No for question Is anyone listed above divorced? If yes,
		// please provide separation documents.
		mydriver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();

		// Locate the Continue Button and click on it to continue.
		Thread.sleep(3000);
		assertValue("Save and continue", mydriver.findElement(By.name("commit")));
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

	}

	private void assertValue(String string, WebElement findElement) {
		// TODO Auto-generated method stub

	}

	private void assertEquals(String actual_Text1, String expected_Text1) {
		// TODO Auto-generated method stub
	}

}
