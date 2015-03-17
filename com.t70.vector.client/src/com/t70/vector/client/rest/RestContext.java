package com.t70.vector.client.rest;

import com.t70.vector.client.interfaces.*;
import com.t70.vector.client.config.*;

/**
 * Base for a REST context.
 * 
 * Supports configuration a set of models to a set of URLs and then getting a RestRepository for those models when
 * needed.
 * 
 * @author Bryce Simonds
 */
abstract class RestContext implements IContext
{
    private RestModelBuilder m_modelBuilder;

    private IVectorConfig m_mainConfig;

    protected RestContext(IVectorConfig config)
    {
        m_mainConfig = config;

        m_modelBuilder = null;
    }

    private void createModels()
    {
        if (m_modelBuilder != null)
            return;

        m_modelBuilder = new RestModelBuilder();
        onModelCreating(m_modelBuilder);

        m_modelBuilder.checkEntries();
    }

    protected abstract void onModelCreating(RestModelBuilder modelBuilder);

    public <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls)
    {
        return getRepository(cls, null);
    }

    public <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls, Object args)
    {
        createModels();

        args = (args == null) ? new Object() : args;

        EntityConfig<TEntity> entityConfig;

        try
        {
            entityConfig = m_modelBuilder.getConfig(cls);
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return null;
        }

        entityConfig.validateHasNeededKeys(args);

        return new RestRepository<TEntity>(
            m_mainConfig,
            entityConfig,
            args);
    }
}
