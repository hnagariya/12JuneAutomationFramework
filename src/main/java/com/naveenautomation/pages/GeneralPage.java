package com.naveenautomation.pages;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GeneralPage extends LoadableComponent<GeneralPage> {

	protected WebDriver wd;
	private static final int DEFAULT_TIME_FOR_PAGE_LOAD = 60;

	public GeneralPage(WebDriver wd, boolean waitForPageToLoad) {
		this.wd = wd;
		if (waitForPageToLoad) {
			this.waitForDocumentCompleteState();
		}
	}

	@Override
	protected void load() {
		String pageURL = getPageUrl();

		wd.get(pageURL);

	}

	public boolean waitForDocumentCompleteState() {
		return new WebDriverWait(wd, DEFAULT_TIME_FOR_PAGE_LOAD).until((ExpectedCondition<Boolean>) p -> {
			while (true) {
				String documentState = getDocumentReadyState();
				if (documentState.equals("complete")) {
					return true;
				}
			}
		});
	}

	public String getDocumentReadyState() {
		JavascriptExecutor jse = (JavascriptExecutor) wd;
		return jse.executeScript("return document.readyState").toString();
	}

	@Override
	public GeneralPage get() {
		return super.get();
	}

	protected boolean urlContains(String url) {

		try {
			String pageUrl = getPageUrl();
			URL pageUrlObject = new URL(pageUrl);
			URL urlObject = new URL(url);
			String pageUrlHost = pageUrlObject.getHost();
			String urlHost = urlObject.getHost();
			if (pageUrlHost.equalsIgnoreCase(urlHost)) {
				String pageUrlExclHost = pageUrl.substring(pageUrl.indexOf(pageUrlHost) + pageUrlHost.length());
				String urlExclHost = url.substring(url.indexOf(urlHost) + urlHost.length());
				System.out.println(
						"Comparing pages , actual: " + urlExclHost + " , and expected is : " + pageUrlExclHost);
				return urlExclHost.toLowerCase().contains(pageUrlExclHost.toLowerCase());
			}
		} catch (MalformedURLException e) {

		}
		return false;
	}

	@Override
	protected abstract void isLoaded();

	protected abstract String getPageUrl();

	@SafeVarargs
	public final GeneralPage waitForPageToLoad(final Class<? extends GeneralPage>... pagestoWaitFor) {

		return waitForPageToLoad(30, pagestoWaitFor);
	}

	@SafeVarargs
	protected final GeneralPage waitForPageToLoad(int timeForLoad,
			final Class<? extends GeneralPage>... pagestoWaitFor) {

		return new WebDriverWait(wd, timeForLoad).until(new ExpectedCondition<GeneralPage>() {

			@Override
			public GeneralPage apply(WebDriver input) {

				for (Class<? extends GeneralPage> page : pagestoWaitFor) {

					try {
						GeneralPage pg = page.getConstructor(WebDriver.class, Boolean.TYPE).newInstance(wd, true);
						return pg;
					} catch (InstantiationException e) {
						
					} catch (IllegalAccessException e) {
						
					} catch (IllegalArgumentException e) {
						
					} catch (InvocationTargetException e) {
						
					} catch (NoSuchMethodException e) {
						
					} catch (SecurityException e) {
						
					}

				}
				return null;

			}
		});

	}
}
