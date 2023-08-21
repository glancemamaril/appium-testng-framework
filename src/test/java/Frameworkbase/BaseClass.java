package Frameworkbase;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.ExtentReportsHelper;

public class BaseClass extends ExtentReportsHelper {

	@SuppressWarnings("rawtypes")
	public AndroidDriver mobileDriver;
	public String appiumServer = "127.0.0.1";
	public int appiumPort = 4723;
	URL appiumURL = null;

	@SuppressWarnings("rawtypes")
	public AppiumDriver getMobileDriver() {
		//System.out.println(mobileDriver);
		return mobileDriver;
	}

	@SuppressWarnings("rawtypes")
	public AppiumDriver initializeDriver()
	{
		try {
			/*Appium URL setup is dependent on the current Appium version
			 * for versions 1.2 and below, such as Appium Desktop, "/wd/hub" should be added at the end
			 * for versions 2.0 onwards, that string is no longer necessary
			 * Note that Appium can still automate mobile apps regardless of Appium version
			 * currently still placed here, in case user is currently using an older version of Appium*/
			appiumURL = new URL("http://"+appiumServer+":"+appiumPort+"/wd/hub"); //for Appium 1.2 and lower
			//appiumURL = new URL("http://"+appiumServer+":"+appiumPort); //for Appium 2.0 onwards
			this.mobileDriver = new AndroidDriver(appiumURL,setAppCapabilitiesAndroid());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mobileDriver;

	}
	@Parameters({"platformName", "platformVersion", "appPackage", "appActivity","automationName","deviceName"})//These parameters can be configured in testng_Agile.xml
	//@Parameters({"categoryXML", "browser", "url", "td"})//These parameters can be configured in testng_Agile.xml
	@BeforeClass
	public DesiredCapabilities setAppCapabilitiesAndroid() {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "8.0");
		cap.setCapability("appPackage", "com.android.calculator2");
		cap.setCapability("appActivity", "com.android.calculator2.Calculator");
		cap.setCapability("automationName", "UIAutomator2");
		return cap;
	}
	
	public DesiredCapabilities setAppCapabilitiesAndroidWeb() {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "8.0");
		return cap;
	}
	
	public DesiredCapabilities setAppCapabilitiesIOS() {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability("platformName", "iOS");
		cap.setCapability("platformVersion", "15.0");
		cap.setCapability("appPackage", "com.android.calculator2");
		cap.setCapability("model", "iPhone-SE \\(2020\\)");
		cap.setCapability("automationName", "XCUITest");
		return cap;
	}

	@SuppressWarnings("rawtypes")
	public String getScreenShotPath(String testCaseName,AndroidDriver mobileDriver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) mobileDriver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		mobileDriver.quit();
	}
}
