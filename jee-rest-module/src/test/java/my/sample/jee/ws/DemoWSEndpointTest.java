package my.sample.jee.ws;

import my.sample.jee.ws.xto.DemoResponse;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.apache.openejb.junit.jee.config.PropertyFile;
import org.apache.openejb.testing.EnableServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

@EnableServices(value = "jaxrs")
@PropertyFile("openejb-junit.properties")
@RunWith(EJBContainerRunner.class)
public class DemoWSEndpointTest extends AbstractBaseTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DemoWSEndpointTest.class);

    @Before
    public void setup() {

    }

    @Test
    public void testGetDemos() {
        try {
            WebClient client = createWebClient("/demos");
            DemoResponse demos = client.get(DemoResponse.class);
            assertNotNull(demos);
        } catch (RuntimeException e) {
            logger.error(e.getLocalizedMessage(), e);
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testPostDemo() {
        try {
            WebClient client = createWebClient("/demos");

            Date myDate = new Date();
            DemoResponse post = new DemoResponse("ich-bin", myDate);

            DemoResponse demos = client.post(post, DemoResponse.class);
            assertNotNull(demos);
            assertEquals(myDate.toInstant().truncatedTo(ChronoUnit.SECONDS), demos.getTheDate().toInstant().truncatedTo(ChronoUnit.SECONDS));
        } catch (RuntimeException e) {
            logger.error(e.getLocalizedMessage(), e);
            fail(e.getLocalizedMessage());
        }
    }
}
