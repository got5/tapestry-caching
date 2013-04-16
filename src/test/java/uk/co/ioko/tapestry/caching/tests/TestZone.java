package uk.co.ioko.tapestry.caching.tests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestZone {
	@Test
	public void testZone() {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.caching", "Test", "src/test/webapp");
		Document page = pageTester.renderPage("PageTestZone");
		Assert.assertNotNull(page);
		
		
	}
}
