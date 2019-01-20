package com.alexander.start;

import org.glassfish.jersey.server.ResourceConfig;

public class StartServer extends ResourceConfig {
	public StartServer() {
		packages("com.alexander");
	}
}
