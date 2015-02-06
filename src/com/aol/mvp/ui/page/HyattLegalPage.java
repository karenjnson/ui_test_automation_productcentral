package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;

public class HyattLegalPage extends FullPage implements LandingPage, InputPage{

	public HyattLegalPage(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public static final Log LOG = LogFactory.getLog(HyattLegalPage.class);


	public void openLandingPage(Properties envProps) {

		String url = envProps.getProperty("MVP.LANDING.URL.HYATTLEGAL");

		LOG.debug("Getting page: " + url);
		driver.get(url);
	}


	public InputPage getStarted()
	{
		driver.findElement(By.linkText("Get Started")).click();
		return this;
	}
	
	
	public boolean checkHeader()
	{
		return driver.findElement(By.xpath("html/body/section/div/div/h1")).isDisplayed();
	}


	@Override
	public boolean provideInput(Account account) {
		return signIn(account);	
	}


	@Override
	public DownloadPage downloadNow() {
		return null;
	}
	
}
