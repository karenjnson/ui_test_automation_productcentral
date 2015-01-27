package com.aol.example.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public class PhotoBucketBasicPage extends FullPage{
	public static final Log LOG = LogFactory.getLog(PhotoBucketBasicPage.class);
	
	public PhotoBucketBasicPage(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public PhotoBucketBasicPage openPhotoBucketPage(Properties envProps) {

		String url = envProps.getProperty("STARTCHECKOUT.URL.PHOTOBUCKET");
		
		LOG.debug("Getting page: " + url);
		driver.get(url);

		return this;
	}
	
	public void activatePhotoBucket() 
	{
		
		driver.findElement(By.linkText("Activate Now")).click();
	}

}
