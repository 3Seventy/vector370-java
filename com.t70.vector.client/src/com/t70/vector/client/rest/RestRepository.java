package com.t70.vector.client.rest;

import java.util.*;

import javax.ws.rs.core.Response;

import com.t70.vector.client.config.*;

import com.t70.vector.client.constant.MethodType;

import com.t70.vector.client.interfaces.*;

import com.t70.vector.client.utils.*;

public class RestRepository<TEntity> implements IRepository<TEntity>
{
    /**
     * List of arguments that are required for all HTTP calls based on the TEntity class.
     */
    private final Map<String, Object> m_requiredArgs;

    /**
     * Global client configuration details.
     */
    private final IVectorConfig m_mainConfig;

    /**
     * Configuration details for the TEntity class.
     */
    private final EntityConfig<TEntity> m_entityConfig;

    /**
     * The type object representing the TEntity class.
     */
    private final Class<TEntity> m_class;

    /**
     * Constructor is only callable from inside the library.
     * 
     * @param mainConfig
     * @param entityConfig
     * @param requiredArgs
     */
    protected RestRepository(
        final IVectorConfig mainConfig,
        final EntityConfig<TEntity> entityConfig,
        final Object requiredArgs)
    {
        m_mainConfig = mainConfig;
        m_entityConfig = entityConfig;
        m_requiredArgs = ReflectionUtils.objectToMap(requiredArgs);

        m_class = m_entityConfig.getConfiguredClass();
    }

    private Response executeRequestFor(
        MethodType method,
        Object requestBody,
        Map<String, Object> extraArgs,
        boolean nullOn404)
    {
        RequestBuilder builder = new RequestBuilder(m_mainConfig, m_entityConfig);

        RequestWrapper request = builder.buildRequest(
            method,
            requestBody,
            m_requiredArgs,
            extraArgs);

        return request.execute();
    }

    private void adjustObjectKeys(TEntity outObject, final TEntity sourceObject)
    {
        // Adjust the caller's object to have the correct keys.
        for (MemberConfig key : m_entityConfig.getPrimaryKeys())
        {
            Object value;

            value = key.getValue(sourceObject);
            key.setValue(outObject, value);
        }
    }

    @Override
    public <TKey> TEntity get(final TKey id)
    {
        return get(id, false);
    }

    @Override
    public <TKey> TEntity get(final TKey id, Boolean throwIfNotFound)
    {
        if (id == null)
            throw new NullPointerException();

        final List<MemberConfig> primaryKeys = m_entityConfig.getPrimaryKeys();

        int count = primaryKeys.size();
        HashMap<String, Object> extraArgs = new HashMap<String, Object>();

        if (count == 1)
        {
            MemberConfig key = primaryKeys.get(0);
            extraArgs.put(key.getName(), id);
        }
        else
        {
            Map<String, Object> fields = ReflectionUtils.objectToMap(id);

            for (Map.Entry<String, Object> kvp : fields.entrySet())
            {
                String key = kvp.getKey();
                Object val = kvp.getValue();

                if (val != null)
                    extraArgs.put(key, val);
            }
        }

        Response response = executeRequestFor(MethodType.GET, null, extraArgs, !throwIfNotFound);

        return (response != null) ? response.readEntity(m_class) : null;
    }

    @Override
    public Collection<TEntity> getAll()
    {
        Response response = executeRequestFor(MethodType.GET, null, null, false);

        Collection<TEntity> list;

        if (response != null)
        {
            list = response.readEntity(m_entityConfig.getModelClassInfo().getCollectionClass());
        }
        else
        {
            list = new ArrayList<TEntity>();
        }

        return list;
    }

    @Override
    public void add(TEntity entity)
    {
        if (entity == null)
            throw new NullPointerException();

        Response response = executeRequestFor(MethodType.POST, entity, null, false);

        TEntity result = (response != null) ? response.readEntity(m_class) : null;

        adjustObjectKeys(entity, result);
    }

    @Override
    public void addRange(Collection<TEntity> entities)
    {
        if (entities == null)
            throw new NullPointerException();

        for (TEntity e : entities)
            add(e);
    }

    @Override
    public void update(TEntity entity)
    {
        if (entity == null)
            throw new NullPointerException();

        Map<String, Object> extra = ReflectionUtils.objectToMap(entity);

        Response response = executeRequestFor(MethodType.PUT, entity, extra, false);

        TEntity result = (response != null) ? response.readEntity(m_class) : null;

        adjustObjectKeys(entity, result);
    }

    @Override
    public void updateRange(Collection<TEntity> entities)
    {
        if (entities == null)
            throw new NullPointerException();

        for (TEntity e : entities)
            update(e);
    }

    @Override
    public void delete(TEntity entity)
    {
        if (entity == null)
            throw new NullPointerException();

        Map<String, Object> extra = ReflectionUtils.objectToMap(entity);

        executeRequestFor(MethodType.DELETE, null, extra, true);
    }

    @Override
    public void deleteAll(Collection<TEntity> entities)
    {
        if (entities == null)
            throw new NullPointerException();

        for (TEntity e : entities)
            delete(e);
    }
}
