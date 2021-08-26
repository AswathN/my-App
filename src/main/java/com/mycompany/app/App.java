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
			System.out.println("<<<<Test Starts>>>>");
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			//driver = new ChromeDriver();
			String Sauce_Username = System.getenv("SAUCE_USERNAME");
			//String Sauce_Password = System.getenv("Sauce_Password");
			String Sauce_Accesskey = System.getenv("SAUCE_ACCESS_KEY");
			//String Sauce_Tunnel = "Purposefinancial_tunnel"/*System.getenv("Tunnel")*/;
			String Sauce_url = "https://" + Sauce_Username + ":" + Sauce_Accesskey;
			MutableCapabilities capabilities = new MutableCapabilities();
			capabilities.setCapability("goog:chromeOptions", new ChromeOptions());
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability("platformName", "Windows 10");
			capabilities.setCapability("browserVersion", "latest");
			capabilities.setCapability("browserName", "Chrome");
			driver = new RemoteWebDriver(new URL(Sauce_url), capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Launching Web Page");
			driver.get("https://www.google.com/");
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
