package com.aol.assist.ui.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public abstract class InfoPageAbstract extends FullPage implements InfoPage {

	private static final Log LOG = LogFactory.getLog(InfoPageAbstract.class);
	
	public InfoPageAbstract(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InfoPage selectWhyChoose() {
		LOG.debug("Switching to 'Why Choose Us' page...");
		driver.findElement(By.id("whyus")).click();
		return new WhyUsPage(driver);
	}

	@Override
	public InfoPage selectFaqs() {
		LOG.debug("Switching to 'FAQs' page...");
		driver.findElement(By.id("faqs")).click();
		return new FaqPage(driver);
	}
	@Override
	public InfoPage selectTos() {
		LOG.debug("Switching to 'Terms of Service' page...");
		driver.findElement(By.id("tos")).click();
		return new TosPage(driver);
	}
 
}
