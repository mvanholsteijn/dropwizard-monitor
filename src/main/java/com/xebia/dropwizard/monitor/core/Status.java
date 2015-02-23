package com.xebia.dropwizard.monitor.core;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Status {
	private final String key;
	private final String release;
	private final String message;

	public Status(int port) {
		String hostname = null;
		try {
			InetAddress localMachine = InetAddress.getLocalHost();
			hostname = localMachine.getHostName();
		} catch (UnknownHostException e) {
			hostname = "unknown-host-name";
		}

		this.key = String.format("%s:%s",hostname, port);
		this.release = System.getenv("RELEASE") != null ? System.getenv("RELEASE") : "unknown";
		this.message = String.format("Hello world from %s", this.release);
	}

	public String getKey() {
		return key;
	}

	public String getRelease() {
		return release;
	}
	public String getMessage() {
		return message;
	}
}

