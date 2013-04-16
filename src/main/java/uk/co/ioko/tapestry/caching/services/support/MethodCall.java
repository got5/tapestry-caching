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

import java.io.Serializable;

/**
 * Simple DTO that allows us to recreate a method call made to RenderSupport.  For cached components,
 * we are then able to 'playback' any associated method calls.
 *
 * @author seldred
 */
public class MethodCall implements Serializable {
	private static final long serialVersionUID = 3246458478687721828L;

	private String methodName;

	private Class<?>[] paramTypes;

	private Object[] params;

	public MethodCall(String methodName, Class<?>[] paramTypes, Object[] params) {
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public Object[] getParams() {
		return params;
	}
}