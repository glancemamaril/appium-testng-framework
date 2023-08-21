package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ReadProperty {

	private static Properties prop;
	
	public static String getPropertyValue(String key) {
		
		FileReader reader = null;
		
		try {
			reader = new FileReader(".//TestData//Configuration.properties");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	      
		prop = new Properties();
	    
	    try {
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	      
	    return prop.getProperty(key);  
	}
	
	public String getPlatform() {
		String platform = prop.getProperty("platformName");
		System.out.println(platform);
		return platform;
	}
	
	public String getPlatformVersion() {
		String platformVersion = prop.getProperty("platformVersion");
		return platformVersion;
	}
	
	public String getAppPackage() {
		String appPackage = prop.getProperty("appPackage");
		return appPackage;
	}
	
	public String getAppActivity() {
		String appActivity = prop.getProperty("appActivity");
		return appActivity;
	}
	
	public String getAutomationName() {
		String automation = prop.getProperty("automationName");
		return automation;
	}
	
	public String getDeviceName() {
		String device = prop.getProperty("deviceName");
		return device;
	}

	public String getReportName() {
		String report = prop.getProperty("reportName");
		return report;
	}
	
	public String getDocumentTitle() {
		String report = prop.getProperty("documentTitle");
		return report;
	}
	
	public static void setPropertyValue(String key, String value) {
		PropertiesConfiguration properties;
		try {
			properties = new PropertiesConfiguration(".//TestData//Configuration.properties");
			properties.setProperty(key, value);
			properties.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
