package com.aol.mvp.ui.page;

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

		if ("HyattLegal".equalsIgnoreCase(productName)) return new HyattLegalPage(driver);
		if ("PhotoBucketBasic".equalsIgnoreCase(productName)
				|| "PhotoBucketPlus".equalsIgnoreCase(productName)) return new PhotoBucketBasicPage(driver);
		if ("privateWifi".equalsIgnoreCase(productName)) return new PrivateWifiPage(driver);

		defaultErr(productName);
		return null;
	}

	private static void defaultErr(String productName) {
		LOG.error("Unable to set landing page for product: " + productName);
	}
}
