package com.t70.vector.client.config;

import java.lang.reflect.Field;

class FieldConfig extends MemberConfig
{
    /**
     * Details of the field that this {@code PropertyConfig} is for.
     */
    private Field m_fieldInfo;

    protected FieldConfig(Field fieldInfo)
    {
        super();

        m_fieldInfo = fieldInfo;

        initName(m_fieldInfo.getName());
    }

    /**
     * For reference for now.
     * 
     * @return The reflected fields
     */
    public Field getFieldInfo()
    {
        return m_fieldInfo;
    }

    public Object getValue(final Object o)
    {
        boolean accessSave = m_fieldInfo.isAccessible();

        try
        {
            m_fieldInfo.setAccessible(true);
            return m_fieldInfo.get(o);
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        finally
        {
            if (!accessSave)
                m_fieldInfo.setAccessible(false);
        }

        return null;
    }

    public void setValue(Object o, Object value)
    {
        boolean accessSave = m_fieldInfo.isAccessible();

        try
        {
            m_fieldInfo.setAccessible(true);
            m_fieldInfo.set(o, value);
        }
        catch (SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        finally
        {
            if (!accessSave)
                m_fieldInfo.setAccessible(false);
        }
    }
}
