package com.aol.mvp.testcase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.common.model.user.ASQ;
import com.aol.common.model.user.Account;
import com.aol.mvp.ui.page.FullPage;

public class TestLandingPage extends UITestBase{

	private static final Log LOG = LogFactory.getLog(TestLandingPage.class);
	

	@Parameters({"accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode"})
	@Test
	public void testLandingPage(String accountType, String username, String password,
			@Optional("Question") String asqQuestion, @Optional("1234") String asqAnswer,
			@Optional("us") String countryCode)
	{
		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);

		landingPage.openLandingPage(envProps);

		eyes.checkWindow(productName+" Landing Page");
		
		// page may have Mobile version
		if(mobileVersion){
			driver.findElement(By.linkText("Mobile Version")).click();
		}
	}	
}
