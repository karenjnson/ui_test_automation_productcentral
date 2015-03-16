package com.aol.assist.ui.page;


import com.aol.automation.webdriver.WebDriverWrapper;

public class WhyUsPage extends InfoPageAbstract {

	public WhyUsPage(WebDriverWrapper driver) throws Exception {
		super(driver);
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
	}
	
	private static final String EXPECTED_HEADER_TEXT = "We care as much as you do.";
}
