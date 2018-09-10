package my.sample.jee.ws.api.ws;

import my.sample.jee.ws.xto.DemoResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DemoWSEndpoint {

    String API_PATH = "/api/v0.1";

    /**
     * Gets all {@link DemoResponse demo resources} as a collection.
     * @return A list of {@link DemoResponse} containing the resources.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("demos")
    DemoResponse getDemo();
    //Sample: http://localhost:8282/api/v0.1/demos

}
