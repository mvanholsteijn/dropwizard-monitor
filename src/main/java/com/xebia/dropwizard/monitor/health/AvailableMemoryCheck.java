package com.xebia.dropwizard.monitor.health;

import com.codahale.metrics.health.HealthCheck;

public class AvailableMemoryCheck extends HealthCheck {
	private final int minFree;

	public AvailableMemoryCheck(int minFree) {
		this.minFree = minFree;
	}

	@Override
    protected Result check() throws Exception {
    	long heapFreeSize = Runtime.getRuntime().freeMemory();
    	if(heapFreeSize < minFree) {
    		return Result.unhealthy(String.format("less than %d Kbytes free, only %d Kb available", minFree/1024, heapFreeSize/1024));
    	} else {
            return Result.healthy(String.format("more than %d Kbytes free, total %d Kb available", minFree/1024, heapFreeSize/1024));
        }
    }
}