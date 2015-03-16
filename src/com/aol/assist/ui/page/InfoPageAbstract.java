package com.aol.assist.ui.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public abstract class InfoPageAbstract extends FullPage implements InfoPage {

	private static final Log LOG = LogFactory.getLog(InfoPageAbstract.class);
	
	public InfoPageAbstract(WebDriverWrapper driver) {
		super(driver);
	}

	@Override
	public InfoPage selectWhyChooseUs() throws Exception {
		LOG.debug("Switching to 'Why Choose Us' page...");
		driver.findElement(WHY_US_TAB).click();
		return new WhyUsPage(driver);
	}

	@Override
	public InfoPage selectFAQs() throws Exception {
		LOG.debug("Switching to 'FAQs' page...");
		driver.findElement(FAQ_TAB).click();
		return new FaqPage(driver);
	}
	
	@Override
	public InfoPage selectTOS() throws Exception {
		LOG.debug("Switching to 'Terms of Service' page...");
		driver.findElement(TERMS_OF_SERVICE_TAB).click();
		return new TosPage(driver);
	}

	private static final By FAQ_TAB = By.id("faqs");
	protected static final By HEADER_TEXT_LOC = By.xpath("//*[@id='hero-content']/div[1]/h1");
	private static final By TERMS_OF_SERVICE_TAB = By.id("tos");
	private static final By WHY_US_TAB = By.id("whyus");
 
}
