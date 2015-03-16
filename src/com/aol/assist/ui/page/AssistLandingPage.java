package com.aol.assist.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class AssistLandingPage extends FullPage implements LandingPage, LoginPage, DownloadPage, ErrorIneligiblePage{

	public static final Log LOG = LogFactory.getLog(AssistLandingPage.class);

	public AssistLandingPage(WebDriverWrapper driver) {
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
	public void openLandingPage(Properties envProps) throws Exception {

		String url = envProps.getProperty("ASSIST.LANDINGPAGE.URL");
		
	    LOG.debug("Getting page: " + url);
		driver.get(url);
		
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
	}


	@Override
	public PlansPage getStarted() throws Exception {
		driver.findElement(CALL_TO_ACTION_LOC).click();
		return new PlansPage(driver);		
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

	
	private static final By CALL_TO_ACTION_LOC = By.id("hero-content-cta");
	private static final By HEADER_TEXT_LOC = By.id("hero-content-title");
	
	private static final String EXPECTED_HEADER_TEXT = "We make technology simple.";
	
}
