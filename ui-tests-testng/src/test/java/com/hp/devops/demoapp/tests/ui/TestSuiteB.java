package com.hp.devops.demoapp.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 03/12/14
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class TestSuiteB {

	private WebDriver driver;
	private String autHost;
	private String autPort;
	private String proxyHost;   //  web-proxy.bbn.hp.com
	private String proxyPort;   //  8080

	private void setUP() {
		autHost = System.getProperty("app.host");
		if (autHost == null || autHost == "") autHost = "http://54.146.140.70";
		autPort = System.getProperty("app.port");
		if (autPort == null || autPort == "") autPort = "9000";

		proxyHost = System.getProperty("proxy.host");
		proxyPort = System.getProperty("proxy.port");

		if (proxyHost == null || proxyPort == null || proxyHost == "" || proxyPort == "") {
			driver = new HtmlUnitDriver();
		} else {
			Proxy proxy = new Proxy();
			proxy.setHttpProxy(proxyHost + ":" + proxyPort);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.PROXY, proxy);
			driver = new HtmlUnitDriver(cap);
		}

		driver.get(autHost + ":" + autPort);
	}

	@BeforeClass
	public void beforeClass() {
		setUP();
	}

	@BeforeGroups(groups = {"Group_B"})
	public void beforeGroups() {
		setUP();
	}

	@Test(groups = {"Group_B"})
	public void testCaseA() {
		WebElement query = driver.findElement(By.id("bandsList"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@Test
	public void testCaseB() {
		WebElement query = driver.findElement(By.id("totalVotes"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@Test(groups = {"Group_B"})
	public void testCaseC() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@AfterGroups(groups = {"Group_B", "Group_1"})
	public void afterGroups() {
		driver.quit();
	}
}
