package com.aol.assist.testcase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aol.common.model.user.ASQ;
//import com.aol.common.model.user.Account;
import com.aol.common.util.io.IOUtils;
import com.aol.common.util.screen.ScreenUtil;
import com.aol.assist.ui.page.LandingPage;
import com.aol.assist.ui.page.LandingPageFactory;
import com.aol.automation.webdriver.WebDriverFactory;
import com.aol.automation.webdriver.WebDriverWrapper;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.Eyes;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;

import dataProviders.DataProviderGenerator;

public abstract class UITestBase {

	private static final Log LOG = LogFactory.getLog(UITestBase.class);

	protected static Properties mainProps = null;
	protected static Properties envProps = null;

	protected String envName;
	protected String errorScreenShotSuffix;

	private WebDriverFactory driverFactory;
	protected WebDriverWrapper driver = null;

	//protected Account account = null;
	protected Eyes eyes;
	protected String productName;
	protected LandingPage landingPage;
	protected boolean mobileVersion = false;
	protected String deviceType = null;
	private boolean isEyesDisabled = false;

	public String prodUrl;
	public String ctaCssSelector;
	private Object inputTestFile;

	private static String batchName;
	protected static BatchInfo batch;


	@BeforeSuite(alwaysRun=true)
	@Parameters({"env", "gridProviders", "batchName", "prodUrl", "inputTestFile","productName", "ctaCssSelector"})
	public void beforeSuite(String env, @Optional(DEFAULT_GRID_PROVIDERS) String gridProviders, @Optional String batchName, @Optional String prodUrl, @Optional String inputTestFile, @Optional String productName, @Optional String ctaCssSelector) throws IOException {

		LOG.debug("@BEFORESUITE *******************************");

		if (StringUtils.isBlank(env)) {
			LOG.warn("No env or unrecognized env specified ["+ env +"].  Defaulting env to 'qa'");
			env = "qa";
		}
		envName = env.toLowerCase();

		getConfigProperties();
		isEyesDisabled = shouldDisableEyes();
		LOG.debug("Is Eyes() disabled: " + isEyesDisabled);

		/* NOTE: gridProviders is set to DEFAULT_GRID_PROVIDERS if user doesn't specify */
		driverFactory = new WebDriverFactory(getGridProviders(gridProviders));
		
		batch = new BatchInfo(batchName);
		LOG.debug("Setting the batch name used by applitools "+batchName);
		
		this.prodUrl = prodUrl;
		LOG.debug("Setting the prodUrl:  "+prodUrl);
		
		this.inputTestFile = inputTestFile;
		LOG.debug("Setting the input Test file: "+inputTestFile);
		
		this.productName = productName;
		LOG.debug("Setting the product name: "+productName);
		
		this.ctaCssSelector = ctaCssSelector;
		LOG.debug("Setting the CTA Selector: "+ctaCssSelector);
	}
	
	@Parameters({"prodUrl"})
	public String getProdUrl() {
		LOG.debug("The product URL: "+prodUrl);
		return prodUrl;
	}
	
	@Parameters({"inputTestFile"})
	public String getInputTestFile() {
		LOG.debug("The input test file: "+inputTestFile);
		return (String) inputTestFile;
	}
	
	@Parameters({"productName"})
	public String getProductName() {
		LOG.debug("The product Name: "+productName);
		return productName;

	}
	
	@Parameters({"ctaCssSelector"})
	public String getCtaCssSelector() {
		LOG.debug("The CTA Selector: "+ctaCssSelector);
		return ctaCssSelector;

	}

	
	/* OBTAIN THE TEST DATA
	 * 	 The data is retrieved from a flat file
	 *   Each line the flat file represents a test case
	*/
	//@DataProvider
	//@Parameters({"inputFileName"})
	// Get the Data(Test Cases)
	//public Object[][] theDataProviderData (ITestContext testContext, Method method, String inputFileName) throws Exception {
		
		//Object[][] theTestData = new Object[][] { {"windows","chrome","1440","900","privatewifi","44.0","win8" },{"windows","firefox","1440","900","privatewifi","44.0","win8" } };
		
		/*
		DataProviderGenerator getData = new DataProviderGenerator(inputFileName);
		Object[][] theTestData = getData.testData();
		*/
		
		//return theTestData;
	//}



	//@BeforeMethod(alwaysRun=true)
	@Parameters({"os", "browserType", "width", "height", "productName", "browserVersion", "osType"})
	public void beforeMethod(ITestContext testContext, Method method, String os,
			String browserType, @Optional("") String width, @Optional("") String height, String productName, @Optional("")String browserVersion, @Optional("") String osType )
	{
		LOG.debug("@BEFOREMETHOD *******************************");
		LOG.debug("width: " + width);
		LOG.debug("Height: " + height);

		/* used by SauceLabs */
		String testName = method.getName();
		/* used by BrowserStack */
		String build = testName;
		width = ((width.equals(""))?"1920":width);
		height = ((height.equals(""))?"1080":height);

		LOG.debug("Getting webdriver instance...");

		//if(platform.equals("")&&device.equals(""))
			driver = getDriverFactory(testContext).getRemoteWebDriver(build, testName, os, browserType, browserVersion);
			
			if(driver==null)
				LOG.info("Driver is null !!");
		//else
			//driver = getDriverFactory(testContext).getRemoteWebDriver(build, testName, os, browserType, browserVersion,platform,device);
		
		//if(width.equals("") && height.equals(""))
			//driver.openEyes(getEyes(), testName, productName+" "+testName+"_"+os+"_"+browserType+"_"+platform+"_"+device);
		/*else
		{*/
			String resolution =width+"x"+height;
			LOG.info("Default resolution should be considered : "+resolution);	
			//driver.openEyes(getEyes(), testName, productName+" "+testName+"_"+os+"_"+browserType+"_"+browserVersion+"_"+resolution, getRectangle(width, height));
			String applicationName = productName+"-"+osType;
			driver.openEyes(getEyes(), testName,applicationName+" "+browserType+"_"+os+"_"+resolution, getRectangle(width, height));
		//}

		saveSessionId(testContext);

		landingPage = LandingPageFactory.getLandingPage(driver, productName);
		
		this.productName = productName;
		errorScreenShotSuffix = os + "_" + browserType
				+ "_"+ browserVersion
				+ "_" + "USR" + "."
				+ mainProps.getProperty("SCREENSHOT_IMG_FORMAT");
		


		if(StringUtils.isNotBlank(testContext.getCurrentXmlTest().getParameter("mobileVersion"))){
			mobileVersion  = Boolean.valueOf(testContext.getCurrentXmlTest().getParameter("mobileVersion"));
			deviceType = testContext.getCurrentXmlTest().getParameter("deviceType");
		}
	}
	

	

	@AfterMethod(alwaysRun=true)
	protected void afterMethod() {
		LOG.debug("@AFTERMETHOD *******************************");

		try {
			// End visual testing. Validate visual correctness.
			if(eyes != null){
				//eyes.close();
			}
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


	protected RectangleSize getRectangle(String width, String height) {
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
	private String getGridProviders(String testNgXMLSpecifiedGridProviders) {
		/* allows us to override which provider(s) to use at runtime. ie: via Jenkins config */
		String runTimeArg = "gridProviders";

		LOG.debug("Checking for runtime arg '"+ runTimeArg +"'");
		String gridProviders = System.getProperty(runTimeArg, testNgXMLSpecifiedGridProviders);
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

//	protected void createAccount(String accountType, String username, String password, String asqQuestion, String asqAnswer, String countryCode) {
//		ASQ accountSecurityQAndA = new ASQ(asqQuestion, asqAnswer);
//		account = new Account(accountType, username, password, accountSecurityQAndA, countryCode);
//	}

	protected void checkWindow(String suffix) {
		if (!isEyesDisabled) {
			eyes.setSaveFailedTests(false);
			eyes.checkWindow(productName+" "+suffix);
		}
	}

	private String getApplitoolsApiKey() {
//		return StringUtils.isNotBlank(System.getProperty("applitoolsApiKey"))?System.getProperty("applitoolsApiKey"):mainProps.getProperty("APPLITOOLS_API_KEY");
		return getPropertyFromSystemOrFile("applitoolsApiKey", "APPLITOOLS_API_KEY");
	}

	private String getPropertyFromSystemOrFile(String runTimeArg, String configPropName) {
		String value;

		LOG.debug("Checking for runTime supplied parameter '"+ runTimeArg +"'");
		if (StringUtils.isNotBlank(System.getProperty(runTimeArg))) {
			value = System.getProperty(runTimeArg);
			LOG.debug("Found runTime arg '"+ runTimeArg +"': " + value);
			return value;
		}

		value = mainProps.getProperty(configPropName);
		LOG.debug("Using '"+ configPropName +"' property from main config file: " + value);
		return value;
	}

	protected Eyes getEyes() {

		if(eyes == null) {
			eyes = new Eyes();
	        eyes.setApiKey(getApplitoolsApiKey());
	        eyes.setMatchLevel(MatchLevel.CONTENT);
	        eyes.setForceFullPageScreenshot(true);
	        eyes.setMatchTimeout(7);
	        eyes.setBatch(batch);
		}
		return eyes;
	}

	private boolean shouldDisableEyes() {
		LOG.debug("Checking if Eyes() should be disabled...");
//		return StringUtils.isNotBlank(System.getProperty("disableEyes"))?BooleanUtils.toBoolean(System.getProperty("disableEyes")):BooleanUtils.toBoolean(mainProps.getProperty("DISABLE_EYES"));
		return BooleanUtils.toBoolean(getPropertyFromSystemOrFile("disableEyes", "DISABLE_EYES"));
	}
	
	public boolean shouldDisableMbox() {
		LOG.debug("Checking if Mbox should be disabled...");
//		return StringUtils.isNotBlank(System.getProperty("disableEyes"))?BooleanUtils.toBoolean(System.getProperty("disableEyes")):BooleanUtils.toBoolean(mainProps.getProperty("DISABLE_EYES"));
		return BooleanUtils.toBoolean(getPropertyFromSystemOrFile("disableMbox", "DISABLE_MBOX"));
	}


	protected WebDriverFactory getDriverFactory(ITestContext testContext) {
		if(driverFactory == null){
			driverFactory = new WebDriverFactory(getGridProviders(testContext.getCurrentXmlTest().getParameter("gridProviders")));
		}
		return driverFactory;
	}
	
	protected static void scrollToBottom(WebDriverWrapper driver) {
        LOG.info("Scrolling to bottom !");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


	private static final String CONFIG_DIR = "resources/config";
	private static final String DEFAULT_GRID_PROVIDERS = "griddick";
	private static final String MAIN_PROPERTIES_FILE = CONFIG_DIR + "/main.properties";
	private static final String ENV_PROPERTIES_FILE = "env.properties";
}