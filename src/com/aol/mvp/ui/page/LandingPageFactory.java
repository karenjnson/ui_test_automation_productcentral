package com.aol.mvp.ui.page;

import com.aol.automation.webdriver.WebDriverWrapper;

public class LandingPageFactory {

	public static LandingPage getLandingPage(WebDriverWrapper driver, String productName){
		LandingPage landingPage = null;
		if(productName.equals("HyattLegal")){
			landingPage = new HyattLegalPage(driver);
		}else  if(productName.equals("PhotoBucketBasic")|| productName.equals("PhotoBucketPlus")){
			landingPage = new PhotoBucketBasicPage(driver);
		}
		return landingPage;
	}
}
