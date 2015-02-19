package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class OnePointPage extends FullPage implements LandingPage, LoginPage, DownloadPage, ErrorIneligiblePage{

	public static final Log LOG = LogFactory.getLog(OnePointPage.class);

	public OnePointPage(WebDriverWrapper driver) {
		super(driver);
	}


	public String getDownloadNowText()
	{
		return driver.findElement(By.xpath("html/body/form/div[3]/table/tr/td/span")).getText();
	}


	public String getMaxDownloadErrorText()
	{
		return driver.findElement(By.xpath("html/body/section/div/div/p")).getText();
	}


	@Override
	public void download() {
		driver.findElement(By.linkText("Download Now")).click();
	}


	@Override
	public boolean login(Account account) {
		return signIn(account);
	}


	@Override
	public void openLandingPage(Properties envProps) {

		String url = envProps.getProperty("MVP.LANDING.URL.ONEPOINT");

		LOG.debug("Getting page: " + url);
		driver.get(url);
	}


	@Override
	public LoginPage getStarted() {
		driver.findElement(By.linkText("Download Now")).click();
		return this;		
	}


	@Override
	public boolean checkHeader() {
		return false;
	}

	@Override
	public DownloadPage downloadNow() {
		return this;
	}

	@Override
	public ErrorIneligiblePage loginWithIneligibleUserCredentials(Account account) {
		signIn(account);
		return this;
	}
	
	public LoginPage clickDeviceTypeLink(String deviceType){		
		driver.findElement(By.className(deviceType)).click();
		return this;
	}

}
