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

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldFocusPriority;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Proxy class for RenderSupport that records all methods called on it. This is so we can 'playback' method calls for
 * cached components.
 *
 * @author seldred
 */
public class JavascriptSupportRecorder extends SupportRecorder implements JavaScriptSupport {


    private JavaScriptSupport javaScriptSupport;

    public JavascriptSupportRecorder(JavaScriptSupport javaScriptSupport) {
        this.javaScriptSupport = javaScriptSupport;
    }

    private Method getMethod(String name, Class<?>... parameterTypes) {
        return super.getMethod(JavaScriptSupport.class, name, parameterTypes);
    }

    public String allocateClientId(String id) {
        Method method = getMethod("allocateClientId", String.class);
        recordMethodCall(method, id);
        return javaScriptSupport.allocateClientId(id);
    }

    public String allocateClientId(ComponentResources resources) {
        Method method = getMethod("allocateClientId", ComponentResources.class);
        recordMethodCall(method, resources);
        return javaScriptSupport.allocateClientId(resources);
    }

    public void addScript(String format, Object... arguments) {
        Method method = getMethod("addScript", String.class, Object[].class);
        recordMethodCall(method, format, arguments);
        javaScriptSupport.addScript(format, arguments);

    }

    public void addScript(InitializationPriority priority, String format, Object... arguments) {
        Method method = getMethod("addScript", InitializationPriority.class, String.class, Object[].class);
        recordMethodCall(method, priority, format, arguments);
        javaScriptSupport.addScript(priority, format, arguments);
    }

    public void addInitializerCall(String functionName, JSONObject parameter) {
        Method method = getMethod("addInitializerCall", String.class, JSONObject.class);
        recordMethodCall(method, functionName, parameter);
        javaScriptSupport.addInitializerCall(functionName, parameter);

    }

    public void addInitializerCall(InitializationPriority priority, String functionName, JSONObject parameter) {
        Method method = getMethod("addInitializerCall", InitializationPriority.class, String.class, JSONObject.class);
        recordMethodCall(method, priority, functionName, parameter);
        javaScriptSupport.addInitializerCall(priority, functionName, parameter);
    }
    
    public void addInitializerCall(InitializationPriority priority, String functionName, JSONArray parameter) {
        Method method = getMethod("addInitializerCall", InitializationPriority.class, String.class, JSONArray.class);
        recordMethodCall(method, priority, functionName, parameter);
        javaScriptSupport.addInitializerCall(priority, functionName, parameter);
    }
    
    public void addInitializerCall(String functionName, JSONArray parameter) {
        Method method = getMethod("addInitializerCall", String.class, JSONArray.class);
        recordMethodCall(method, functionName, parameter);
        javaScriptSupport.addInitializerCall(functionName, parameter);
    }

    public void addInitializerCall(String functionName, String parameter) {
        Method method = getMethod("addInitializerCall", String.class, String.class);
        recordMethodCall(method, functionName, parameter);
        javaScriptSupport.addInitializerCall(functionName, parameter);
    }

    public void addInitializerCall(InitializationPriority priority, String functionName, String parameter) {
        Method method = getMethod("addInitializerCall", InitializationPriority.class, String.class, String.class);
        recordMethodCall(method, priority, functionName, parameter);
        javaScriptSupport.addInitializerCall(priority, functionName, parameter);
    }

    public void importJavaScriptLibrary(Asset asset) {
        Method method = getMethod("importJavaScriptLibrary", Asset.class);
        recordMethodCall(method, asset);
        javaScriptSupport.importJavaScriptLibrary(asset);
    }

    public void importStylesheet(Asset stylesheet) {
        Method method = getMethod("importStylesheet", Asset.class);
        recordMethodCall(method, stylesheet);
        javaScriptSupport.importStylesheet(stylesheet);

    }

    public void importStylesheet(StylesheetLink stylesheetLink) {
        Method method = getMethod("importStylesheet", StylesheetLink.class);
        recordMethodCall(method, stylesheetLink);
        javaScriptSupport.importStylesheet(stylesheetLink);
    }

    public void importStack(String stackName) {
        Method method = getMethod("importStack", String.class);
        recordMethodCall(method, stackName);
        javaScriptSupport.importStack(stackName);

    }

    public void importJavaScriptLibrary(String libraryURL) {
        Method method = getMethod("importJavaScriptLibrary", String.class);
        recordMethodCall(method, libraryURL);
        javaScriptSupport.importJavaScriptLibrary(libraryURL);
    }

    public void autofocus(FieldFocusPriority priority, String fieldId) {
        Method method = getMethod("autofocus", FieldFocusPriority.class, String.class);
        recordMethodCall(method, priority, fieldId);
        javaScriptSupport.autofocus(priority, fieldId);
    }
}