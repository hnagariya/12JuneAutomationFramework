package com.naveenautomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naveenautomation.base.TestBase;

public class EditPage extends TestBase {

	public EditPage() {
		PageFactory.initElements(wd, this);
	}

	@FindBy(id = "input-firstname")
	WebElement firstNameInput;

	@FindBy(id = "input-lastname")
	WebElement lastNameInput;

	@FindBy(id = "input-email")
	WebElement emailInput;

	@FindBy(id = "input-telephone")
	WebElement telePhoneInput;

	@FindBy(css = "input[value='Continue']")
	WebElement submitBtn;

	public void enterFName(String name) {
		firstNameInput.sendKeys(name);
	}

	public void enterLName(String lname) {
		lastNameInput.sendKeys(lname);
	}

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}

	public void enterTelephone(String number) {
		telePhoneInput.sendKeys(number);
	}

	public AccountPage clickSubmitBtn() {
		submitBtn.click();
		return new AccountPage();
	}

}
