SETUP
Also checkout ui_test_automation_base from GitHub - https://github.com/aol/ui_test_automation_base

There is a pom.xml so you should be able to get all required dependencies with 'mvn eclipse:clean eclipse:eclipse'

The files for controlling tests are:

	./resources/config/main.properties (use to set you Applitools Eyes API key)
	./testng/TestAssistGetStarted.xml   (controls which test suite to run, which browsers to use, which groups of tests to include/exclude, etc.)


Sample test suite is ui_test_automation_assist/src/com/aol/assist/testcase/TestAssistGetStartedPage.java


To stand up your own local instance of grid you'll need to:

1. Download latest selenium-server-standalone jar file 
2. From two separate terminal windows run these two commands:

	java -jar selenium-server-standalone-latest.jar -role hub

	java -jar selenium-server-standalone-latest.jar -role node  -hub http://localhost:4444/grid/register


RUN EXAMPLE TEST
1. mvn eclipse:clean eclipse:eclipse
2. mvn clean install
3. via eclipse:
	i. right+click testng/TestAssistGetStarted.xml
	ii. Run As > TestNG Suite


LOGGING
configure logging in ./resources/log4j.properties
