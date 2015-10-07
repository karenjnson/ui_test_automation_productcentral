package com.aol.assist.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.PlansPage;
import com.applitools.eyes.BatchInfo;

public class TestAssistAllPages extends UITestBase {
	BatchInfo batch = new BatchInfo("Assist_UI_Pages"); 

	private static final Log LOG = LogFactory.getLog(TestAssistAllPages.class);
	

	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testLandingPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		LOG.info("Testing Landing Page");
		eyes.setBatch(batch);
		landingPage.openLandingPage(envProps);
		scrollToBottom(driver);
		checkWindow("Landing Page");
	}
	
	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testPlansPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
	
		PlansPage plansPage = landingPage.getStarted();
		scrollToBottom(driver);
		eyes.setBatch(batch);
		checkWindow("Plans Page");

		plansPage.chooseOneTimeFix();
		eyes.setBatch(batch);
		checkWindow("OneTimeFix");
		eyes.close();
		
	}
	
	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testDevicesPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		scrollToBottom(driver);
		
		PlansPage plansPage = landingPage.getStarted();
		plansPage.clickDevicesWeSupportPage();
		eyes.setBatch(batch);
		checkWindow("Devices We Support");
		eyes.close();

	}
	
	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testCommonIssuesPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		landingPage.openLandingPage(envProps);
		scrollToBottom(driver);
		
		PlansPage plansPage = landingPage.getStarted();	
		plansPage.clickCommonIssuesPage();
		eyes.setBatch(batch);
		checkWindow("Common Issues");
		eyes.close();

	}
	
	@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test
	public void testWhyChooseUsPage(String accountType, String username, String password, @Optional("Question") String asqQuestion,
			@Optional("1234") String asqAnswer, @Optional("us") String countryCode) throws Exception {
		
		//createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		scrollToBottom(driver);
		
		PlansPage plansPage = landingPage.getStarted();	
		plansPage.clickWhyChooseUsPage();
		eyes.setBatch(batch);
		checkWindow("Why Choose Us");
		eyes.close();
	}
	
}
