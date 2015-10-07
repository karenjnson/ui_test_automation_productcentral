package com.aol.assist.ui.page;

import com.aol.common.model.account.*;

public interface LoginPage {
	public boolean login(Account account);
	public ErrorIneligiblePage loginWithIneligibleUserCredentials(Account account);
	public String getInvalidUserText();
	public String getInvalidPasswordText();
	public DownloadPage downloadNow();
}
