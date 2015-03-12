package com.aol.assist.ui.page;

import org.openqa.selenium.By;

import com.aol.automation.webdriver.WebDriverWrapper;

public abstract class Infopageabstract extends FullPage implements InfoPage {

	
	public Infopageabstract(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InfoPage selectWhyChoose() {
		driver.findElement(By.id("whyus"));
		return new Whyuspage(driver);
	}

	@Override
	public InfoPage selectFaqs() {
		driver.findElement(By.id("faqs"));
		return new Whyuspage(driver);
	}
	@Override
	public InfoPage selectTos() {
		driver.findElement(By.id("tos"));
		return new Whyuspage(driver);
	}
 
}
