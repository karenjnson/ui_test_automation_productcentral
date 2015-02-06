package com.aol.mvp.ui.page;

import java.util.Properties;

public interface LandingPage {
	public void openLandingPage(Properties envProps);
	public InputPage getStarted();
	public DownloadPage downloadNow();
	public boolean checkHeader();
}
