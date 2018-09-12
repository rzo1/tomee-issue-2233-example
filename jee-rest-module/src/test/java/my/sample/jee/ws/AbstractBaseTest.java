package my.sample.jee.ws;

import my.sample.jee.ws.api.ws.DemoWSEndpoint;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.johnzon.jaxrs.jsonb.jaxrs.JsonbJaxrsProvider;
import org.apache.johnzon.mapper.access.FieldAccessMode;
import org.apache.openejb.OpenEjbContainer;
import org.apache.openejb.junit.jee.EJBContainerRule;
import org.apache.openejb.junit.jee.InjectRule;
import org.apache.openejb.junit.jee.config.Properties;
import org.apache.openejb.junit.jee.config.Property;
import org.apache.openejb.junit.jee.resources.TestResource;
import org.apache.openejb.server.cxf.rs.johnzon.TomEEJsonbProvider;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.naming.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    protected WebClient createWebClient(String method) {
        TomEEJsonbProvider provider = new TomEEJsonbProvider();
        provider.setAccessMode(new FieldAccessMode(true,true));
        provider.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        provider.setLocale(Locale.GERMANY);

        List<JsonbJaxrsProvider> providers = new ArrayList<>();
        providers.add(provider);

        WebClient httpClient = WebClient.create("http://localhost:" + PORT + CONTEXT_PATH, providers);
        httpClient.path(DemoWSEndpoint.API_PATH + method);
        httpClient.accept(MediaType.APPLICATION_JSON_TYPE);
        httpClient.type(MediaType.APPLICATION_JSON_TYPE);
        return httpClient;
    }

}
