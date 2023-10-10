package com.naveenautomation.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
		events=new WebdriverEvents();
		e_driver.register(events);

		//Assigning back the value to Web driver
		wd = e_driver;

		/* Loading the Page */
		wd.get(URL);

		wd.manage().window().maximize();

		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void tearDown() {
		wd.quit();
	}

}
