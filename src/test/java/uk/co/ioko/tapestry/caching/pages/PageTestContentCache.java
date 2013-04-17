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

package uk.co.ioko.tapestry.caching.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;

import java.util.Date;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jun 23, 2009 Time: 8:51:45 AM
 */
public class PageTestContentCache {

	@Property
	private Date now;

	@Property
	@Persist
	private Integer count;
	
	@SetupRender
	public void setupRender() {
		if (count == null) {
			count = 0;
		}

		this.now = new Date();
	}
	
	@OnEvent(value="increment")
	public void onIncrement() {
		count++;
	}
}
