package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.PrivateWifiPage;

public class PrivateWifi extends UITestBase{


	private static final Log LOG = LogFactory.getLog(PrivateWifi.class);


	//TODO: stop copy/pasting same code into every test

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void loadPrivateWifiPageAndSignIn(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);

		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.download();

		try {
			privateWifi.signIn(account);
			eyes.checkWindow("Private Wifi Download Page");

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Parameters({"accountType", "usernameMax", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void checkMaxLimitReached(String accountType, String usernameMax, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, usernameMax, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);
		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.download();

		try {
			privateWifi.signIn(account);
			eyes.checkWindow("Private Wifi Download Error Page For Max Downlaod");

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void privateWifiDownloadTest(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);

		privateWifi.download();

		try {
			privateWifi.signIn(account);
			Assert.assertTrue(privateWifi.validateDownloadText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Parameters({"accountType", "usernameMax", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void privateWifiMaxReached(String accountType, String usernameMax, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, usernameMax, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);

		privateWifi.download();

		try {
			privateWifi.signIn(account);
			Assert.assertTrue(privateWifi.validateMaxErrorText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
