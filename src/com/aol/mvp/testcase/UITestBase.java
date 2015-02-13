package com.aol.mvp.testcase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aol.automation.webdriver.WebDriverFactory;
import com.aol.automation.webdriver.WebDriverWrapper;
import com.aol.common.model.user.Account;
import com.aol.common.util.io.IOUtils;
import com.aol.common.util.screen.ScreenUtil;
import com.aol.mvp.ui.page.LandingPage;
import com.aol.mvp.ui.page.LandingPageFactory;
import com.applitools.eyes.Eyes;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;

public abstract class UITestBase {

	private static final Log LOG = LogFactory.getLog(UITestBase.class);

	protected static Properties mainProps = null;
	protected static Properties envProps = null;

	protected String envName;
	protected String errorScreenShotSuffix;

	private WebDriverFactory driverFactory;
	protected WebDriverWrapper driver = null;

	protected Account account = null;
	protected Eyes eyes;
	protected String productName;
	protected LandingPage landingPage;
	protected boolean mobileVersion = false;


	@BeforeSuite(alwaysRun=true)
	@Parameters({"env", "gridProviders"})
	public void beforeSuite(String env, @Optional(DEFAULT_GRID_PROVIDERS) String gridProviders) throws IOException {

		if (StringUtils.isBlank(env)) {
			LOG.warn("No env or unrecognized env specified ["+ env +"].  Defaulting env to 'qa'");
			env = "qa";
		}
		envName = env.toLowerCase();

		getConfigProperties();

		/* NOTE: gridProviders is set to DEFAULT_GRID_PROVIDERS if user doesn't specify */
		driverFactory = new WebDriverFactory(getGridProviders(gridProviders));		
	}


	private Eyes getEyes() {
		
		if(eyes == null){
			eyes = new Eyes();
	        eyes.setApiKey(mainProps.getProperty("APPLITOOLS_API_KEY"));
	        eyes.setMatchLevel(MatchLevel.LAYOUT);
		}
		
		return eyes;
	}


	@BeforeMethod(alwaysRun=true)
//	@Parameters({"os", "browserType", "browserVersion", "platform"})
//	public void beforeMethod(ITestContext testContext, Method method, String os,
//			String browserType, @Optional("")String browserVersion, @Optional("") String platform)
//	{
	@Parameters({"os", "browserType", "width", "height", "productName", "browserVersion"})
	public void beforeMethod(ITestContext testContext, Method method, String os,
			String browserType, String width, String height, String productName, @Optional("")String browserVersion)
	{
		LOG.debug("width: " + width);
		LOG.debug("Height: " + height);

		/* used by SauceLabs */
		String testName = method.getName();
		/* used by BrowserStack */
		String build = testName;

		LOG.debug("Getting webdriver instance...");
		
		driver = getDriverFactory(testContext).getRemoteWebDriver(build, testName, os, browserType, browserVersion);

		driver.openEyes(getEyes(), testName, productName+" "+testName, getRectangle(width, height));

		saveSessionId(testContext);
		
		landingPage = LandingPageFactory.getLandingPage(driver, productName);
		this.productName = productName; 
		errorScreenShotSuffix = os + "_" + browserType
				+ "_"+ browserVersion
				+ "_" + "USR" + "."
				+ mainProps.getProperty("SCREENSHOT_IMG_FORMAT");
		
		if(StringUtils.isNotBlank(testContext.getCurrentXmlTest().getParameter("mobileVersion"))){
			mobileVersion  = Boolean.valueOf(testContext.getCurrentXmlTest().getParameter("mobileVersion"));
		}
	}


	private WebDriverFactory getDriverFactory(ITestContext testContext) {
		if(driverFactory == null){
			driverFactory = new WebDriverFactory(getGridProviders(testContext.getCurrentXmlTest().getParameter("gridProviders")));
		}
		
		return driverFactory;
	}


	@AfterMethod(alwaysRun=true)
	protected void afterMethod() {
		try {
			// End visual testing. Validate visual correctness.
			eyes.close();
		} finally {
			if (driver != null) {
				LOG.info(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " Clearing driver");
				driver.quit();
			}
			if(eyes != null){
				eyes.abortIfNotClosed();
			}
		}
	}

	private void getConfigProperties() throws IOException {
		mainProps = IOUtils.readPropertiesFile(MAIN_PROPERTIES_FILE);
		String delimiter = mainProps.getProperty("DELIMITER", "/");
		String envPropertyFile = CONFIG_DIR + delimiter + envName + delimiter + ENV_PROPERTIES_FILE;
		envProps = IOUtils.readPropertiesFile(envPropertyFile);
	}


	private RectangleSize getRectangle(String width, String height) {
		LOG.debug("getRectangle() width: " + width);
		LOG.debug("getRectangle() Height: " + height);

		int w = Integer.parseInt(width);
		int h = Integer.parseInt(height);
		return new RectangleSize(w, h);
	}


	/**
	 * Stores session ID in testContext so we can retrieve it later
	 * to build URL to get resources from third party browser hosts
	 * like SauceLabs
	 *
	 * @param testContext - provided by TestNG
	 */
	protected void saveSessionId(ITestContext testContext) {
		SessionId sessionId = driver.getSessionId();
		testContext.setAttribute("sessionId", sessionId);
	}


	/**
	 * Please provide a unique fileName
	 *
	 * example format: testname_os_browser_user
	 * example: TestPurchase_WINDOWS_chrome_hubbartc1
	 */
	protected void captureScreenshotOnFailure(String fileName) {
		try {
			ScreenUtil.captureImageOnFailure(driver, mainProps.getProperty("SCREENSHOT_ERROR_IMG_DIR"), fileName);
		} catch (Exception e) {
			LOG.error("Unable to capture screenshot: " + mainProps.getProperty("SCREENSHOT_ERROR_IMG_DIR") + "/" + fileName);
			e.printStackTrace();
		}
	}

	protected String getErrorScreenshotName(String username) {
		return errorScreenShotSuffix.replace("USR", username);
	}

	/**
	 * NOTE: Do NOT try to get the grid url until we know the os, browser, version
	 * One provider may not have our requested platform available and we should
	 * be able to shift to another provider dynamically
	 */
	private String getGridProviders(String userSpecifiedGridProviders) {
		/* allows us to override which provider(s) to use at runtime. ie: via Jenkins config */
		String mavenParamName = "runlocal";

		LOG.debug("Checking for maven supplied parameter '"+ mavenParamName +"'");
		String gridProviders = System.getProperty(mavenParamName, userSpecifiedGridProviders);
		LOG.debug("Setting 'gridProviders': " + gridProviders);
		return gridProviders;
	}

	protected void sleep(long ms, String why_do_you_need_a_hard_sleep) {
		try {
			LOG.warn("Hard sleep("+ ms+ "): " + why_do_you_need_a_hard_sleep);
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			LOG.error("Sleep interupted: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static final String CONFIG_DIR = "resources/config";
	private static final String DEFAULT_GRID_PROVIDERS = "griddick";
	private static final String MAIN_PROPERTIES_FILE = CONFIG_DIR + "/main.properties";
	private static final String ENV_PROPERTIES_FILE = "env.properties";
}