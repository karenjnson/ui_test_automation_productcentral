package com.aol.assist.ui.page;

import java.util.Properties;

public interface LandingPage {
	public void openLandingPage(Properties envProps) throws Exception;
	public PlansPage getStarted() throws Exception;
	public boolean checkHeader();
	public void clickMobileVersion();
	public LoginPage clickDeviceTypeLink(String deviceType);
}
