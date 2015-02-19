package com.aol.mvp.testcase;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.mvp.ui.page.DownloadPage;
import com.aol.mvp.ui.page.LoginPage;

public class TestDownloadProduct extends UITestBase {
	private static final Log LOG = LogFactory.getLog(TestDownloadProduct.class);

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void downloadTest(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		
		LoginPage loginPage = null;
		// page may have Mobile version
		if (mobileVersion) {
			landingPage.clickMobileVersion();
			// click device type link
			loginPage = landingPage.clickDeviceTypeLink(deviceType);			
		}else{
			loginPage = landingPage.getStarted();			
		}
		
				
		

		try {
			loginPage.login(account);
			DownloadPage downloadPage = loginPage.downloadNow();	
			//TODO: need to get actual value to assert
			Assert.assertEquals("????", downloadPage.getDownloadNowText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode", "expectedErrorText"})
	@Test
	public void maxDownloadErrorTest(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode, @Optional("You have reached the maximum number of activations") String expectedErrorText)
	{
		createAccount(accountType, username, password, asqQuestion, asqAnswer, countryCode);
		landingPage.openLandingPage(envProps);
		LoginPage loginPage = landingPage.getStarted();

		try {
			loginPage.login(account);
			DownloadPage downloadPage = loginPage.downloadNow();
			Assert.assertTrue(StringUtils.startsWith(downloadPage.getMaxDownloadErrorText(), 
					expectedErrorText));
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}	
}
