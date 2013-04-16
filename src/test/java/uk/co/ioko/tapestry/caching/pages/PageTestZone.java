package uk.co.ioko.tapestry.caching.pages;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;

public class PageTestZone {
	
	@Property
	private String value;
	
	@Component
	private Zone zoneTest;
	
	@BeginRender
	public void init() {
		value="[Initial value]";
	}
	
	@OnEvent(value=EventConstants.ACTION, component="linkZone")
	public Object onActionFromLinkZone() {
		value="[ActionLink component clicked]";
		
		return zoneTest.getBody();
	}
}
