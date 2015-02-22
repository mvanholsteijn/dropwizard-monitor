package com.xebia.drop.helloworld;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelloWorldConfiguration extends Configuration {
    @JsonProperty
    private int minimumNumberOfBytesFree = 1024 * 1024;


    public int getMinimumNumberOfBytesFree() {
        return minimumNumberOfBytesFree;
    }
}
