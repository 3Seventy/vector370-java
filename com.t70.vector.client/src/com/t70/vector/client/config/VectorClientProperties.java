package com.t70.vector.client.config;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.jar.*;

import com.t70.vector.client.interfaces.IVectorConfig;
import com.t70.vector.client.utils.StringUtils;

/**
 * Used for reading the properties from the config.properties file that the Vector 370 client uses for making requests.
 */
public class VectorClientProperties implements IVectorConfig
{
    private final static String TARGET_SPEC_TITLE = "3Seventy Vector Client";

    private final static String PROPERTY_PREFIX = "com.t70.vector.client.";

    private final static String UNKNOWN_VERSION = "[UNKNOWN]";

    private final static String DEFAULT_URL = "https://api.3seventy.com/api/v2.0";

    /**
     * Base URL where requests are sent to.
     */
    private String m_baseUrl;

    /**
     * The user name to use when generating the HTTP auth header.
     */
    private String m_userName;

    /**
     * The password to send through the HTTP auth header.
     */
    private String m_password;

    /**
     * The maximum number of attempts we will make when trying to send a REST request.
     */
    private Integer m_maxTries;

    /**
     * The timeout value in seconds we will wait for a response from the server.
     */
    private Integer m_httpTimeout;

    /**
     * The user agent string to use when sending HTTP requests.
     */
    private String m_userAgent;

    /**
     * Cached configuration details.
     */
    private static VectorClientProperties m_config = new VectorClientProperties();

    /**
     * Gets the configuration object which was read from the properties file.
     *
     * If the configuration has not yet been read from the properties file, then a new configuration object is created
     * and the properties will be read. Otherwise the information is cached.
     *
     * @return The configuration read from the properties file.
     */
    public static IVectorConfig getConfig()
    {
        if (m_config == null)
            m_config = new VectorClientProperties();

        return m_config;
    }

    /**
     * Default constructor
     */
    private VectorClientProperties()
    {
        load();
    }

    /**
     * Gets the base URL where requests are sent to.
     */
    public String getBaseUrl()
    {
        return m_baseUrl;
    }

    /**
     * Gets the user name to use in HTTP auth headers.
     */
    public String getUserName()
    {
        return m_userName;
    }

    /**
     * Gets the password to use in the HTTP auth header.
     */
    public String getPassword()
    {
        return m_password;
    }

    /**
     * Gets the string to use for the user agent header.
     */
    public String getUserAgent()
    {
        return m_userAgent;
    }

    /**
     * Gets the maximum number of attempts we will make when trying to send a REST request.
     */
    public Integer getMaxTries()
    {
        return m_maxTries;
    }

    /**
     * The timeout value in seconds we will wait for a response from the server.
     */
    public Integer getHttpTimeout()
    {
        return m_httpTimeout;
    }

    public static String readManifestVersion()
    {
        Enumeration<URL> resources;

        try
        {
            resources = VectorClientProperties.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
        }
        catch (IOException e)
        {
            return UNKNOWN_VERSION;
        }

        while (resources.hasMoreElements())
        {
            try
            {
                Manifest manifest = new Manifest(resources.nextElement().openStream());

                Attributes attrs = manifest.getMainAttributes();

                Object specName = attrs.get(Attributes.Name.SPECIFICATION_TITLE);
                Object specVersion = attrs.get(Attributes.Name.SPECIFICATION_VERSION);

                if ((specName == null) || !specName.toString().equals(TARGET_SPEC_TITLE))
                    continue;

                if (specVersion == null)
                    continue;

                String test = specVersion.toString();

                if (!StringUtils.isNullOrWhitespace(test))
                    return test;
            }
            catch (IOException e)
            {
                continue;
            }
        }

        return UNKNOWN_VERSION;
    }

    /**
     * Loads the configuration.
     */
    private void load()
    {
        Properties properties = new Properties();

        InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        try
        {
            properties.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Required properties

        m_userName = properties.getProperty(PROPERTY_PREFIX + "userName");

        m_password = properties.getProperty(PROPERTY_PREFIX + "password");

        // Properties that have default values.

        // Default to https://services.3seventy.com/api/v2.0
        m_baseUrl = properties.getProperty(
            PROPERTY_PREFIX + "baseUrl",
            DEFAULT_URL);

        // Default to 3 times
        m_maxTries = Integer.parseInt(properties.getProperty(
            PROPERTY_PREFIX + "maxTries",
            "3"));

        // Default to 3 minutes
        m_httpTimeout = Integer.parseInt(properties.getProperty(
            PROPERTY_PREFIX + "httpTimeout",
            "180"));

        String version = readManifestVersion();

        m_userAgent = properties.getProperty(
            PROPERTY_PREFIX + "userAgent",
            "3Seventy SDK.JAVA " + version);
    }
}
