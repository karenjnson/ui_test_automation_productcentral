package com.aol.example.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.example.model.Product;

public class CheckoutPage extends FullPage {

	public static final Log LOG = LogFactory.getLog(CheckoutPage.class);

	public CheckoutPage(WebDriverWrapper driver) {
		super(driver);
		FullPage.signInOutLocator = SIGN_IN_OUT_LOC;
	}

	/* valid query string for computer checkup checkout:
	 * "?categoryId=pc-tools-and-storage&brandName=aol-computer-checkup-2" */
	public CheckoutPage startCheckout(Properties envProps, Product product, String ncid) {

		String url = envProps.getProperty("STARTCHECKOUT.URL") + "?client="
				+ product.getClientId() +"&skus=" + product.getSku() + "&ncid=" + ncid;

		LOG.debug("Getting page: " + url);
		driver.get(url);

		return this;
	}

	public String getProductName() {
		return driver.findElement(PRODUCT_NAME_LOC).getText();
	}

//	public OrderConfirmationPage purchase() {
//		WebElement el = driver.findElement(PLACE_ORDER_LOC);
//		LOG.debug("Clicking button to purchase deal.");
//		el.click();
//		LOG.debug("After call to click purchase deal button.");
//		return new OrderConfirmationPage(driver);
//	}

	private static By PLACE_ORDER_LOC = By.id("purchaseDeal");
	private static By PRODUCT_NAME_LOC = By.xpath("//*[@id='co_productname']/h2");
	private static By SIGN_IN_OUT_LOC = By.id("checkout-sign-in");
}
