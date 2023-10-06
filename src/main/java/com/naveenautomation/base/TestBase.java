package com.naveenautomation.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver wd;

	private final String DEFAULT_BROWSER = "CHROME";
	private final String URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";

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

		/* Loading the Page */
		wd.get(URL);

		wd.manage().window().maximize();

		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void tearDown() {
		wd.quit();
	}

}
