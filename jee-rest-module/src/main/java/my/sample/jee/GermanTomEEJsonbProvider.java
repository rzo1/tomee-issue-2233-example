package my.sample.jee;

import org.apache.johnzon.jaxrs.jsonb.jaxrs.JsonbJaxrsProvider;
import org.apache.johnzon.mapper.access.FieldAccessMode;
import org.apache.openejb.server.cxf.rs.johnzon.TomEEJsonbPropertyVisibilityStrategy;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

@Provider
// This will sort the Provider to be after CXF defaults. Check org.apache.cxf.jaxrs.provider.ProviderFactory.sortReaders()
@Produces({"application/json", "application/*+json"})
@Consumes({"application/json", "application/*+json"})
public class GermanTomEEJsonbProvider<T> extends JsonbJaxrsProvider<T> {

    public GermanTomEEJsonbProvider() {
        super();
        config.withPropertyVisibilityStrategy(new TomEEJsonbPropertyVisibilityStrategy());
        config.withLocale(Locale.GERMANY);
        config.withDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.GERMANY);
        config.withNullValues(false);
        config.setProperty("johnzon.accessMode", new FieldAccessMode(true, true));
    }

}
