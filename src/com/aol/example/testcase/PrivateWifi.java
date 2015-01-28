package com.aol.example.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.example.model.Product;
import com.aol.example.ui.page.CheckoutPage;
import com.aol.example.ui.page.PrivateWifiPage;

public class PrivateWifi extends UITestBase{

	
 	private static final Log LOG = LogFactory.getLog(PrivateWifi.class);
    private PrivateWifiPage privateWifi= null;
	
    
    
	@Parameters({"accountType", "username", "password"})
	@Test
	public void loadPrivateWifiPageAndSignIn(String accountType, String username, String password)
	{
		account = new Account(accountType, username, password, null);
		privateWifi = new PrivateWifiPage(driver);
		
		privateWifi.openPrivateWifi(envProps);
		
		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.downloadPrivateWifi();
	
		try {
			privateWifi.signIn(account.getUsername(),account.getPassword());
			eyes.checkWindow("Private Wifi Download Page");
			
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Parameters({"accountType", "username_max", "password"})
	@Test 
	public void checkMaxLimitReached(String accountType, String username_max, String password)
	{
		account = new Account(accountType, username_max, password, null);
		privateWifi = new PrivateWifiPage(driver);
		
		privateWifi.openPrivateWifi(envProps);
		eyes.checkWindow("Private Wifi Landing Page");
		privateWifi.downloadPrivateWifi();
	
		try {
			privateWifi.signIn(account.getUsername(),account.getPassword());
			eyes.checkWindow("Private Wifi Download Error Page For Max Downlaod");
			
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Parameters({"accountType", "username", "password"})
	@Test
	public void privateWifiDownloadTest(String accountType, String username, String password)
	{
		account = new Account(accountType, username, password, null);
		privateWifi = new PrivateWifiPage(driver);
		
		privateWifi.openPrivateWifi(envProps);
		
		privateWifi.downloadPrivateWifi();
	
		try {
			privateWifi.signIn(account.getUsername(),account.getPassword());
			Assert.assertTrue(privateWifi.validateDownloadText());
			
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Parameters({"accountType", "username_max", "password"})
	@Test 
	public void privateWifiMaxReached(String accountType, String username_max, String password)
	{
		account = new Account(accountType, username_max, password, null);
		privateWifi = new PrivateWifiPage(driver);
		
		privateWifi.openPrivateWifi(envProps);
		
		privateWifi.downloadPrivateWifi();
	
		try {
			privateWifi.signIn(account.getUsername(),account.getPassword());
			Assert.assertTrue(privateWifi.validateMaxErrorText());
			
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
