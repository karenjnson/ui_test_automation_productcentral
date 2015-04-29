package com.aol.assist.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.CommonIssuesPage;
import com.aol.assist.ui.page.DevicesWeSupportPage;
import com.aol.assist.ui.page.PlansPage;
import com.aol.assist.ui.page.WhyChooseUsPage;

public class TestAssistGetStarted extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestAssistGetStarted.class);

	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testLandingPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		checkWindow("Landing Page");
		
		PlansPage plansPage = landingPage.getStarted();
		checkWindow("Plans Page");

		plansPage.chooseOneTimeFix();
		checkWindow("OneTimeFix");
		
		DevicesWeSupportPage devicesWeSupportPage = plansPage.clickDevicesWeSupportPage();
		checkWindow("Devices We Support");
		
		CommonIssuesPage commonIssuesPage = devicesWeSupportPage.clickCommonIssuesPage();
		checkWindow("Common Issues");
		
		commonIssuesPage.clickWhyChooseUsPage();
		checkWindow("Why Choose Us");
	}
	
}
