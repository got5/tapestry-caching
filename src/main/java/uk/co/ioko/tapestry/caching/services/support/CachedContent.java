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
import java.util.List;

public class CachedContent implements Serializable {

	private static final long serialVersionUID = -4232730988618575451L;

	private String content;

	private List<MethodCall> renderSupportMethodCalls;

	private List<MethodCall> clientBehaviorSupportMethodCalls;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MethodCall> getRenderSupportMethodCalls() {
		return renderSupportMethodCalls;
	}

	public void setRenderSupportMethodCalls(List<MethodCall> methodCalls) {
		this.renderSupportMethodCalls = methodCalls;
	}

	public List<MethodCall> getClientBehaviorSupportMethodCalls() {
		return clientBehaviorSupportMethodCalls;
	}

	public void setClientBehaviorSupportMethodCalls(List<MethodCall> methodCalls) {
		this.clientBehaviorSupportMethodCalls = methodCalls;
	}
}