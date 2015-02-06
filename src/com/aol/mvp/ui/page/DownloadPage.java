package com.aol.mvp.ui.page;

import com.aol.common.model.user.Account;

public interface DownloadPage {
	public void download();
	public boolean validateDownloadText();
	public boolean signIn(Account account);
}
