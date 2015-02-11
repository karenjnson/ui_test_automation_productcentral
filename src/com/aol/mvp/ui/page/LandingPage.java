package com.aol.mvp.ui.page;

import java.util.Properties;

public interface LandingPage {
	public void openLandingPage(Properties envProps);
	public LoginPage getStarted();
	public DownloadPage downloadNow();
	public boolean checkHeader();
}
