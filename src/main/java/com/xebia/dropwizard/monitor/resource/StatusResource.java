package com.xebia.dropwizard.monitor.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.xebia.dropwizard.monitor.core.Status;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusResource {

    int portNumber;

    public StatusResource(int portNumber) {
    	this.portNumber = portNumber;
    }

    @GET
    @Timed
    public Status getStatus() {
        return new Status(portNumber);
    }
}
