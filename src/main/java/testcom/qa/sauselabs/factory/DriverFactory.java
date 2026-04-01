package testcom.qa.sauselabs.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import testcom.qa.sauselabs.exceptions.BrowserException;
import testcom.qa.sauselabs.exceptions.FrameworkExceptions;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	

	/**
	 * This method is used to init the driver on the basis of given browser name
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name : " + browserName);
		ChainTestListener.log("browser name: " + browserName);
		optionsManager = new OptionsManager(prop);


		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on selenium grid
				initRemoteDriver("chrome");
			} else {
				// run on local
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;
		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on selenium grid
				initRemoteDriver("edge");
			} else {
				// run on local
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;

		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on selenium grid
				initRemoteDriver("firefox");
			} else {
				// run on local
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("plz pass the valid browser name..." + browserName);
			throw new BrowserException("===INVALID BROWSER===");
		}

		getDriver().get(prop.getProperty("url"));// login page url
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	// run it on remote grid
	private void initRemoteDriver(String browserName) {
		switch (browserName) {
		case "chrome":
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			break;

		case "edge":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			break;

		case "firefox":
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			break;

		default:
			System.out.println("This browser is not supported on Selenium Grid " + browserName);
			throw new BrowserException("====== INVALID BROWSER =====");

		}

	}

	/**
	 * getDriver: get the local thready copy of the driver
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties getProperties() {
		String envName = System.getProperty("env");
		prop = new Properties();
		FileInputStream ip = null;

		try {

			if (envName == null) {
				System.out.println("No environment provided hence running on QA environment");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}

			else {
				System.out.println("Evironment Seleted: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "preprod":
					ip = new FileInputStream("./src/test/resources/config/preprod.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;

				default:
					throw new FrameworkExceptions("==== INVALID ENVIRONMENT NAME ====");
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
