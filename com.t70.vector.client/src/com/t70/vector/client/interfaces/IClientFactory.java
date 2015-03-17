package com.t70.vector.client.interfaces;

import javax.ws.rs.client.WebTarget;

public interface IClientFactory
{
    public WebTarget create(IVectorConfig config);
}
