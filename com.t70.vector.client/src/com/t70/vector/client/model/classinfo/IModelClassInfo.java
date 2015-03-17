package com.t70.vector.client.model.classinfo;

import java.util.*;

import javax.ws.rs.core.GenericType;

/**
 * A factory interface that generates class types for a given model class.
 * 
 * This interface is used for correctly generating the entities returned by the JSON requests.
 *
 * @param <TEntity>
 *            The base entity type we represent.
 * 
 * @author Bryce Simonds
 */
public interface IModelClassInfo<TEntity>
{
    /**
     * Creates a new instance of the model class.
     */
    public TEntity create();

    /**
     * Gets the Class object for the model class.
     */
    public Class<TEntity> getModelClass();

    /**
     * Gets a GenericType representing a collection of the model class.
     */
    public GenericType<Collection<TEntity>> getCollectionClass();
}
