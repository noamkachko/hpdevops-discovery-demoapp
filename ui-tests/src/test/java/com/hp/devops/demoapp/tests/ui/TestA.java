package com.hp.devops.demoapp.tests.ui;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 25/11/14
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class TestA {

	static private WebDriver driver;

	@BeforeClass
	static public void beforeAll() {
		driver = new HtmlUnitDriver();
		driver.get("http://54.146.140.70:9000");
	}

	@Test
	public void testUIcaseA() {
		WebElement query = driver.findElement(By.id("bandsList"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@AfterClass
	static public void afterAll() {
		driver.quit();
	}
}
