package com.aol.example.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.Account;
import com.aol.example.ui.page.HyattLegalPage;

public class HyattLegal extends UITestBase{
	
	private static final Log LOG = LogFactory.getLog(HyattLegal.class);
    private HyattLegalPage hyattLegal= null;
	
    
	@Parameters({"accountType", "username", "password"})
	@Test
	public void photoBucketNotValidErrorPage(String accountType, String username, String password)
	{
		account = new Account(accountType, username, password, null);
		hyattLegal = new HyattLegalPage(driver);
		
		hyattLegal.openHyattLegal(envProps);
		
		eyes.checkWindow("PhotoBucket Landing Page");
		hyattLegal.getStarted();
		
		try {
			hyattLegal.signIn(account.getUsername(),account.getPassword());
			eyes.checkWindow("PhotoBucket Error Page");
			Thread.sleep(1000);
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("Hyatt Legal" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	

}
