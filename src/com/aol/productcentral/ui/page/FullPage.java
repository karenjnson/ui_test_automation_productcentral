package com.aol.assist.ui.page;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.account.*;
import com.aol.common.ui.login.UILogin;


public abstract class FullPage{

	private static final Log LOG = LogFactory.getLog(FullPage.class);

	protected WebDriverWrapper driver = null;
	protected Properties envProps = null;

	protected static By signInOutLocator = By.id("loginlink");

	protected static int LONG_WAIT = 60;


	public FullPage(WebDriverWrapper driver) {
		this.driver = driver;
	}

	public boolean checkHeaderText(By headerTextLoc, String expectedHeaderTextPartial) throws Exception {
		WebElement headerTextObj = driver.waitForElementToBeVisible(headerTextLoc);
		String headerText = headerTextObj.getText();
		LOG.debug("Header text found: '" + headerText + "'");
		
		if (!headerText.contains(expectedHeaderTextPartial)) {
			String errMsg = "This may not be the page we expected. "
					+ "Found header text: '" + headerText + "'"
					+ ", but expected: '" + expectedHeaderTextPartial + "'";
			LOG.error(errMsg);
			throw new Exception(errMsg);
		}
		return true;
	}

	/**
	 * expects an account with valid asq answer
	 * @return
	 */
//	public MyAccountsPage getMyAccountPage(Account account) {
//		driver.findElement(MY_ACCT_LOC).click();
//
//		// is this the asq page
//		if (isSecurityQuestionAsked()) {
//			answerSecurityQuestion(account);
//		}
//
//		if ("gb".equalsIgnoreCase(account.getCountryCode())) {
//			return new UkMyAccountsPage(driver);
//		}
//		return new UsMyAccountsPage(driver);
//	}

	public boolean signInViaPopup(Account account) {
		boolean result = false;
		clickSignIn();	// launch popup

		try {
			UILogin.signIn(driver, account.getAcctType(), account.getUsername(), account.getPassword());
			result = true;
		} catch (Exception e) {
			LOG.error("There was an issue signing in: " + e.getMessage());
		}
		return result;
	}

	public boolean signIn(Account account) {
		return signIn(account.getUsername(), account.getPassword());
	}

	/** call signIn(Account account) instead
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	@Deprecated
	public boolean signIn(String userName, String password) {
		try {
			driver.findElement(USERNAME_INPUT).sendKeys(userName);
			driver.findElement(PASSWORD_INPUT).sendKeys(password);
			driver.findElement(SIGNIN_BUTTON).click();
		} catch(Exception e) {
		   LOG.error("There was an issue signing in: " + e.getMessage());
		   return false;
		}
		return true;
	}



	private void answerSecurityQuestion(Account account) {
		// get security question from page
		String question = driver.findElement(SECURITY_QUESTION_LOC).getText().trim();
		LOG.debug("Page is displaying security questionn: " + question);

		// find security question/answer in account
		String answer = account.getAccountSecurityAnswer(question);

		// enter answer
		LOG.debug("Entering answer on page: " + answer);
		driver.findElement(SECURITY_ANSWER_LOC).sendKeys(answer);

		// submit
		driver.findElement(SUBMIT_SECURITY_ANSWER_LOC).click();
	}

	private void clickSignIn() {
		driver.findElement(signInOutLocator).click();
	}

	private boolean isSecurityQuestionAsked() {
		boolean result = false;
		try {
			driver.waitForElementToBePresent(SECURITY_QUESTION_DIV_LOC, LONG_WAIT);
			result = true;
		} catch (TimeoutException e) {
			LOG.debug("Didn't find Security Question.");
		}
		return result;
	}

	public void sleep(long ms, String why_do_you_need_a_hard_sleep) {
		try {
			LOG.warn("Hard sleep("+ ms+ "): " + why_do_you_need_a_hard_sleep);
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getInvalidUserText() {
		return driver.findElement(By.id("snPwdErr")).getText();
	}
	
	public String getInvalidPasswordText() {
		return driver.findElement(By.id("snPwdErr")).getText();
	}

	public String getIneligibleText() {
		return driver.findElement(By.className("small-12")).getText();
	}
	
	public void clickMobileVersion(){
		driver.findElement(By.linkText("Mobile Version")).click();
	}
	
	public DevicesWeSupportPage clickDevicesWeSupportPage() throws Exception
	{
		driver.findElement(DEVICES_LINK).click();
		return new DevicesWeSupportPage(driver);
	}
	
	public CommonIssuesPage clickCommonIssuesPage() throws Exception
	{
		driver.findElement(COMMON_ISSUES_LINK).click();
		return new CommonIssuesPage(driver);
	}
	
	public WhyUsPage clickWhyChooseUsPage() throws Exception
	{
		driver.findElement(WHY_CHOOSE_US_LINK).click();
		return new WhyUsPage(driver);
	}

	private static final By MY_ACCT_LOC = By.xpath("//*[@id='myAccount']/a");
	private static final By SECURITY_ANSWER_LOC = By.id("asqanswer");
	private static final By SECURITY_QUESTION_DIV_LOC = By.id("asq");
	private static final By SECURITY_QUESTION_LOC = By.xpath("//*[@id='asq']/form/h4");
	private static final By SUBMIT_SECURITY_ANSWER_LOC = By.id("continueID");
	private static final By USERNAME_INPUT = By.id("lgnId1");
	private static final By PASSWORD_INPUT = By.id("pwdId1");
	private static final By SIGNIN_BUTTON = By.id("submitID");
	private static final By DEVICES_LINK = By.id("banner-links-devices-we-support");
	private static final By COMMON_ISSUES_LINK = By.id("banner-links-common-issues");
	private static final By WHY_CHOOSE_US_LINK = By.id("banner-links-why-choose-us");
}
