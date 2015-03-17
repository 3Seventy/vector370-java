package com.t70.vector.client.config;

import java.lang.reflect.Field;
import java.util.*;

import com.t70.vector.client.constant.MappingType;

import com.t70.vector.client.model.classinfo.IModelClassInfo;

import com.t70.vector.client.utils.Property;
import com.t70.vector.client.utils.ReflectionUtils;

public class EntityConfig<TEntity>
{
    private Map<String, MemberConfig> m_members;

    /**
     * The class to which this is a configuration for.
     */
    private Class<TEntity> m_configuredClass;

    /**
     * A prebuilt list of keys.
     */
    private List<MemberConfig> m_primaryKeys = null;

    /**
     * Entity class information for reflection.
     */
    private IModelClassInfo<TEntity> m_modelClassInfo;

    /**
     * The format string for getting this entity.
     */
    public String UriFormatStr;

    /**
     * Gets or sets if the UriFormatString is for an absolute URI or a relative URI.
     * 
     * If set, then the URI in the UriFormatString is considered to be fully formed without the need of having the base
     * URI prepended to it.
     */
    public boolean IsAbsoluteUri;

    /**
     * Constructor
     * 
     * @param modelClassInfo
     */
    public EntityConfig(IModelClassInfo<TEntity> modelClassInfo)
    {
        m_configuredClass = modelClassInfo.getModelClass();
        m_modelClassInfo = modelClassInfo;

        buildMembers();
    }

    /**
     * Setup the initial list of properties.
     */
    private void buildMembers()
    {
        m_members = new HashMap<String, MemberConfig>();

        Map<String, Field> fields = ReflectionUtils.getAllDeclaredFields(m_configuredClass);

        for (Map.Entry<String, Field> kvp : fields.entrySet())
        {
            FieldConfig fieldConfig = new FieldConfig(kvp.getValue());

            m_members.put(kvp.getKey(), fieldConfig);
        }

        // By doing properties last, we will override any fields with their getters and setter methods.
        Map<String, Property> props = ReflectionUtils.getAllDeclaredProperties(m_configuredClass);

        for (Map.Entry<String, Property> kvp : props.entrySet())
        {
            PropertyConfig propConfig = new PropertyConfig(kvp.getValue());

            m_members.put(kvp.getKey(), propConfig);
        }
    }

    public Class<TEntity> getConfiguredClass()
    {
        return m_configuredClass;
    }

    /**
     * Returns the class which can be used to construct new instances of the model.
     */
    public IModelClassInfo<TEntity> getModelClassInfo()
    {
        return m_modelClassInfo;
    }

    /**
     * Returns a list of field configuration values.
     */
    public final Collection<MemberConfig> getAllMembers()
    {
        return m_members.values();
    }

    public final List<MemberConfig> getPrimaryKeys()
    {
        if (m_primaryKeys == null)
        {
            m_primaryKeys = new ArrayList<MemberConfig>(m_members.size());

            for (MemberConfig mc : getAllMembers())
            {
                if (mc.isPrimaryKey())
                    m_primaryKeys.add(mc);
            }
        }

        return m_primaryKeys;
    }

    public EntityConfig<TEntity> uriFormat(String uriFormat)
    {
        return uriFormat(uriFormat, false);
    }

    public EntityConfig<TEntity> uriFormat(String uriFormat, Boolean isAbsolute)
    {
        UriFormatStr = uriFormat;
        IsAbsoluteUri = isAbsolute;

        return this;
    }

    public MemberConfig forMember(String name)
    {
        if (!m_members.containsKey(name))
        {
            String msg = String.format("Unable to locate member %s", name);
            throw new IndexOutOfBoundsException(msg);
        }

        return m_members.get(name);
    }

    public void validateHasNeededKeys(Object args)
    {
        if (args == null)
        {
            String msg = String.format("The \"args\" parameter cannot be null");
            throw new NullPointerException(msg);
        }

        Class<?> cls = args.getClass();

        Map<String, Field> fields = ReflectionUtils.getAllDeclaredFields(cls);
        Map<String, Property> props = ReflectionUtils.getAllDeclaredProperties(cls);

        Collection<MemberConfig> members = getAllMembers();

        for (MemberConfig mc : members)
        {
            String name = mc.getName();

            if (mc.getUrlMapType() != MappingType.URL_SEGMENT)
                continue;

            if (fields.containsKey(name))
                continue;

            if (props.containsKey(name))
                continue;

            throw new RuntimeException("Missing required key " + name);
        }
    }
}
