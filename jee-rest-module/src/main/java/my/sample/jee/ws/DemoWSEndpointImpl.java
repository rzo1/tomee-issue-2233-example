package my.sample.jee.ws;

import my.sample.jee.ws.api.ws.DemoWSEndpoint;
import my.sample.jee.ws.xto.DemoResponse;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.util.Date;

@Stateless
@Path(DemoWSEndpoint.API_PATH)
public class DemoWSEndpointImpl implements DemoWSEndpoint {

    public DemoResponse getDemo() {
        return new DemoResponse("1", new Date());
    }

    @Override
    public DemoResponse postDemo(DemoResponse demoResponse) {
        demoResponse.setName("RETURN");
        return demoResponse;
    }
}
