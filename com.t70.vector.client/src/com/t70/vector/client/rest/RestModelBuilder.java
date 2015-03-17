package com.t70.vector.client.rest;

import java.util.*;

import com.t70.vector.client.config.*;
import com.t70.vector.client.model.classinfo.*;

public class RestModelBuilder
{
    private Map<Class<?>, EntityConfig<?>> m_configs = new HashMap<Class<?>, EntityConfig<?>>();

    private <TEntity> IModelClassInfo<TEntity> getModelClassInfo(Class<TEntity> cls)
        throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Package pkg = cls.getPackage();
        String pkgName = pkg.getName();

        String clsName = cls.getName();

        String modelClassInfoName = clsName.replace(pkgName, pkgName + ".classinfo") + "ClassInfo";

        @SuppressWarnings("unchecked")
        Class<? extends IModelClassInfo<TEntity>> typeCls =
            (Class<? extends IModelClassInfo<TEntity>>)Class.forName(modelClassInfoName);

        IModelClassInfo<TEntity> rval;
        rval = typeCls.newInstance();

        return rval;
    }

    /**
     * Gets the configuration object for a type of entity.
     * 
     * @param cls
     *            The entity type we are attempting to get the configuration for.
     * 
     * @return A configuration object.
     */
    public <TEntity> EntityConfig<TEntity> entity(Class<TEntity> cls)
    {
        return entity(cls, null);
    }

    /**
     * Gets the configuration object for a type of entity.
     * 
     * The {@code modelType} parameter is optional, if {@code null} then this function will attempt to locate the
     * correct {@code IModelType<TEntity>}
     * 
     * @param cls
     *            The entity type we are attempting to get the configuration for.
     * 
     * @param modelClassInfo
     *            An implementation of {@code IModelClassInfo<TEntity>} -- <i>OPTIONAL</i>
     * 
     * @see IModelClassInfo
     */
    @SuppressWarnings("unchecked")
    public <TEntity> EntityConfig<TEntity> entity(Class<TEntity> cls, IModelClassInfo<TEntity> modelClassInfo)
    {
        if (m_configs.containsKey(cls))
        {
            return (EntityConfig<TEntity>)m_configs.get(cls);
        }

        if (modelClassInfo == null)
        {
            try
            {
                modelClassInfo = getModelClassInfo(cls);
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                String msg = String.format("Unable to create or find IModelClassInfo<%s>", cls.getName());

                throw new RuntimeException(msg, e);
            }
        }

        EntityConfig<TEntity> config = new EntityConfig<TEntity>(modelClassInfo);
        m_configs.put(cls, config);

        return config;
    }

    @SuppressWarnings("unchecked")
    <TEntity> EntityConfig<TEntity> getConfig(Class<TEntity> cls)
        throws ClassNotFoundException
    {
        if (m_configs.containsKey(cls))
        {
            return (EntityConfig<TEntity>)m_configs.get(cls);
        }

        String msg = String.format("Unable to locate configuration for %s", cls.getName());
        throw new ClassNotFoundException(msg);
    }

    private void checkSingleEntry(EntityConfig<?> config)
    {

    }

    protected void checkEntries()
    {
        for (Map.Entry<Class<?>, EntityConfig<?>> kvp : m_configs.entrySet())
        {
            checkSingleEntry(kvp.getValue());
        }
    }
}
