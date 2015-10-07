package com.aol.assist.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.CommonIssuesPage;
import com.aol.assist.ui.page.DevicesWeSupportPage;
import com.aol.assist.ui.page.PlansPage;
import com.aol.assist.ui.page.WhyChooseUsPage;
import com.applitools.eyes.Eyes;
import com.applitools.eyes.StdoutLogHandler;

public class TestApplitools extends UITestBase {
	
	/*
	@Before
	public void setup() throws Exception {
		// Set Batch Name for Applitools
		eyes.setBatch(batch);
	}
	*/
	
	

	private static final Log LOG = LogFactory.getLog(TestApplitools.class);

	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode", "prodUrl" })
	@Test
	public void uiNonResponsiveTest(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode, @Optional("") String prodUrl) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		
		LOG.debug("RUNNING TEST ******************");
	    driver.get(prodUrl);

        // Visual validation point #1
          eyes.checkWindow("Main Page");
          //eyes.close();
		
          /*   
          landingPage.openLandingPage(envProps);
		//scrollToBottom(driver);
		eyes.setLogHandler(new StdoutLogHandler(true));
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
		*/
	}
	
	
	
}
