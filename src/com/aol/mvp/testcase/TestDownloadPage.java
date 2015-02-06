package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.DownloadPage;
import com.aol.mvp.ui.page.PrivateWifiPage;

public class TestDownloadPage extends UITestBase {
	private static final Log LOG = LogFactory.getLog(TestDownloadPage.class);
	
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void privateWifiDownloadTest(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);

		DownloadPage downloadPage = landingPage.downloadNow();

		try {
			downloadPage.signIn(account);
			Assert.assertTrue(downloadPage.validateDownloadText());

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
