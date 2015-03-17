package com.t70.vector.client.config;

import com.t70.vector.client.constant.MappingType;
import com.t70.vector.client.utils.StringUtils;

public abstract class MemberConfig
{
    private String m_name;

    private String m_mappingName;

    private MappingType m_urlMapType;

    private boolean m_isPrimaryKey;

    protected MemberConfig()
    {
    }

    protected void initName(String name)
    {
        m_name = name;
        m_mappingName = name;
    }

    public String getName()
    {
        return m_name;
    }

    public String getMappingName()
    {
        return m_mappingName;
    }

    public MappingType getUrlMapType()
    {
        return m_urlMapType;
    }

    public boolean isPrimaryKey()
    {
        return m_isPrimaryKey;
    }

    public MemberConfig makeOptionalUrlSegment()
    {
        m_urlMapType = MappingType.OPTIONAL_URL_SEGMENT;
        return this;
    }

    public MemberConfig makeUrlSegment()
    {
        m_urlMapType = MappingType.URL_SEGMENT;
        return this;
    }

    public MemberConfig makeGetParameter()
    {
        m_urlMapType = MappingType.GET;
        return this;
    }

    public MemberConfig makePostParameter()
    {
        m_urlMapType = MappingType.POST;
        return this;
    }

    public MemberConfig makeGetOrPostParameter()
    {
        m_urlMapType = MappingType.GET_OR_POST;
        return this;
    }

    public MemberConfig makeCookieParameter()
    {
        m_urlMapType = MappingType.COOKIE;
        return this;
    }

    public MemberConfig makeHeaderParameter()
    {
        m_urlMapType = MappingType.HEADER;
        return this;
    }

    public MemberConfig makePrimaryKey()
    {
        m_isPrimaryKey = true;
        return this;
    }

    public MemberConfig mapTo(String urlName)
    {
        if (StringUtils.isNullOrWhitespace(urlName))
            throw new IllegalArgumentException();

        m_mappingName = urlName;
        return this;
    }

    abstract public Object getValue(final Object o);

    abstract public void setValue(Object o, Object value);
}
