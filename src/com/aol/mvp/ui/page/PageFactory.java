package com.aol.mvp.ui.page;

import com.aol.automation.webdriver.WebDriverWrapper;

public class PageFactory {

	public static LandingPage getLandingPage(WebDriverWrapper driver, String productName){
		LandingPage landingPage = null;
		if(productName.equals("HyattLegal")){
			landingPage = new HyattLegalPage(driver); 
		}else  if(productName.equals("PhotoBucketBasic")){
			landingPage = new PhotoBucketBasicPage(driver);
		}
		return landingPage;
	}
	
	public static DownloadPage getDownloadPage(WebDriverWrapper driver, String productName){
		DownloadPage downloadPage = null;
		//TODO: Add logic to instantiate specific Impl based on productName
		return downloadPage;
	}
	
	public static ActivationPage getActivationPage(WebDriverWrapper driver, String productName){
		ActivationPage activationPage = null;
		
		//TODO: Add logic to instantiate specific Impl based on productName
		return activationPage;
	}

}
