package com.aol.example.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public class PrivateWifiPage extends FullPage {

	public static final Log LOG = LogFactory.getLog(PrivateWifiPage.class);

	public PrivateWifiPage(WebDriverWrapper driver) {
		super(driver);
	}


	public PrivateWifiPage openPrivateWifi(Properties envProps) {

		String url = envProps.getProperty("STARTCHECKOUT.URL.PRIVATEWIFI");

		LOG.debug("Getting page: " + url);
		driver.get(url);

		return this;
	}


	public void downloadPrivateWifi()
	{

		driver.findElement(By.linkText("Download Now")).click();
	}

	public void clickAppleStore()
	{

		driver.findElement(By.linkText("Get Started")).click();
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


}
