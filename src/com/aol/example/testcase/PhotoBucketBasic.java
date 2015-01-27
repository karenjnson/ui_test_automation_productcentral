package com.aol.example.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.Account;
import com.aol.example.ui.page.PhotoBucketBasicPage;
import com.aol.example.ui.page.PrivateWifiPage;

public class PhotoBucketBasic extends UITestBase{
	
	private static final Log LOG = LogFactory.getLog(PhotoBucketBasic.class);
    private PhotoBucketBasicPage photoBucket= null;
	
    
	@Parameters({"accountType", "username", "password"})
	@Test
	public void photoBucketNotValidErrorPage(String accountType, String username, String password)
	{
		account = new Account(accountType, username, password, null);
		photoBucket = new PhotoBucketBasicPage(driver);
		
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
	
	
	

	

}
