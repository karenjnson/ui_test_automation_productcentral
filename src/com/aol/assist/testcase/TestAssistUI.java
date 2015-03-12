package com.aol.assist.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.AssistPlansPage;
import com.aol.assist.ui.page.InfoPage;

public class TestAssistUI extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestAssistUI.class);

	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testAssistUI(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) {
		
		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		checkWindow("Landing Page");

		AssistPlansPage plansPage = landingPage.getStarted();
		
		checkWindow("Plans Default");
		
		plansPage.chooseOneTimeFix();
		checkWindow("OneTimeFix");
		InfoPage infopage=plansPage.readFAQ();
		checkWindow("FAQ");
		
		infopage.selectWhyChoose();
		checkWindow("WhyUs");
		
		infopage.selectTos();
		checkWindow("TOS");
	}
}
