package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.PhotoBucketBasicPage;

public class PhotoBucketBasic extends UITestBase{

	private static final Log LOG = LogFactory.getLog(PhotoBucketBasic.class);


	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	//@Test
	public void photoBucketNotValidErrorPage(String accountType, String username, String password,
	@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
	@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PhotoBucketBasicPage photoBucket = new PhotoBucketBasicPage(driver);

		photoBucket.openPhotoBucketPage(envProps);

		eyes.checkWindow("PhotoBucket Landing Page");
		photoBucket.activatePhotoBucket();

		try {
			photoBucket.signIn(account.getUsername(),account.getPassword());
			eyes.checkWindow("PhotoBucket Error Page");

		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("Try Again" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void photoBucketErroPageFlow(String accountType, String username, String password,
	@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
	@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
		PhotoBucketBasicPage photoBucket = new PhotoBucketBasicPage(driver);

		photoBucket.openPhotoBucketPage(envProps);

		photoBucket.activatePhotoBucket();
		try {
			photoBucket.signIn(account.getUsername(),account.getPassword());
			photoBucket.checkErrorMessage();
			Thread.sleep(1000);
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("Try Again" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
