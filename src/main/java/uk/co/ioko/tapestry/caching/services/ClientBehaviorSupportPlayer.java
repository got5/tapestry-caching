package uk.co.ioko.tapestry.caching.services;

import java.util.List;

import org.apache.tapestry5.services.ClientBehaviorSupport;

import uk.co.ioko.tapestry.caching.services.support.MethodCall;

public class ClientBehaviorSupportPlayer extends SupportPlayer {

	private ClientBehaviorSupport clientBehaviorSupport;

	public ClientBehaviorSupportPlayer(ClientBehaviorSupport clientBehaviorSupport) {
		this.clientBehaviorSupport = clientBehaviorSupport;
	}

	public void playbackMethodCalls(List<MethodCall> methodCalls) {
		super.playbackMethodCalls(clientBehaviorSupport, methodCalls);
	}
}
