package com.aol.assist.testcase;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aol.assist.ui.page.CommonIssuesPage;
import com.aol.assist.ui.page.DevicesWeSupportPage;
import com.aol.assist.ui.page.LandingPageFactory;
import com.aol.assist.ui.page.PlansPage;
import com.aol.assist.ui.page.WhyChooseUsPage;
import com.applitools.eyes.Eyes;
import com.applitools.eyes.StdoutLogHandler;

import dataProviders.DataProviderGenerator;

public class ResponsiveUITest extends UITestBase {
	
	
    /* OBTAIN THE TEST DATA
     *       The data is retrieved from a flat file
     *   Each line the flat file represents a test case
    */
    @DataProvider
    // Get the Data(Test Cases)
    public Object[][] theDataProviderData () throws Exception {

        	DataProviderGenerator getData = new DataProviderGenerator(getInputTestFile() );
            Object[][] theTestData = getData.testData();
        	
            return theTestData;
    }	
	

	private static final Log LOG = LogFactory.getLog(ResponsiveUITest.class);
	

	//@Parameters({ "accountType", "username", "password", "asqQuestion", "asqAnswer", "countryCode" })
	@Test(dataProvider = "theDataProviderData")
	public void uiNonResponsiveTest(ITestContext testContext, Method method, String os, String browserType, String browserVersion, String width, String height, String osType) throws Exception {
			String theProdUrl = getProdUrl();
			productName = getProductName();
			
			/* used by SauceLabs */
			String testName = method.getName();
			/* used by BrowserStack */
			String build = testName;
			width = ((width.equals(""))?"1920":width);
			height = ((height.equals(""))?"1080":height);

			LOG.debug("Getting webdriver instance...");

			driver = getDriverFactory(testContext).getRemoteWebDriver(build, testName, os, browserType, browserVersion);
				
			if(driver==null)    LOG.info("Driver is null !!");
			
			LOG.info("All the input parameters:  "+os+" "+browserType+" "+width+" "+height);
	
			String resolution =width+"x"+height;
			String applicationName = getProductName()+"-"+osType;
			driver.openEyes(getEyes(), testName,applicationName+" "+browserType+"_"+os+"_"+resolution, getRectangle(width, height));
		
		
			LOG.debug("TESTING: "+productName+" ***********************");
			if (shouldDisableMbox() ) {
				theProdUrl = getProdUrl() + "?mBoxDisable=1";
				LOG.debug("TURNING ON MBOX for TESTING *****************");
			} 
			LOG.debug("PRODURL: "+theProdUrl);
			driver.get(theProdUrl);

        // Visual validation point #1
          eyes.checkWindow("Main Page");
          
          driver.findElementByCssSelector(getCtaCssSelector()).click();
          eyes.checkWindow("Checkout Page");
          
          eyes.close();
     
	}
	
	
	
}
