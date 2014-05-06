package org.autowebauth.client.fx.business.logging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Produces a logger.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class LogProvider
{
    @Produces
    public Logger produceLogger(InjectionPoint ip)
    {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }

}
