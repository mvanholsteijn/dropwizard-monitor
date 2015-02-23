package com.xebia.dropwizard.monitor;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * just redirect request on / to /public/index.html
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HtmlPageResource {

  @GET
  public Response index() {
	URI uri = UriBuilder.fromPath("/public/index.html").build();
	Response response = Response.seeOther(uri).build();
	throw new WebApplicationException(response);
  }
}
