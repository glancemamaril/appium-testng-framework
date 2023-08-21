package mobile.Calculator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import mobile.android.pages.Calculator;

public class TrialTestCase extends BaseClass {
	
	@SuppressWarnings("rawtypes")
	AppiumDriver mobileDriver;
	Calculator calculator;
	
	private void initialize() {
		mobileDriver = initializeDriver();
		calculator = new Calculator(mobileDriver);
		ExtentTest test = extent.createTest("SampleTest","Sample dsescription");
		test.log(Status.INFO,"Test One started");
	}
	
	@Test
	public void sampleTest() throws InterruptedException {
		initialize();
		String expression = "1+2=";
		calculator.parseExpression(expression);
		Assert.assertTrue(calculator.verifyResult(), "Result not the same!");
		Thread.sleep(3000);
	}
	
}
