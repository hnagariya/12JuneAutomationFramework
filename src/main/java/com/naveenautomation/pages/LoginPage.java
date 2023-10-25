package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.proxydriver.ProxyDriver;

public class LoginPage extends Page {

	public String PAGE_URL = "/opencart/index.php?route=account/login";

	public LoginPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static By emailInput = By.id("input-email");
	private static By pwdInput = By.id("input-password");
	private static By loginBtn = By.cssSelector("input[type='Submit']");
	private static By alertBanner = By.cssSelector(".alert-danger");

	private void enterEmail(String email) {
		((ProxyDriver) wd).sendKeys(emailInput, email);
	}

	private void enterPassword(String password) {
		((ProxyDriver) wd).sendKeys(pwdInput, password);
	}

	public AccountPage SubmitLogin(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		((ProxyDriver) wd).click(loginBtn);
		return new AccountPage(wd, true);
	}

	public String getAlertText() {
		return ((ProxyDriver) wd).getText(alertBanner);
	}

	@Override
	protected void isLoaded() {
		if (!urlContains(wd.getCurrentUrl())) {
			throw new Error();
		}
	}

	@Override
	protected String getPageUrl() {
		return getDomain() + PAGE_URL;
	}

	@Override
	public LoginPage get() {
		return (LoginPage) super.get();
	}

}
