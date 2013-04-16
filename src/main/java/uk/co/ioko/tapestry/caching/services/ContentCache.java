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

import uk.co.ioko.tapestry.caching.services.support.CacheRegion;
import uk.co.ioko.tapestry.caching.services.support.CachedContent;

/**
 * Provides methods for retrieving cached content.  Note that cached content consists of two parts:
 * a simple string representing the plain text of the content after it was rendered and a list of
 * cached method calls made to RenderSupport.
 *
 * @author seldred
 */
public interface ContentCache {

	/**
	 * Attempts to get the named content from the specified cache, where cache region is either
	 * "short", "medium" or "long".  Returns null if no matching component is found.
	 * @param contentKey the key for storing the content
	 * @param cacheRegion either "short", "medium" or "long"
	 * @return the cached content or null if not found
	 */
	public CachedContent getContent(String contentKey, CacheRegion cacheRegion);

	/**
	 * Adds the named content to the specified cache, where cache region is either "short",
	 * "medium" or "long".  The content is keyed on the given key.
	 * @param contentKey the key for storing the content
	 * @param content the content to add
	 * @param cacheRegion either "short", "medium" or "long"
	 */
	public void addContent(String contentKey, CachedContent content, CacheRegion cacheRegion);
}