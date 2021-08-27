package com.mycompany.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App {
	private static RemoteWebDriver driver;

	public static void main(String[] args) throws MalformedURLException {
		try {
			String browser = args[0];
			MutableCapabilities capabilities = new MutableCapabilities();
			MutableCapabilities sauceoptions = new MutableCapabilities();
			System.out.println("<<<<Test Starts>>>>");
			
			String Sauce_Username = System.getenv("SAUCE_USERNAME");
			System.out.println("Sauce User : " + Sauce_Username);
			String Sauce_Accesskey = System.getenv("SAUCE_ACCESS_KEY");
			System.out.println("Sauce AccessKey : " + Sauce_Accesskey);
			String Sauce_Tunnel = System.getenv("SAUCE_TUNNEL");
			sauceoptions.setCapability("tunnelIdentifier", Sauce_Tunnel);
			sauceoptions.setCapability("seleniumVersion", "3.141.59");
			sauceoptions.setCapability("idleTimeout", 120);
			sauceoptions.setCapability("capturePerformance", true);
			String Sauce_url = "https://" + Sauce_Username + ":" + Sauce_Accesskey
					+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
			switch (browser) {
			case "CHROME":
				WebDriverManager.chromedriver().setup();
				capabilities.setCapability("goog:chromeOptions", new ChromeOptions());
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//capabilities.setCapability("platformName", "Windows 10");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("browserName", "CHROME");
				capabilities.setCapability("sauce:options", sauceoptions);
				driver = new RemoteWebDriver(new URL(Sauce_url), capabilities);
				break;
			case "SAFARI":
				//capabilities.setCapability("platformName", "macOS 10.15");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("browserName", "safari");
				capabilities.setCapability("sauce:options", sauceoptions);
				driver = new RemoteWebDriver(new URL(Sauce_url), capabilities);
				break;

			default:
				break;
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Launching Web Page");
			driver.get("https://qa01-www.aeaonline.net/");
			System.out.println("Web Page Launched");
			Thread.sleep(10000);
			System.out.println("<<<<Test Ends>>>>");
			driver.close();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
