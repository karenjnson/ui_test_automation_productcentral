package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.model.Product;
import com.aol.mvp.ui.page.CheckoutPage;

public class TestExampleLifestore extends UITestBase {

	private static final Log LOG = LogFactory.getLog(TestExampleLifestore.class);

	private CheckoutPage checkout = null;
	private Product product = null;

	@BeforeTest
	@Parameters ({"clientId", "sku", "friendlyName"})
	private void setProductDetails(String clientId, String sku, String friendlyName) {
		product = new Product(clientId, sku, friendlyName);
	}

	@BeforeMethod
	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode", "ncid"})
	public void loadCheckoutPageAndSignIn(String accountType, String username, String password,
			String asqQuestion, String asqAnswer, String countryCode, String ncid)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		checkout = new CheckoutPage(driver);
		checkout.startCheckout(envProps, product, ncid);

        eyes.checkWindow("Start Checkout Page");

		try {
			checkout.signIn(account);
		} catch (Exception e) {
			//TODO: handle screenshots with a listener
			captureScreenshotOnFailure("signIn_" + getErrorScreenshotName(account.getUsername()));

			Assert.fail("Unable to login: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void aTest() {
		captureScreenshotOnFailure("aTest_" + getErrorScreenshotName(account.getUsername()));
		Assert.assertEquals(1, 1);
	}

}
