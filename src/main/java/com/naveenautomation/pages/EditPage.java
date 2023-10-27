package com.naveenautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.proxydriver.ProxyDriver;

public class EditPage extends Page {

	public EditPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private static By firstNameInput = By.id("input-firstname");
	private static By lastNameInput = By.id("input-lastname");
	private static By emailInput = By.id("input-email");
	private static By telePhoneInput = By.id("input-telephone");
	private static By submitBtn = By.cssSelector("input[value='Continue']");

	public void enterFName(String name) {
		((ProxyDriver) wd).sendKeys(firstNameInput, name);
	}

	public void enterLName(String lname) {
		((ProxyDriver) wd).sendKeys(lastNameInput, lname);
	}

	public void enterEmail(String email) {
		((ProxyDriver) wd).sendKeys(emailInput, email);
	}

	public void enterTelephone(String number) {
		((ProxyDriver) wd).sendKeys(telePhoneInput, number);
	}

	public AccountPage clickSubmitBtn() {
		((ProxyDriver) wd).click(submitBtn);
		return null;
	}

	@Override
	protected void isLoaded() {
		// TODO Auto-generated method stub

	}

}
