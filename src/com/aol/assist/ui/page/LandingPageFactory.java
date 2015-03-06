package com.aol.assist.ui.page;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aol.automation.webdriver.WebDriverWrapper;

public class LandingPageFactory {

	private static final Log LOG = LogFactory.getLog(LandingPageFactory.class);

	public static LandingPage getLandingPage(WebDriverWrapper driver, String productName){

		if (StringUtils.isBlank(productName)) {
			defaultErr(productName);
			return null;
		}
		
		if ("AssistGetStarted".equalsIgnoreCase(productName)) return new AssistGetStartedPage(driver);


/*		if ("HyattLegal".equalsIgnoreCase(productName)) return new HyattLegalPage(driver);
		if ("PhotoBucketBasic".equalsIgnoreCase(productName)) return new PhotoBucketBasicPage(driver);
		if ("PhotoBucketPlus".equalsIgnoreCase(productName)) return new PhotoBucketPlusPage(driver);
		if ("OnePoint".equalsIgnoreCase(productName)) return new OnePointPage(driver);
		if ("privateWifi".equalsIgnoreCase(productName) || "PrivateWifiMobile".equalsIgnoreCase(productName)) return new PrivateWifiPage(driver);
	*/	
		defaultErr(productName);
		return null;
	}

	private static void defaultErr(String productName) {
		LOG.error("Unable to set landing page for product: " + productName);
	}
}