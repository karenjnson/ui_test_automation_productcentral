package com.aol.assist.testcase;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.ErrorIneligiblePage;
import com.aol.assist.ui.page.LoginPage;

public class TestRegistrationProduct extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestRegistrationProduct.class);
	

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testValidUser(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode) throws Exception
	{
		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		checkWindow("Landing Page");
		LoginPage loginPage = landingPage.getStarted();

		try {
			loginPage.login(account);
			checkWindow("Landing Page after login");
			Thread.sleep(1000);
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure(productName+" " + getErrorScreenshotName(account.getUsername()));
			LOG.error("Error occured", e);
			Assert.fail("Error occured in submitting input page: " + e.getMessage());
		}
	}

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testIneligibleUser(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode) throws Exception {
		
		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			ErrorIneligiblePage ineligiblePage = loginPage.loginWithIneligibleUserCredentials(account);
			Assert.assertTrue(StringUtils.contains(ineligiblePage.getIneligibleText(), "it appears that your username is not eligible"));

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));
			LOG.error("Error occured", e);
			Assert.fail("Unable to login: " + e.getMessage());
		}
	}
	
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testInvalidUser(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode) throws Exception {

		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			boolean actual = loginPage.login(account);
			Assert.assertEquals(true, actual);
			Assert.assertEquals("Error: Oops,due to an unexpected error we are unable to sign you in at this time. Please try again later.", loginPage.getInvalidUserText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));
			LOG.error("Error occured", e);
			Assert.fail("Unable to login: " + e.getMessage());
		}
	}
	
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testInvalidPassword(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode) throws Exception {

		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			boolean actual = loginPage.login(account);
			Assert.assertEquals(true, actual);
			Assert.assertEquals("Error: Incorrect Username or Password.", loginPage.getInvalidPasswordText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));
			LOG.error("Error occured", e);
			Assert.fail("Unable to login: " + e.getMessage());
		}
	}
}
