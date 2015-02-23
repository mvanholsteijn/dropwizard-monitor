package com.xebia.dropwizard.monitor;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.xebia.dropwizard.monitor.health.AvailableMemoryCheck;
import com.xebia.dropwizard.monitor.resource.StatusResource;

public class MonitorService extends Application<MonitorConfiguration> {

	public static void main(String[] args) throws Exception {
		new MonitorService().run(args);
	}

	@Override
	public void initialize(Bootstrap<MonitorConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets", "/public", "index.html"));
	}

	private int getListenPort(Configuration configuration) {
		int httpPort = -1;
		DefaultServerFactory serverFactory = (DefaultServerFactory) configuration.getServerFactory();
		for (ConnectorFactory connector : serverFactory.getApplicationConnectors()) {
		    if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
		        httpPort = ((HttpConnectorFactory) connector).getPort();
		        break;
		    }
		}
		
		if(httpPort == -1) {
			SimpleServerFactory simpleServerFactory = (SimpleServerFactory) configuration.getServerFactory();
			HttpConnectorFactory connector = (HttpConnectorFactory) simpleServerFactory.getConnector();
			if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
			    httpPort = connector.getPort();
			}
		}
		return httpPort;
	}
	
	@Override
	public void run(MonitorConfiguration configuration,
			Environment environment) {
		environment.jersey().register(new StatusResource(getListenPort(configuration)));
		environment.jersey().register(new HtmlPageResource());
		environment.healthChecks().register("available-memory",
				new AvailableMemoryCheck(1024 * 1024));

	}

}
