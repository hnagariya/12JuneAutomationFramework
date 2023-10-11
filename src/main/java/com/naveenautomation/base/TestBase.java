package com.naveenautomation.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.naveenautomation.Utils.WebdriverEvents;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver wd;

	private final String DEFAULT_BROWSER = "CHROME";
	private final String URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";

	public static Logger logger;
	public WebdriverEvents events;
	public EventFiringWebDriver e_driver;

	@BeforeClass
	public void loggerSteup() {
		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
	}

	public void intialisation() {
		switch (DEFAULT_BROWSER) {
		case "CHROME":
			wd = WebDriverManager.chromedriver().create();
			break;
		case "EDGE":
			wd = WebDriverManager.edgedriver().create();
			break;
		case "FIREFOX":
			wd = WebDriverManager.firefoxdriver().create();
			break;
		default:
			throw new IllegalArgumentException();
		}

		// Wrap the instance
		e_driver = new EventFiringWebDriver(wd);

		// Events which need to be captured
		events = new WebdriverEvents();
		e_driver.register(events);

		// Assigning back the value to Web driver
		wd = e_driver;

		wd.get(URL);

		wd.manage().window().maximize();

		wd.manage().deleteAllCookies();

		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void tearDown() {
		wd.quit();
	}

	public static void failedTestScreenShot(String testMethodName) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		File screenShotFfile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(screenShotFfile,
					new File("./FailedTestCasesScreenShot\\" + "_" + testMethodName + "_" + timeStamp + ".jpg"));
		} catch (IOException e) {

			System.out.println("................................The IO Exception is ...... " + e);
			e.printStackTrace();
		}

	}
}
