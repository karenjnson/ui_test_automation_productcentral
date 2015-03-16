package com.aol.assist.ui.page;


import com.aol.automation.webdriver.WebDriverWrapper;

public class FaqPage extends InfoPageAbstract {

	public FaqPage(WebDriverWrapper driver) throws Exception {
		super(driver);
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
	}

	private static final String EXPECTED_HEADER_TEXT = "FAQs";
}
