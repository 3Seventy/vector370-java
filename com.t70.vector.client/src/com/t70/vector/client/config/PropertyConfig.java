package com.t70.vector.client.config;

import com.t70.vector.client.utils.Property;

class PropertyConfig extends MemberConfig
{
    /**
     * Details of the property that this {@code PropertyConfig} is for.
     */
    private Property m_propertyInfo;

    protected PropertyConfig(Property propertyInfo)
    {
        super();

        m_propertyInfo = propertyInfo;
        initName(m_propertyInfo.getName());
    }

    /**
     * For reference for now.
     * 
     * @return The reflected property info.
     */
    public Property getPropertyInfo()
    {
        return m_propertyInfo;
    }

    public Object getValue(final Object o)
    {
        return m_propertyInfo.getValue(o);
    }

    public void setValue(Object o, Object value)
    {
        m_propertyInfo.setValue(o, value);
    }
}
