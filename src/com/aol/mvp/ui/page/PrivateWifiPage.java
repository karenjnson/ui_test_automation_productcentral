package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class PrivateWifiPage extends FullPage implements LandingPage, LoginPage, DownloadPage{

	public static final Log LOG = LogFactory.getLog(PrivateWifiPage.class);

	public PrivateWifiPage(WebDriverWrapper driver) {
		super(driver);
	}


	public boolean validateDownloadText()
	{
		if(driver.findElement(By.xpath("html/body/section[1]/div/div/div[1]/div[2]/div/h1")).isDisplayed()) return true;
		return false;

	}


	public boolean validateMaxErrorText()
	{
		if(driver.findElement(By.xpath("html/body/section/div/div/h3")).isDisplayed()) return true;
		return false;

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

		String url = envProps.getProperty("MVP.LANDING.URL.PRIVATEWIFI");

		LOG.debug("Getting page: " + url);
		driver.get(url);
	}


	@Override
	public LoginPage getStarted() {
		driver.findElement(By.linkText("Get Started")).click();
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


}
