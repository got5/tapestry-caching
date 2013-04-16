/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     ioko tapestry-commons is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ioko tapestry-commons is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.caching.tests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.apache.tapestry5.test.PageTester;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 23, 2009 Time: 11:15:39 AM
 */
public class TestContentCache {

	@Test
	public void testCaching() throws InterruptedException {
		PageTester pageTester = new PageTester("uk.co.ioko.tapestry.caching", "Test", "src/test/webapp");
		Document page1 = pageTester.renderPage("PageTestContentCache");
		Assert.assertNotNull(page1);

		String live = page1.getElementById("live").getChildMarkup();
		String cached = getCachedElement(page1);
		String cachedSuffix = getCachedSuffixElement(page1);

		String liveDate = getDate(live);
		String cachedDate = getDate(cached);
		String cachedSuffixDate = getDate(cachedSuffix);
		Integer liveIncrement = getIncrement(live);
		Integer cachedIncrement = getIncrement(cached);
		Integer cacheSuffixIncrement = getIncrement(cachedSuffix);

		Assert.assertEquals(liveDate, cachedDate);
		Assert.assertEquals(liveDate, cachedSuffixDate);
		Assert.assertEquals(liveIncrement, cachedIncrement);
		Assert.assertEquals(liveIncrement, cacheSuffixIncrement);

		Element increment = page1.getElementById("increment");

		Thread.sleep(1000);
		Document page2 = pageTester.clickLink(increment);

		/* The page should now have
			Live - updated and incremented
			Cached - not updated at all
			Cached Suffix - updated and incremented
		*/
		live = page2.getElementById("live").getChildMarkup();
		cached = getCachedElement(page2);
		cachedSuffix = getCachedSuffixElement(page2);


		String liveDate2 = getDate(live);
		String cachedDate2 = getDate(cached);
		String cachedSuffixDate2 = getDate(cachedSuffix);
		Integer liveIncrement2 = getIncrement(live);
		Integer cachedIncrement2 = getIncrement(cached);
		Integer cacheSuffixIncrement2 = getIncrement(cachedSuffix);

		Assert.assertTrue(!liveDate2.equals(liveDate));
		Assert.assertEquals(liveDate, cachedDate2);
		Assert.assertEquals(liveDate2, cachedSuffixDate2);

		Assert.assertEquals((Integer)(liveIncrement2-1), liveIncrement);
		Assert.assertEquals((Integer)(liveIncrement2-1), cachedIncrement2);
		Assert.assertEquals(liveIncrement2, cacheSuffixIncrement2);

		Thread.sleep(1000);
		Document page3 = pageTester.renderPage("PageTestContentCache");

		/* The page should now have
			Live - updated date but not increment
			Cached - not updated at all
			Cached Suffix - will match page 2
		*/
		live = page3.getElementById("live").getChildMarkup();
		cached = getCachedElement(page3);
		cachedSuffix = getCachedSuffixElement(page3);

		String liveDate3 = getDate(live);
		String cachedDate3 = getDate(cached);
		String cachedSuffixDate3 = getDate(cachedSuffix);
		Integer liveIncrement3 = getIncrement(live);
		Integer cachedIncrement3 = getIncrement(cached);
		Integer cacheSuffixIncrement3 = getIncrement(cachedSuffix);

		Assert.assertTrue(!liveDate3.equals(liveDate));
		Assert.assertTrue(!liveDate3.equals(liveDate2));
		Assert.assertEquals(liveDate, cachedDate3);
		Assert.assertEquals(cachedSuffixDate3, cachedSuffixDate2);

		Assert.assertEquals((Integer)(liveIncrement3-1), liveIncrement);
		Assert.assertEquals((Integer)(liveIncrement3-1), cachedIncrement3);
		Assert.assertEquals(liveIncrement3, cacheSuffixIncrement3);
	}

	private String getCachedSuffixElement(Document document) {
		Node node = ((Element) document.getRootElement().getChildren().get(1)).getChildren().get(6);
		return node.toString();
	}

	private String getCachedElement(Document document){
		Node node =  ((Element) document.getRootElement().getChildren().get(1)).getChildren().get(4);
		return node.toString();
	}

	/**
	 * We need to parse the string as the cached version is just text and not elements
	 *
	 * @param markup
	 * @return
	 */
	private String getDate(String markup) {

		int start = markup.indexOf("<dd>");
		int end = markup.indexOf("</dd>");

		return markup.substring(start + 4, end);
	}

	/**
	 * We need to parse the string as the cached version is just text and not elements
	 *
	 * @param markup
	 * @return
	 */
	private Integer getIncrement(String markup) {

		int start = markup.lastIndexOf("<dd>");
		int end = markup.lastIndexOf("</dd>");

		return Integer.valueOf(markup.substring(start + 4, end));

	}
}
