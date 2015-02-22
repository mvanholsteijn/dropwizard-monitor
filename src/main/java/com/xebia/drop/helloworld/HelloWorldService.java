package com.xebia.drop.helloworld;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.xebia.drop.helloworld.health.AvailableMemoryCheck;
import com.xebia.drop.helloworld.resource.StatusResource;

public class HelloWorldService extends Application<HelloWorldConfiguration> {

	public static void main(String[] args) throws Exception {
		new HelloWorldService().run(args);
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets", "/public", "index.html"));
	}

	@Override
	public void run(HelloWorldConfiguration configuration,
			Environment environment) {
		environment.jersey().register(new StatusResource(8080));
		environment.healthChecks().register("available-memory",
				new AvailableMemoryCheck(1024 * 1024));

	}

}
