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
	static private String testProxy = "web-proxy.bbn.hp.com:8081";
    static private String appUrl = "http://54.146.140.70:9000";

	@BeforeClass
	static public void beforeAll() {

        if("true".equals(System.getProperty("proxy"))){
            isBehindProxy = true;
            System.out.println("isBehindProxy is true!");
            if(System.getenv("testproxy")!=null){
                testProxy = System.getenv("testproxy");
            }
            System.out.println("testProxy is " + testProxy + "; can be modified via environment variable, i.e., 'export textproxy=web-proxy.bbn.hp.com:8080'");
        } else {
            System.out.println("We do not use proxy");
        }

		if (isBehindProxy) {
			Proxy proxy = new Proxy();
			proxy.setHttpProxy(testProxy);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.PROXY, proxy);
			driver = new HtmlUnitDriver(cap);
		} else {
			driver = new HtmlUnitDriver();
		}
        if(System.getProperty("appUrl")!=null){
            driver.get(System.getProperty("appUrl"));
        } else {
            driver.get(appUrl);
        }
        System.out.println("App URL is " + appUrl+"; can be modifed via system property, i.e., '-DappUrl=\"http://54.146.140.70:9000\"'");
    }

	@Test
	public void testUIcaseA() {
        System.out.println("Proudly running test " + Thread.currentThread().getStackTrace()[1]);
        WebElement query = driver.findElement(By.id("bandsList"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@Test
	public void testUIcaseB() {
        System.out.println("Proudly running test " + Thread.currentThread().getStackTrace()[1]);
		WebElement query = driver.findElement(By.id("totalVotes"));
		Assert.assertEquals(query.getTagName(), "div");
		Assert.assertEquals(query.isDisplayed(), true);
	}

	@AfterClass
	static public void afterAll() {
		driver.quit();
	}
}
