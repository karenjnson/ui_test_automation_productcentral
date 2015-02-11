package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class PhotoBucketBasicPage extends FullPage implements LandingPage, LoginPage {
	public static final Log LOG = LogFactory.getLog(PhotoBucketBasicPage.class);

	public PhotoBucketBasicPage(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openLandingPage(Properties envProps) {

		String url = envProps.getProperty("MVP.LANDING.URL.PHOTOBUCKET");

		LOG.debug("Getting page: " + url);
		driver.get(url);
		
	}

	public LoginPage getStarted() {
		driver.findElement(By.linkText("Activate Now")).click();
		return this;
	}

	public boolean isErrorMessageDisplayed() {
		return driver.findElement(By.linkText("Try Again")).isDisplayed();
	}

	@Override
	public boolean checkHeader() {
		return true;
	}

	@Override
	public boolean login(Account account) {
		return signIn(account);
	}

	@Override
	public DownloadPage downloadNow() {
		return null;
	}

}
