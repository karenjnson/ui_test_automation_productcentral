package com.aol.assist.ui.page;

import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public class WhyChooseUsPage extends FullPage {

	public WhyChooseUsPage(WebDriverWrapper driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
		checkHeaderText(HEADER_TEXT_LOC, EXPECTED_HEADER_TEXT);
	}
	
	private static final String EXPECTED_HEADER_TEXT = "Why Choose Us";
	private static final By HEADER_TEXT_LOC = By.id("whyus");

}
