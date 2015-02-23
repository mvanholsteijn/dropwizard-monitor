package com.xebia.dropwizard.monitor;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitorConfiguration extends Configuration {
    @JsonProperty
    private int minimumNumberOfBytesFree = 1024 * 1024;


    public int getMinimumNumberOfBytesFree() {
        return minimumNumberOfBytesFree;
    }
}
