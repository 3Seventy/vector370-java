package com.t70.vector.client.interfaces;

import java.util.*;

/**
 * Interface for persistent data repositories.
 *
 * This abstraction is provided as a hook for generating unit test mocks.
 * 
 * @param <TEntity>
 *            The type of entity being stored/retrieved.
 */
public interface IRepository<TEntity>
{
    /**
     * Gets a specific entity that matches the supplied ID.
     * 
     * For objects with a compound key <code>id</code> can be a complex object who's properties are each part of the
     * compound key.
     *
     * <pre>
     * Subscription sub = contactSubRepo.Get(new Object()
     * {
     *     int ContactId = 5;
     *     int SubscriptionId = 10;
     * });
     * </pre>
     * 
     * If there's a single key, there is no need for boxing:
     * 
     * <pre>
     * Contact contact = contactRepo.Get(5);
     * </pre>
     * 
     * @param id
     *            The ID of the object being pulled.
     * 
     * @param <TKey>
     *            The type of object that specifies the key data.
     * 
     * @return NULL if not found, or a valid entity object.
     */
    public <TKey> TEntity get(final TKey id);

    /**
     * Gets a specific entity that matches the supplied ID.
     * 
     * For objects with a compound key <code>id</code> can be a complex object who's properties are each part of the
     * compound key.
     *
     * <pre>
     * Subscription sub = contactSubRepo.Get(new Object()
     * {
     *     int ContactId = 5;
     *     int SubscriptionId = 10;
     * });
     * </pre>
     * 
     * If there's a single key, there is no need for boxing:
     * 
     * <pre>
     * Contact contact = contactRepo.Get(5);
     * </pre>
     * 
     * @param id
     *            The ID to search for.
     * 
     * @param throwIfNotFound
     *            Set to true if an error should be thrown if an object is not found. If this is false, then NULL is
     *            returned when an object is not found.
     * 
     * @return The object if found, or NULL if not. If throwIfNotFound is set to true then an exception will be thrown
     *         when the object cannot be located.
     */
    public <TKey> TEntity get(final TKey id, Boolean throwIfNotFound);

    /**
     * Returns a list of all objects available in the repository.
     */
    public Collection<TEntity> getAll();

    /**
     * Adds an object to the repository.
     * 
     * @param entity
     *            The entity to add
     */
    public void add(TEntity entity);

    /**
     * Adds a list of objects to the repository.
     * 
     * @param entities
     *            The list of objects to add
     */
    public void addRange(Collection<TEntity> entities);

    /**
     * Updates an object in the repository.
     * 
     * @param entity
     *            The object to update
     */
    public void update(TEntity entity);

    /**
     * Updates a list of objects in the repository.
     * 
     * @param entities
     *            The list of objects to add
     */
    public void updateRange(Collection<TEntity> entities);

    /**
     * Removes an object from the repository.
     * 
     * @param entity
     *            The object ot delete
     */
    public void delete(TEntity entity);

    /**
     * Removes a list of objects from the repository.
     * 
     * @param entities
     *            The list of objects to remove.
     */
    public void deleteAll(Collection<TEntity> entities);
}
