package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public class HyattLegalPage extends FullPage{
	
	public HyattLegalPage(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public static final Log LOG = LogFactory.getLog(HyattLegalPage.class);

	
	public HyattLegalPage openHyattLegal(Properties envProps) {

		String url = envProps.getProperty("STARTCHECKOUT.URL.HYATTLEGAL");
		
		LOG.debug("Getting page: " + url);
		driver.get(url);

		return this;
	}

	
	public void getStarted() 
	{
		
		driver.findElement(By.linkText("Get Started")).click();
	}
}
