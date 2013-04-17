package uk.co.ioko.tapestry.caching.services;

import java.lang.reflect.Method;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.corelib.data.InsertPosition;
import org.apache.tapestry5.services.ClientBehaviorSupport;

public class ClientBehaviorSupportRecorder extends SupportRecorder implements ClientBehaviorSupport {

	private ClientBehaviorSupport clientBehaviorSupport;

	public ClientBehaviorSupportRecorder(ClientBehaviorSupport clientBehaviorSupport) {
		this.clientBehaviorSupport = clientBehaviorSupport;
	}

	@SuppressWarnings({"deprecation"})
    public void addFormFragment(String clientId, String showFunctionName, String hideFunctionName) {
		Method method = getMethod("addFormFragment", String.class, String.class, String.class);
		recordMethodCall(method, new Object[] { clientId, showFunctionName, hideFunctionName });
		clientBehaviorSupport.addFormFragment(clientId, showFunctionName, hideFunctionName);
	}

    public void addFormFragment(String clientId, boolean alwaysSubmit, String showFunctionName, String hideFunctionName) {
        Method method = getMethod("addFormFragment", String.class, boolean.class, String.class, String.class);
        recordMethodCall(method, new Object[] {clientId, alwaysSubmit, showFunctionName, hideFunctionName});
        clientBehaviorSupport.addFormFragment(clientId, alwaysSubmit, showFunctionName, hideFunctionName);
    }

    public void addFormInjector(String clientId, Link link, InsertPosition insertPosition, String showFunctionName) {
		Method method = getMethod("addFormInjector", String.class, Link.class, InsertPosition.class, String.class);
		recordMethodCall(method, new Object[] { clientId, link, insertPosition, showFunctionName });
		clientBehaviorSupport.addFormInjector(clientId, link, insertPosition, showFunctionName);
	}

	public void addValidation(Field field, String validationName, String message, Object constraint) {
		Method method = getMethod("addValidation", Field.class, String.class, String.class, Object.class);
		recordMethodCall(method, new Object[] { field, validationName, message, constraint });
		clientBehaviorSupport.addValidation(field, validationName, message, constraint);
	}

	public void addZone(String clientId, String showFunctionName, String updateFunctionName) {
		Method method = getMethod("addZone", String.class, String.class, String.class);
		recordMethodCall(method, new Object[] { clientId, showFunctionName, updateFunctionName });
		clientBehaviorSupport.addZone(clientId, showFunctionName, updateFunctionName);
	}

	public void linkZone(String linkId, String elementId, Link eventLink) {
		Method method = getMethod("linkZone", String.class, String.class, Link.class);
		recordMethodCall(method, new Object[] { linkId, elementId, eventLink });
		clientBehaviorSupport.linkZone(linkId, elementId, eventLink);
	}

	// =============================================

	private Method getMethod(String name, Class<?>... parameterTypes) {
		return super.getMethod(ClientBehaviorSupport.class, name, parameterTypes);
	}
}
