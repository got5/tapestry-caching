package uk.co.ioko.tapestry.caching.tests;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class TestZone extends SeleniumTestCase {

	@Test
	public void testZone() {
		refreshZone();
		// The page is reloaded (zone is cached), and we check if the zone has
		// been well initialized.
		refreshZone();
	}

	private void refreshZone() {
		open("/PageTestZone");

		assertTrue(getHtmlSource().contains("[Initial value]"));

		click("//a[@class='linkZone']");

		waitForAjaxRequestsToComplete("10000");

		assertTrue(getHtmlSource().contains("[ActionLink component clicked]"));
	}
}
