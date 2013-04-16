/*
 * Copyright (c) 2009 ioko365 Ltd
 *
 * This file is part of ioko tapestry-commons.
 *
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ioko tapestry-commons.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.co.ioko.tapestry.caching.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.services.LibraryMapping;

import uk.co.ioko.tapestry.caching.services.support.CacheRegion;

/**
 * Created by IntelliJ IDEA. User: ben Date: Mar 25, 2009 Time: 4:26:18 PM
 */
public class CacheModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(ContentCache.class, ContentCacheImpl.class);
		binder.bind(TimerService.class, TimerServiceImpl.class);
	}


	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("cache", "uk.co.ioko.tapestry.caching"));

	}

	public static void contributeTypeCoercer(Configuration<CoercionTuple> configuration) {
		/*
		   * String -> CacheRegion
		   */
		Coercion<String, CacheRegion> stringToCacheRegion = new Coercion<String, CacheRegion>() {
			public CacheRegion coerce(String input) {
				return CacheRegion.valueOf(input.toUpperCase());
			}
		};
		configuration.add(new CoercionTuple<String, CacheRegion>(String.class, CacheRegion.class, stringToCacheRegion));
	}


	public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add("contentCache.cacheConfigurationFile", "/ehcacheTapestryContent.xml");
	}
}
