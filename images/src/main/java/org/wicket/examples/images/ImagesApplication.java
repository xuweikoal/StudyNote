package org.wicket.examples.images;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ImagesApplication extends WebApplication {

	public ImagesApplication() {
		
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		
		return Home.class;
	}
	
	protected void init() {
		super.init();
		
		getSharedResources();
	}

}
