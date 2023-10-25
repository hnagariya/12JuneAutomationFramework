package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.proxydriver.ProxyDriver;

public class AccountPage extends Page {

	private static final String PAGE_URL = "opencart/index.php?route=account/account";

	public AccountPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
		// TODO Auto-generated constructor stub
	}

	private static By myAccountText=By.cssSelector("#content>h2:first-of-type");
	private static By acccountInfoUpdateSuccessMessage=By.cssSelector("div.alert-success");
	private static By editYourInfoLink= By.cssSelector("//a[text()='Edit your account information']");


	public String getMyAccountText() {
		return ((ProxyDriver)wd).getText(myAccountText);
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

}
