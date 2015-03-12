package com.aol.assist.ui.page;

import java.util.Properties;

public interface LandingPage {
	public void openLandingPage(Properties envProps);
	public AssistPlansPage getStarted();
	public boolean checkHeader();
	public void clickMobileVersion();
	public LoginPage clickDeviceTypeLink(String deviceType);
}
