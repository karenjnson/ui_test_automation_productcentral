package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.ErrorIneligiblePage;
import com.aol.mvp.ui.page.LoginPage;

public class TestInputPage extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestInputPage.class);
	

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testValidUser(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);

		eyes.checkWindow(productName+" Landing Page");
		LoginPage loginPage = landingPage.getStarted();

		try {
			loginPage.login(account);
			eyes.checkWindow(productName);
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
			@Optional("us") String countryCode) {
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			ErrorIneligiblePage ineligiblePage = loginPage.loginWithIneligibleUserCredentials(account);
			Assert.assertTrue(ineligiblePage.validateIneligibleText());

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
			@Optional("us") String countryCode) {
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			boolean actual = loginPage.login(account);
			Assert.assertTrue(actual);
			Assert.assertTrue(loginPage.validateInvalidUserText());

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
			@Optional("us") String countryCode) {
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			boolean actual = loginPage.login(account);
			Assert.assertTrue(actual);
			Assert.assertTrue(loginPage.validateInvalidPasswordText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));
			LOG.error("Error occured", e);
			Assert.fail("Unable to login: " + e.getMessage());
		}
	}

}
