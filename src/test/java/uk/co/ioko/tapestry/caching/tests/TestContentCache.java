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

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 23, 2009 Time: 11:15:39 AM
 */
public class TestContentCache extends SeleniumTestCase {

	@Test
	public void testCaching() throws InterruptedException {
		open("PageTestContentCache");

		Data data = new Data();
		data = updateData(data);

		Assert.assertEquals(data.getLiveDate(), data.getCachedDate());
		Assert.assertEquals(data.getLiveDate(), data.getCachedSuffixDate());
		Assert.assertEquals(data.getLiveIncrement(), data.getCachedIncrement());
		Assert.assertEquals(data.getLiveIncrement(),
				data.getCacheSuffixIncrement());

		click("//a[@id='increment']");

		/*
		 * The page should now have Live - updated and incremented Cached - not
		 * updated at all Cached Suffix - updated and incremented
		 */
		Data data2 = new Data();
		data2 = updateData(data2);

		Assert.assertTrue(!data2.getLiveDate().equals(data.getLiveDate()));
		Assert.assertEquals(data.getLiveDate(), data2.getCachedDate());
		Assert.assertEquals(data2.getLiveDate(), data2.getCachedSuffixDate());

		Assert.assertEquals((Integer) (data2.getLiveIncrement() - 1),
				data.getLiveIncrement());
		Assert.assertEquals((Integer) (data2.getLiveIncrement() - 1),
				data2.getCachedIncrement());
		Assert.assertEquals(data2.getLiveIncrement(),
				data2.getCacheSuffixIncrement());

		open("PageTestContentCache");

		/*
		 * The page should now have Live - updated date but not increment Cached
		 * - not updated at all Cached Suffix - will match page 2
		 */

		Data data3 = new Data();
		data3 = updateData(data3);

		Assert.assertTrue(!data3.getLiveDate().equals(data.getLiveDate()));
		Assert.assertTrue(!data3.getLiveDate().equals(data2.getLiveDate()));
		Assert.assertEquals(data.getLiveDate(), data3.getCachedDate());
		Assert.assertEquals(data3.getCachedSuffixDate(),
				data2.getCachedSuffixDate());

		Assert.assertEquals((Integer) (data3.getLiveIncrement() - 1),
				data.getLiveIncrement());
		Assert.assertEquals((Integer) (data3.getLiveIncrement() - 1),
				data3.getCachedIncrement());
		Assert.assertEquals(data3.getLiveIncrement(),
				data3.getCacheSuffixIncrement());
	}

	private Data updateData(Data data) {
		if (data == null)
			data = new Data();
		data.setLiveDate(getText("//dl[@id='live']/dd[1]"));
		data.setCachedDate(getText("//dl[@id='cached']/dd[1]"));
		data.setCachedSuffixDate(getText("//dl[@id='cachedSuffix']/dd[1]"));
		data.setLiveIncrement(Integer
				.valueOf(getText("//dl[@id='live']/dd[2]")));
		data.setCachedIncrement(Integer
				.valueOf(getText("//dl[@id='cached']/dd[2]")));
		data.setCacheSuffixIncrement(Integer
				.valueOf(getText("//dl[@id='cachedSuffix']/dd[2]")));
		System.out.println(data);
		return data;
	}

	private class Data {

		private String liveDate;
		private String cachedDate;
		private String cachedSuffixDate;
		private Integer liveIncrement;
		private Integer cachedIncrement;
		private Integer cacheSuffixIncrement;

		public String getLiveDate() {
			return liveDate;
		}

		public void setLiveDate(String liveDate) {
			this.liveDate = liveDate;
		}

		public String getCachedDate() {
			return cachedDate;
		}

		public void setCachedDate(String cachedDate) {
			this.cachedDate = cachedDate;
		}

		public String getCachedSuffixDate() {
			return cachedSuffixDate;
		}

		public void setCachedSuffixDate(String cachedSuffixDate) {
			this.cachedSuffixDate = cachedSuffixDate;
		}

		public Integer getLiveIncrement() {
			return liveIncrement;
		}

		public void setLiveIncrement(Integer liveIncrement) {
			this.liveIncrement = liveIncrement;
		}

		public Integer getCachedIncrement() {
			return cachedIncrement;
		}

		public void setCachedIncrement(Integer cachedIncrement) {
			this.cachedIncrement = cachedIncrement;
		}

		public Integer getCacheSuffixIncrement() {
			return cacheSuffixIncrement;
		}

		public void setCacheSuffixIncrement(Integer cacheSuffixIncrement) {
			this.cacheSuffixIncrement = cacheSuffixIncrement;
		}

		@Override
		public String toString() {
			return "Data [liveDate=" + liveDate + ", cachedDate=" + cachedDate
					+ ", cachedSuffixDate=" + cachedSuffixDate
					+ ", liveIncrement=" + liveIncrement + ", cachedIncrement="
					+ cachedIncrement + ", cacheSuffixIncrement="
					+ cacheSuffixIncrement + "]";
		}

	}
}
