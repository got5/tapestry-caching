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

package uk.co.ioko.tapestry.caching.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 24, 2009 Time: 12:09:21 PM
 */
@SubModule(TestModule.class)
public class TestOverrideConfigModule {
	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {
		configuration.add("contentCache.cacheConfigurationFile",
				"/ehcacheTapestryContent-test.xml");
	}
}
