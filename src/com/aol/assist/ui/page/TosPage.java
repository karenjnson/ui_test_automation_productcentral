package com.aol.assist.ui.page;

import com.aol.automation.webdriver.WebDriverWrapper;

public class TosPage extends InfoPageAbstract {

	public TosPage(WebDriverWrapper driver) throws Exception {
		super(driver);
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
	}
	
	private static final String EXPECTED_HEADER_TEXT = "Terms of Service";
}
