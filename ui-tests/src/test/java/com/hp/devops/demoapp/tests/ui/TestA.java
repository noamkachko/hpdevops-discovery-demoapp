package com.hp.devops.demoapp.tests.ui;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 25/11/14
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class TestA {

	static private WebDriver driver;
	static private boolean isBehindProxy = false;
	static private final String PROXY = "web-proxy.bbn.hp.com:8080";

	@BeforeClass
	static public void beforeAll() {
		if (isBehindProxy) {
			Proxy proxy = new Proxy();
			proxy.setHttpProxy(PROXY);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.PROXY, proxy);
			driver = new HtmlUnitDriver(cap);
		} else {
			driver = new HtmlUnitDriver();
		}
		driver.get("http://54.146.140.70:9000");
	}

	@Test
	public void testUIcaseA() {
		WebElement query = driver.findElement(By.id("bandsList"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@Test
	public void testUIcaseB() {
		WebElement query = driver.findElement(By.id("totalVotes"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@AfterClass
	static public void afterAll() {
		driver.quit();
	}
}
