package com.aol.mvp.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aol.automation.webdriver.WebDriverWrapper;

public class PhotoBucketBasicPage extends PhotoBucketPage {
	public static final Log LOG = LogFactory.getLog(PhotoBucketBasicPage.class);

	public PhotoBucketBasicPage(WebDriverWrapper driver) {
		super(driver);
	}

	@Override
	public void openLandingPage(Properties envProps) {

		String url = envProps.getProperty("MVP.LANDING.URL.PHOTOBUCKET");

		LOG.debug("Getting page: " + url);
		driver.get(url);
		
	}
}
