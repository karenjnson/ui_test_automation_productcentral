package com.aol.assist.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.PlansPage;
import com.aol.assist.ui.page.InfoPage;

public class TestAssistUI extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestAssistUI.class);

	@Parameters({ "accountType", "username", "password", "asqQuestion",
			"asqAnswer", "countryCode" })
	@Test
	public void testAssistUI(String accountType, String username,
			String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer,
			@Optional("us") String countryCode) throws Exception {

		createAccount(accountType, username, password, asqQuestion, asqAnswer,
				countryCode);
		
		landingPage.openLandingPage(envProps);
		checkWindow("Landing Page");

		PlansPage plansPage = landingPage.getStarted();
		checkWindow("Initial Plans Page");

		plansPage.chooseOneTimeFix();
		checkWindow("One Time Fix Tab Selected");

		InfoPage infoPage = plansPage.readFAQ();
		checkWindow("FAQ via footer link");

		infoPage.selectWhyChooseUs();
		checkWindow("Why Us");

		infoPage.selectTOS();
		checkWindow("Terms Of Service");
		
		infoPage.selectFAQs();
		checkWindow("FAQ");
	}
}
