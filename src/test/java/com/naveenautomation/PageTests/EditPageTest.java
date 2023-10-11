package com.naveenautomation.PageTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class EditPageTest {
	@Test
	public void test1() {
		System.out.println(new Date());

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println(timeStamp);
	}

}
