package com.t70.vector.client.utils;

import java.net.URLEncoder;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.t70.vector.client.config.*;
import com.t70.vector.client.constant.*;
import com.t70.vector.client.interfaces.*;
import com.t70.vector.client.rest.DefaultRestClientFactory;

public class RequestBuilder
{
    private interface IPredicate<T>
    {
        boolean test(T s);
    }

    private interface IAction<T1, T2>
    {
        void execute(T1 a1, T2 a2);
    }

    private class StringNullOrWhitespacePredicate implements IPredicate<String>
    {
        public boolean test(String s)
        {
            return StringUtils.isNullOrWhitespace(s);
        }
    }

    private static final Logger s_logger = LogManager.getLogger(RequestBuilder.class.getName());

    private final IVectorConfig m_mainConfig;

    private final EntityConfig<?> m_entityConfig;

    public RequestBuilder(
        final IVectorConfig mainConfig,
        final EntityConfig<?> config)
    {
        m_mainConfig = mainConfig;
        m_entityConfig = config;
    }

    private void mapFields(
        MappingType type,
        IPredicate<String> filter,
        Map<String, String> args,
        IAction<String, String> a)
    {
        final Collection<MemberConfig> members = m_entityConfig.getAllMembers();

        for (MemberConfig mc : members)
        {
            if (mc.getUrlMapType() != type)
                continue;

            String valueStr = "";

            if (args.containsKey(mc.getName()))
                valueStr = args.get(mc.getName());

            if ((filter != null) && filter.test(valueStr))
                continue;

            a.execute(mc.getMappingName(), valueStr);
        }
    }

    private String urlEncode(String s)
    {
        try
        {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (Exception e)
        {
            // Ignore error
            return s;
        }
    }

    private String buildGetParameters(MethodType method, Map<String, String> args)
    {
        final List<String> result = new ArrayList<String>();

        mapFields(
            MappingType.GET,
            new StringNullOrWhitespacePredicate(),
            args,
            new IAction<String, String>()
            {
                public void execute(String name, String value)
                {
                    value = urlEncode(value);
                    result.add(String.format("%s=%s", name, value));
                }
            });

        if ((method == MethodType.POST) || (method == MethodType.PUT) || (method == MethodType.GET))
        {
            mapFields(
                MappingType.GET_OR_POST,
                new StringNullOrWhitespacePredicate(),
                args,
                new IAction<String, String>()
                {
                    public void execute(String name, String value)
                    {
                        value = urlEncode(value);
                        result.add(String.format("%s=%s", name, value));
                    }
                });
        }

        return StringUtils.join("&", result);
    }

    private String subUriSegments(String uri, Map<String, String> args)
        throws IllegalArgumentException
    {
        for (MemberConfig mc : m_entityConfig.getAllMembers())
        {
            MappingType urlMapping = mc.getUrlMapType();

            if ((urlMapping != MappingType.URL_SEGMENT) && (urlMapping != MappingType.OPTIONAL_URL_SEGMENT))
                continue;

            String name = mc.getName();
            String valueStr;

            if (!args.containsKey(name))
            {
                if (urlMapping == MappingType.URL_SEGMENT)
                {
                    String msg = String.format("The %s URL segment is required", name);
                    throw new IllegalArgumentException(msg);
                }
                else
                    valueStr = "";
            }
            else
            {
                valueStr = args.get(name);
            }

            String mappingName = mc.getMappingName();

            uri = uri.replace("{" + mappingName + "}", urlEncode(valueStr));
        }

        return uri;
    }

    private String makeAbsoluteUri(String uri)
    {
        if (!m_entityConfig.IsAbsoluteUri)
        {
            if (uri.charAt(0) == '/')
                uri = uri.substring(1);

            if (uri.charAt(uri.length() - 1) == '/')
                uri = uri.substring(0, uri.length() - 1);

            String baseUrl = m_mainConfig.getBaseUrl();

            uri = String.format("%s/%s", baseUrl, uri);
        }

        return uri;
    }

    private String buildUri(MethodType method, Map<String, String> args)
    {
        String urlString = m_entityConfig.UriFormatStr;

        urlString = subUriSegments(urlString, args);
        urlString = makeAbsoluteUri(urlString);

        String getParams = buildGetParameters(method, args);

        if (!StringUtils.isNullOrWhitespace(getParams))
            urlString += "?" + getParams;

        return urlString;
    }

    private void addHeaderValues(final Invocation.Builder request, Map<String, String> args)
    {
        mapFields(
            MappingType.HEADER,
            new StringNullOrWhitespacePredicate(),
            args,
            new IAction<String, String>()
            {
                public void execute(String name, String value)
                {
                    request.header(name, value);
                }
            });
    }

    private void addCookieValues(final Invocation.Builder request, Map<String, String> args)
    {
        mapFields(
            MappingType.COOKIE,
            new StringNullOrWhitespacePredicate(),
            args,
            new IAction<String, String>()
            {
                public void execute(String name, String value)
                {
                    request.cookie(name, value);
                }
            });
    }

    private WebTarget getClient()
    {
        // TODO: Allow this to be injected.

        IClientFactory factory = new DefaultRestClientFactory();
        return factory.create(m_mainConfig);
    }

    private Map<String, String> mergeArguments(Map<String, Object> args, Map<String, Object> extra)
    {
        Map<String, String> rval = new HashMap<String, String>();

        for (Map.Entry<String, Object> kvp : args.entrySet())
        {
            String key = kvp.getKey();
            Object value = kvp.getValue();

            if (value == null)
                value = "";

            rval.put(key, value.toString());
        }

        if (extra != null)
        {
            for (Map.Entry<String, Object> kvp : extra.entrySet())
            {
                String key = kvp.getKey();
                Object value = kvp.getValue();

                if (value == null)
                    value = "";

                rval.put(key, value.toString());
            }
        }

        return rval;
    }

    public RequestWrapper buildRequest(
        MethodType method,
        Object requestBody,
        Map<String, Object> args,
        Map<String, Object> extraArgs)
    {
        Map<String, String> arguments = mergeArguments(args, extraArgs);

        String uri = buildUri(method, arguments);

        s_logger.debug("{}: {}", method, uri);

        Invocation.Builder builder = getClient()
            .path(uri)
            .request(MediaType.APPLICATION_JSON_TYPE);

        addHeaderValues(builder, arguments);
        addCookieValues(builder, arguments);

        String userAgent = m_mainConfig.getUserAgent();

        if (StringUtils.isNullOrWhitespace(userAgent))
        {
            String version = VectorClientProperties.readManifestVersion();

            userAgent = String.format("3Seventy SDK.JAVA %s", version);
        }

        builder.header("User-Agent", userAgent);

        return new RequestWrapper(method, requestBody, builder);
    }
}
