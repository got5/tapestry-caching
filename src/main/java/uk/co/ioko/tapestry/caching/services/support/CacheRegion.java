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

package uk.co.ioko.tapestry.caching.services.support;

/**
 * Enum representing the available cache regions.  Note that each cache name suffix matches a named
 * cache, as configured in the Ehcache context - these do not necessarily have to match the Enum
 * names as they do currently.
 *
 * @author seldred
 */
public enum CacheRegion {

	SHORT("short"), MEDIUM("medium"), LONG("long");

	private String cacheNameSuffix;

	private CacheRegion(String cacheNameSuffix) {
		this.cacheNameSuffix = cacheNameSuffix;
	}

	public String getCacheNameSuffix() {
		return cacheNameSuffix;
	}
}