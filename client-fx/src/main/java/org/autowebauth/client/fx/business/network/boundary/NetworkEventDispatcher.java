package org.autowebauth.client.fx.business.network.boundary;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.annotation.WlanConnected;
import org.autowebauth.client.fx.business.network.annotation.WlanDisconnected;
import org.autowebauth.client.fx.infrastrucutre.extensions.annotation.Startup;
import org.autowebauth.core.api.network.AutoWebAuth;
import org.autowebauth.core.api.network.provider.NetworkProvider;
import org.autowebauth.core.api.network.provider.conn.ConnectionAction;
import org.autowebauth.core.api.network.provider.conn.ConnectionEvent;
import org.autowebauth.core.api.network.provider.conn.NetworkListener;
import org.slf4j.Logger;

/**
 * {@code Dispatcher} for wireless lan connection activities.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
@Startup
public class NetworkEventDispatcher implements NetworkListener
{

    @Inject
    private Logger log;

    /**
     * Provider API to get information about network activities.
     */
    private NetworkProvider networkProvider;

    /**
     * CDI Events to notify javaFX about network activities.
     */
    @Inject
    private Event<ConnectionEvent> networkEvents;

    public NetworkEventDispatcher()
    {
    }

    @PostConstruct
    public void init()
    {
        this.networkProvider = AutoWebAuth.getFactory().getProvider();
        this.networkProvider.registerListener(this);
    }

    @Override
    public void onConnectionActivity(ConnectionEvent event)
    {
        if (event.getAction() == ConnectionAction.CONNECTED)
        {
            this.networkEvents.select(new AnnotationLiteral<WlanConnected>()
            {
            }).fire(event);
        }
        else if (event.getAction() == ConnectionAction.DISCONNECTED)
        {
            this.networkEvents.select(new AnnotationLiteral<WlanDisconnected>()
            {
            }).fire(event);
        }
    }

    public NetworkProvider getNetworkProvider()
    {
        return this.networkProvider;
    }

    public void setNetworkProvider(NetworkProvider networkProvider)
    {
        this.networkProvider = networkProvider;
    }

}
