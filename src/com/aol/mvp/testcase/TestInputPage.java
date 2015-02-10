package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.InputPage;

public class TestInputPage extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestInputPage.class);
	

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testInputPage(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);

		eyes.checkWindow(productName+" Landing Page");
		InputPage inputPage = landingPage.getStarted();

		try {
			inputPage.provideInput(account);
			eyes.checkWindow(productName);
			Thread.sleep(1000);
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure(productName+" " + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Error occured in submitting input page: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//TODO: Need to add a test for error in getting input page
}
