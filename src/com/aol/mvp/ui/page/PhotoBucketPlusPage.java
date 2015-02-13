package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aol.automation.webdriver.WebDriverWrapper;

public class PhotoBucketPlusPage extends PhotoBucketPage {
	public static final Log LOG = LogFactory.getLog(PhotoBucketPlusPage.class);

	public PhotoBucketPlusPage(WebDriverWrapper driver) {
		super(driver);
	}

	@Override
	public void openLandingPage(Properties envProps) {

		String url = envProps.getProperty("MVP.LANDING.URL.PHOTOBUCKET.PLUS");

		LOG.debug("Getting page: " + url);
		driver.get(url);
		
	}
}
