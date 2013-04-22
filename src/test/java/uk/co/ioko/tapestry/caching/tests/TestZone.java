package uk.co.ioko.tapestry.caching.tests;

import junit.framework.TestCase;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestZone extends TestCase {
	
	private PageTester pageTester;

	@Override
	protected void setUp() throws Exception
	{
		String appPackage = "uk.co.ioko.tapestry.caching";
		String appName = "Test";
		pageTester = new PageTester(appPackage, appName, "src/test/webapp");
		super.setUp();
	}
	
	@Test
	public void testZone() {
		Document page = pageTester.renderPage("PageTestZone");
		Assert.assertNotNull(page);
		
		Element element = page.getElementById("divValue");
		Assert.assertEquals(element.getChildMarkup(), "[Initial value]");
		
		Element link = page.getElementById("linkZone");
		pageTester.clickLink(link);
		
		Assert.assertEquals(element.getChildMarkup(), "[ActionLink component clicked]");
		
		//The page is reloaded (zone is cached), and we check if the zone has been well initialized.
		
		page = pageTester.renderPage("PageTestZone");
		
		link = page.getElementById("linkZone");
		pageTester.clickLink(link);
		
		element = page.getElementById("divValue");
		Assert.assertEquals(element.getChildMarkup(), "[ActionLink component clicked]");
	}
}
