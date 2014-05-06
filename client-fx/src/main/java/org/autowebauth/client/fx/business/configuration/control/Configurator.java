package org.autowebauth.client.fx.business.configuration.control;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.configuration.boundary.ConfigurationBoundary;
import org.slf4j.Logger;

/**
 * 
 * This class provides configurations through {@code CDI producer} concept. It
 * stores, removes and load application configs.
 * 
 * <pre>
 * &#064;Inject
 * private String url; // Loads config with key com.package.Yourclass.url
 * 
 * &#064;Inject
 * private int port; // Loads config with key com.package.Yourclass.port
 * </pre>
 * 
 * Your're also able to use the {@code Annotation} {@link Configuration} to load
 * configuration with some custom qualifier.
 * 
 * <pre>
 * &#064;Inject
 * &#064;Configuration(&quot;database-url&quot;)
 * private String url; // Loads config 'database-url'
 * </pre>
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class Configurator
{
    private final static int UNKNOWN_INT = -1;

    private org.autowebauth.client.fx.business.configuration.entity.Configuration configuration;

    @Inject
    private Logger log;

    @Inject
    ConfigurationBoundary boundary;

    @PostConstruct
    public void loadConfiguration()
    {
        this.configuration = this.boundary.loadConfiguration();
    }

    @PreDestroy
    public void shutdown()
    {
        this.boundary.save(this.configuration);
    }

    @Produces
    public String getString(InjectionPoint ip)
    {
        if (ip.getAnnotated().isAnnotationPresent(Configuration.class))
        {
            String key = ip.getAnnotated().getAnnotation(Configuration.class)
                    .value();
            return getEntry(key);
        }
        else
        {
            return getConfigurationForMember(ip);
        }
    }

    @Produces
    public int getInteger(InjectionPoint ip)
    {
        final String returned;
        if (ip.getAnnotated().isAnnotationPresent(Configuration.class))
        {
            String key = ip.getAnnotated().getAnnotation(Configuration.class)
                    .value();
            returned = getEntry(key);
        }
        else
        {
            returned = getConfigurationForMember(ip);
        }
        if (returned != null)
        {
            return Integer.parseInt(returned);
        }
        else
        {
            return UNKNOWN_INT;
        }
    }

    public String getEntry(String key)
    {
        String result = this.configuration.get(key);
        if (result == null)
        {
            this.log.error("Requested configuration for key {} was not found.",
                    key);
        }
        return result;
    }

    public void removeEntry(String key)
    {
        this.configuration.remove(key);
    }

    public String addEntry(String key, String value)
    {
        return this.configuration.put(key, value);
    }

    private String getConfigurationForMember(InjectionPoint ip)
    {
        String s = this.configuration.get(ip.getMember().getName());
        if (s == null)
        {
            this.log.error(
                    "Requested configuration for member {} was not found.",
                    ip.getMember());
        }
        return s;
    }

}
