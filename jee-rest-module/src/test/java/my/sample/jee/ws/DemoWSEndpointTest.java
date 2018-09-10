package my.sample.jee.ws;

import my.sample.jee.ws.api.ws.DemoWSEndpoint;
import my.sample.jee.ws.xto.DemoResponse;
import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.apache.openejb.junit.jee.config.PropertyFile;
import org.apache.openejb.testing.EnableServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@EnableServices(value = "jaxrs")
@PropertyFile("openejb-junit.properties")
@RunWith(EJBContainerRunner.class)
public class DemoWSEndpointTest extends AbstractBaseTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DemoWSEndpointTest.class);
    private DemoWSEndpoint endpoint = createRestServiceClient();


    @Before
    public void setup() {

    }

    @Test
    public void testGetDemos() {
        try {
            DemoResponse demos = endpoint.getDemo();
            assertNotNull(demos);
        } catch (RuntimeException e) {
            logger.error(e.getLocalizedMessage(), e);
            fail(e.getLocalizedMessage());
        }
    }
}
