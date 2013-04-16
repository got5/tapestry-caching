package uk.co.ioko.tapestry.caching.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.ioko.tapestry.caching.services.support.MethodCall;

public abstract class SupportPlayer {

	private static final Logger logger = LoggerFactory.getLogger(SupportPlayer.class);

	public void playbackMethodCalls(Object target, List<MethodCall> methodCalls) {
		if (methodCalls == null) {
			// WOO-HOO! nothing to playback
			return;
		}
		// iterate through the method calls and use reflection to call them on RenderSupport
		for (MethodCall methodCall : methodCalls) {
			Class clazz = target.getClass();
			Object[] methodParams = methodCall.getParams();
			try {
				Method method = clazz.getMethod(methodCall.getMethodName(), methodCall.getParamTypes());
				// Consider adding a (Object) here.
				// It is getting confused between Object[] and Object...
				method.invoke(target, methodParams);
			} catch (NoSuchMethodException e) {
				logger.error("{}", e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				logger.error("{}", e);
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				logger.error("{}", e);
				throw new RuntimeException(e);
			}
		}
	}
}
