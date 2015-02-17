package com.aol.mvp.ui.page;

import com.aol.common.model.user.Account;

public interface LoginPage {
	public boolean login(Account account);
	public ErrorIneligiblePage loginWithIneligibleUserCredentials(Account account);
	public String getInvalidUserText();
	public String getInvalidPasswordText();
	
}
