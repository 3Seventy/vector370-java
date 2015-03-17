package com.t70.vector.client.utils;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.t70.vector.client.constant.MethodType;

public class RequestWrapper
{
    private MethodType m_method;

    private Object m_requestBody;

    private Invocation.Builder m_request;

    RequestWrapper(MethodType method, Object requestBody, Invocation.Builder request)
    {
        m_method = method;
        m_requestBody = requestBody;
        m_request = request;
    }

    public Response execute()
    {
        Entity<Object> body = null;

        if (m_requestBody != null)
        {
            body = Entity.entity(m_requestBody, MediaType.APPLICATION_JSON_TYPE);
        }

        Response rval = null;

        switch (m_method)
        {
        case GET:
            rval = m_request.get();
            break;

        case POST:
            rval = m_request.post(body);
            break;

        case PUT:
            rval = m_request.put(body);
            break;

        case DELETE:
            rval = m_request.delete();
            break;
        }

        return rval;
    }
}
