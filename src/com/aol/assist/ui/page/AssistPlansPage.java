package com.aol.assist.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class AssistPlansPage extends FullPage implements LandingPage, LoginPage, DownloadPage, ErrorIneligiblePage{

	public static final Log LOG = LogFactory.getLog(AssistPlansPage.class);

	public AssistPlansPage(WebDriverWrapper driver) {
		super(driver);
	}


	public String getDownloadNowText()
	{
		return driver.findElement(By.xpath("html/body/div/section[1]/div/div/div[1]/h1")).getText();
	}

	public LoginPage clickDeviceTypeLink(String deviceType){		
		driver.findElement(By.className(deviceType)).click();
		return this;
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

		String url = envProps.getProperty("ASSIST.PLANS.URL");

		LOG.debug("Getting page: " + url);
		driver.get(url);
	}


	@Override
	public AssistPlansPage getStarted() {
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


	@Override
	public void chooseOneTimeFix() {
		driver.findElement(ONE_TIME_FIX).click();
		
		
	}
	
	private static final By ONE_TIME_FIX = By.linkText("One-Time Fix");

	public FaqPage readFAQ() {
		driver.findElement(FAQLINK).click();
		return new FaqPage(driver);
		
	}
	private static final By FAQLINK = By.linkText("FAQs");
}
