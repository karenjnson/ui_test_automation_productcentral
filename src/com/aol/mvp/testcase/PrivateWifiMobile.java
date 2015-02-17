package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.PrivateWifiPage;

public class PrivateWifiMobile extends UITestBase{


	private static final Log LOG = LogFactory.getLog(PrivateWifi.class);

	//TODO remove all the copy/pasted code

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void loadPrivateWifiMobilePageAndSignIn(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);
		driver.findElement(By.linkText("Mobile Version")).click();
		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.getStarted();

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
	public void mobileCheckMaxLimitReached(String accountType, String usernameMax, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, usernameMax, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);
		driver.findElement(By.linkText("Mobile Version")).click();
		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.getStarted();

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
	public void privateWifiMobileDownload(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);
		driver.findElement(By.linkText("Mobile Version")).click();
		privateWifi.getStarted();

		try {
			privateWifi.signIn(account);
			Assert.assertEquals(true, privateWifi.validateDownloadText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Parameters({"accountType", "usernameMax", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void mobileCheckMaxLimit(String accountType, String usernameMax, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, usernameMax, password, accountSecurityQAndA, countryCode);
		PrivateWifiPage privateWifi = new PrivateWifiPage(driver);

		privateWifi.openLandingPage(envProps);
		driver.findElement(By.linkText("Mobile Version")).click();
		privateWifi.getStarted();

		try {
			privateWifi.signIn(account);
			Assert.assertEquals(true, privateWifi.validateMaxErrorText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
