package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.base.TestBase;

public class AccountPage extends TestBase {

	public AccountPage() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(css = "#content>h2:first-of-type")
	WebElement myAccountText;

	@FindBy(css = "div.alert-success")
	WebElement acccountInfoUpdateSuccessMessage;

	// Success: Your account has been successfully updated.

	@FindBy(xpath = "//a[text()='Edit your account information']")
	WebElement editYourInfoLink;

	public String getMyAccountText() {
		return myAccountText.getText();
	}

	public EditPage clickEditInfoLink() {
		editYourInfoLink.click();
		return new EditPage();
	}

	public String getSuccessMessage() {
		return acccountInfoUpdateSuccessMessage.getText();
	}

}
