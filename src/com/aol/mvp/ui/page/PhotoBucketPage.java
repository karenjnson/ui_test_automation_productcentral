package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public abstract class PhotoBucketPage extends FullPage implements LandingPage, LoginPage, ErrorIneligiblePage {
	public static final Log LOG = LogFactory.getLog(PhotoBucketPage.class);

	public PhotoBucketPage(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	public abstract void openLandingPage(Properties envProps);
	
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

	@Override
	public ErrorIneligiblePage loginWithIneligibleUserCredentials(Account account) {
		signIn(account);
		return this;
	}
}
