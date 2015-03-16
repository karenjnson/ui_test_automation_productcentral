package com.aol.assist.ui.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class PlansPage extends FullPage implements LoginPage, DownloadPage, ErrorIneligiblePage{

	private static final Log LOG = LogFactory.getLog(PlansPage.class);

	public PlansPage(WebDriverWrapper driver) throws Exception {
		super(driver);
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
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
	public DownloadPage downloadNow() {
		return this;
	}

	@Override
	public ErrorIneligiblePage loginWithIneligibleUserCredentials(Account account) {
		signIn(account);
		return this;
	}

	public PlansPage chooseOneTimeFix() {
		driver.findElement(ONE_TIME_FIX).click();
		return this;
	}

	public InfoPage readFAQ() throws Exception {
		driver.findElement(FAQ_LOC).click();
		return new FaqPage(driver);
		
	}
	
	private static final By FAQ_LOC = By.linkText("FAQs");
	private static final By ONE_TIME_FIX = By.linkText("One-Time Fix");
    private static final By HEADER_TEXT_LOC = By.cssSelector("h1.text-light");
    
    private static final String EXPECTED_HEADER_TEXT = "Pick the plan that's right for you";
}
