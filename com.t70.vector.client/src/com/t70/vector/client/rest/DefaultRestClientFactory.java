package com.t70.vector.client.rest;

import javax.ws.rs.client.*;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.t70.vector.client.interfaces.*;

public class DefaultRestClientFactory implements IClientFactory
{

    public WebTarget create(IVectorConfig config)
    {
        String userName = config.getUserName();
        String password = config.getPassword();

        HttpAuthenticationFeature authFeature = HttpAuthenticationFeature
            .basicBuilder()
            .credentials(userName, password)
            .build();

        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register(authFeature);
        clientConfig.register(RequestLoggingFilter.class);
        clientConfig.register(JacksonJsonProvider.class);

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target("");

        return target;
    }
}
