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

package uk.co.ioko.tapestry.caching.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ClientBehaviorSupport;
import org.apache.tapestry5.services.Environment;

import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import uk.co.ioko.tapestry.caching.services.ClientBehaviorSupportPlayer;
import uk.co.ioko.tapestry.caching.services.ClientBehaviorSupportRecorder;
import uk.co.ioko.tapestry.caching.services.ContentCache;
import uk.co.ioko.tapestry.caching.services.JavascriptSupportPlayer;
import uk.co.ioko.tapestry.caching.services.JavascriptSupportRecorder;
import uk.co.ioko.tapestry.caching.services.support.CacheRegion;
import uk.co.ioko.tapestry.caching.services.support.CachedContent;

/**
 * This component provides caching of the generated HTML for a page snippet in EHCache.
 */
@SupportsInformalParameters
public class Container {

	private static final String CACHE_KEY_SEPARATOR = ":";

	/**
	 * Specifies how long this content should be cached for - should be 'short', 'medium' or 'long' (case-insensitive)
	 * as defined by the CacheRegion enum.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value = "medium")
	private CacheRegion cacheRegion;

	/**
	 * By default, cached components are keyed on their full class name. If you wish to provide your own key you can do
	 * so by providing a cacheKey. Note that this will override the cacheKeySuffix parameter.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String cacheKey;

	/**
	 * By default, cached components are keyed on their full class name. If this granularity is insufficient, you can
	 * provide a cacheKeySuffix that will differentiate between different instances of the same component.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String cacheKeySuffix;

	@Inject
	private ComponentResources componentResources;
	
	/*@Inject
	private TimerService timer;*/

	// ==== STUFF REQUIRED FOR JS/CSS CACHING ====

    @Environmental
	private JavaScriptSupport javascriptSupport;

	@Environmental
	private ClientBehaviorSupport clientBehaviorSupport;

	@Inject
	private Environment environment;

	@Inject
	private ContentCache contentCache;

	// ===========================================

	@Parameter(allowNull = false, defaultPrefix = BindingConstants.LITERAL)
	private String element = componentResources.getElementName();

	private Element currentElement;

	private String key;
	
	@SetupRender
	public boolean setupRender(MarkupWriter markupWriter) {
		//timer.start("Start component render for : " + element);
		
		key = getComponentKey();
		CachedContent content = contentCache.getContent(key, cacheRegion);
		if (content == null) {
			return true;
		}
		markupWriter.writeRaw(content.getContent());

		return false;
	}

	@BeginRender
	public void beginRender(MarkupWriter markupWriter) {
		currentElement = markupWriter.element(element);
		componentResources.renderInformalParameters(markupWriter);

		// this replaces the instance of RenderSupport for this thread only
		JavascriptSupportRecorder javascriptSupportRecorder = new JavascriptSupportRecorder(javascriptSupport);
		environment.push(JavaScriptSupport.class, javascriptSupportRecorder);

		// this replaces the instance of ClientBehaviorSupport for this thread only
		ClientBehaviorSupportRecorder cbsRecorder = new ClientBehaviorSupportRecorder(clientBehaviorSupport);
		environment.push(ClientBehaviorSupport.class, cbsRecorder);
	}

	@AfterRender
	public void afterRender(MarkupWriter markupWriter) {
		// end markup
		markupWriter.end();

		// set RenderSupport back to whatever it was before
		JavascriptSupportRecorder javascriptSupportRecorder = (JavascriptSupportRecorder) environment.pop(JavaScriptSupport.class);
		ClientBehaviorSupportRecorder cbsRecorder = (ClientBehaviorSupportRecorder) environment
				.pop(ClientBehaviorSupport.class);

		// cache content
		CachedContent content = new CachedContent();
		content.setContent(currentElement.toString());
		
		content.setRenderSupportMethodCalls(javascriptSupportRecorder.getMethodCalls());
		content.setClientBehaviorSupportMethodCalls(cbsRecorder.getMethodCalls());
		contentCache.addContent(key, content, cacheRegion);
	}
	
	@CleanupRender
	public void cleanupRender() {
		CachedContent content = contentCache.getContent(key, cacheRegion);
		if (content != null) {
			// this will re-call all the RenderSupport methods we called previously
			JavascriptSupportPlayer rsPlayer = new JavascriptSupportPlayer(javascriptSupport);
			rsPlayer.playbackMethodCalls(content.getRenderSupportMethodCalls());

			// this will re-call all the ClientBehaviorSupport methods we called previously
			ClientBehaviorSupportPlayer cbsPlayer = new ClientBehaviorSupportPlayer(clientBehaviorSupport);
			cbsPlayer.playbackMethodCalls(content.getClientBehaviorSupportMethodCalls());
		}
		
		//timer.saveState("Stop component render for : " + element);
		//timer.stop();
	}
	

	/**
	 * Returns the key that we want to cache our component against. Depending on the parameters, this is either the
	 * cacheKey, the component name + cacheKeySuffix or just the component name. The cacheKey will take precedence over
	 * any other parameters.
	 */
	private String getComponentKey() {
		// if cacheKey is set then it overrides everything else
		if ((cacheKey == null) || (cacheKey.length() == 0)) {
			// otherwise use the content key as a starting point
			String myCacheKey = componentResources.getCompleteId();
			// and add the cache key suffix if there is one
			if ((cacheKeySuffix != null) && (cacheKeySuffix.length() > 0)) {
				myCacheKey += CACHE_KEY_SEPARATOR + cacheKeySuffix;
			}
			return myCacheKey;
		}
		return cacheKey;
	}
}
