package uk.co.ioko.tapestry.caching.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.ioko.tapestry.caching.services.support.MethodCall;

public abstract class SupportRecorder {

	private static final Logger logger = LoggerFactory.getLogger(SupportRecorder.class);

	private List<MethodCall> methodCalls;

	public List<MethodCall> getMethodCalls() {
		return methodCalls;
	}

	protected void recordMethodCall(Method method, Object... params) {
		MethodCall methodCall = new MethodCall(method.getName(), method.getParameterTypes(), params);
		if (methodCalls == null) {
			methodCalls = new ArrayList<MethodCall>();
		}
		methodCalls.add(methodCall);
	}

	protected Method getMethod(Class clazz, String name, Class<?>... parameterTypes) {
		try {
			return clazz.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			logger.error("{}", e);
			throw new RuntimeException(e);
		}
	}

}
