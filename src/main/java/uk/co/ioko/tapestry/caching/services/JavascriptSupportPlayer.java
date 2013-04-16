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

import java.util.List;

import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import uk.co.ioko.tapestry.caching.services.support.MethodCall;

public class JavascriptSupportPlayer extends SupportPlayer {

	private JavaScriptSupport javascriptSupport;

	public JavascriptSupportPlayer(JavaScriptSupport javascriptSupport) {
		this.javascriptSupport = javascriptSupport;
	}

	public void playbackMethodCalls(List<MethodCall> methodCalls) {
		super.playbackMethodCalls(javascriptSupport, methodCalls);
	}
}