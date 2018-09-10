package my.sample.jee.ws;

import my.sample.jee.ws.api.ws.DemoWSEndpoint;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.openejb.OpenEjbContainer;
import org.apache.openejb.junit.jee.EJBContainerRule;
import org.apache.openejb.junit.jee.InjectRule;
import org.apache.openejb.junit.jee.config.Properties;
import org.apache.openejb.junit.jee.config.Property;
import org.apache.openejb.junit.jee.resources.TestResource;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.naming.Context;

/**
 * @author rz
 */
/*
 * Previously this was done via an openejb junit properties file -> causing mysterious behaviour...
 * this way: works! (mwiesner)
 */
@Properties(
        @Property(
                key = OpenEjbContainer.OPENEJB_EJBCONTAINER_CLOSE,
                value = OpenEjbContainer.OPENEJB_EJBCONTAINER_CLOSE_SINGLE))
public class AbstractBaseTest {

    @ClassRule
    public static final EJBContainerRule CONTAINER_RULE = new EJBContainerRule();

    protected static final Integer PORT = 4204;
    protected static final String CONTEXT_PATH = "/jee-rest-module";

    static {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
    }

    @Rule
    public final InjectRule injectRule = new InjectRule(this, CONTAINER_RULE);

    @TestResource
    private Context ctx;

    protected DemoWSEndpoint createRestServiceClient() {
        return JAXRSClientFactory.create("http://localhost:" + PORT + CONTEXT_PATH + DemoWSEndpoint.API_PATH,
                DemoWSEndpoint.class);
    }

}
