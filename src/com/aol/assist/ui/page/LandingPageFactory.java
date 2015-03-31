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
		
		if ("Assist by AOL".equalsIgnoreCase(productName)) return new AssistLandingPage(driver);


    	defaultErr(productName);
		return null;
	}

	private static void defaultErr(String productName) {
		LOG.error("Unable to set landing page for product: '" + productName + "'");
	}
}
