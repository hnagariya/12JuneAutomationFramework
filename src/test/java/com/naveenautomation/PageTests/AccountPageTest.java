package com.naveenautomation.PageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.AccountPage;
import com.naveenautomation.pages.EditPage;
import com.naveenautomation.pages.LoginPage;

public class AccountPageTest extends TestBase {

	LoginPage loginPage;
	AccountPage accountPage;
	EditPage editPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage();

	}

	@Test
	public void validateUserCanUpdatePersonalInfo() {
		accountPage = loginPage.SubmitLogin("zenduo@email.com", "zenduo@123");
		editPage = accountPage.clickEditInfoLink();
		editPage.enterFName("Cheater");
		accountPage = editPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessage(), "Success: Your account has been successfully updated.",
				"Info not updated");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
