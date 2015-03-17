package com.t70.vector.client.interfaces;

/**
 * Interface for a context for getting repositories on an entity.
 * 
 * This abstraction is provided as a hook for generating unit test mocks.
 * 
 * @author Bryce Simonds
 */
public interface IContext
{
    /**
     * Gets an IRepository for the specified entity.
     * 
     * <p>
     * If the entity has a set of required arguments, then they need to be supplied with the version of this function
     * which takes the {@code args} parameter.
     * </p>
     * <p>
     * E.g.: For the content:
     * 
     * <pre>
     * IRepository&lt;Content&gt; contentRepo = context.Repository&lt;Content&gt;(new Object()
     * {
     *     int accountId = 5 
     * });
     * </pre>
     * 
     * </p>
     * <p>
     * Note that any required parameters which are mapped to a different HTTP name, will use the name in the model, and
     * not the mapped HTTP name.
     * </p>
     * <p>
     * E.g. the following will result in an exception:
     * 
     * <pre>
     * IRepository&lt;Content&gt; contentRepo = context.Repository&lt;Content&gt;(new Object()
     * { 
     *     int accountId = 5 
     * });
     * </pre>
     * 
     * </p>
     * 
     * @param cls
     *            The type of entity to get the repository of.
     * 
     * @return A repository for getting, updating and adding items.
     * 
     * @throws ClassNotFoundException
     *             Thrown if a class is not configured.
     */
    public <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls);

    /**
     * Gets an IRepository for the specified entity.
     * 
     * <p>
     * If the entity has a set of required arguments, then they need to be supplied with this function here.
     * </p>
     * <p>
     * E.g.: For the content:
     * 
     * <pre>
     * IRepository&lt;Content&gt; contentRepo = context.Repository&lt;Content&gt;(new Object()
     * {
     *     int accountId = 5 
     * });
     * </pre>
     * 
     * </p>
     * <p>
     * Note that any required parameters which are mapped to a different HTTP name, will use the name in the model, and
     * not the mapped HTTP name.
     * </p>
     * <p>
     * E.g. the following will result in an exception:
     * 
     * <pre>
     * IRepository&lt;Content&gt; contentRepo = context.Repository&lt;Content&gt;(new Object()
     * { 
     *     int accountId = 5 
     * });
     * </pre>
     * 
     * </p>
     * 
     * @param cls
     *            The type of entity to get the repository of.
     * @param args
     *            An object who's fields/properties will be mapped to the required URL arguments.
     * 
     * @return A repository for getting, updating and adding items.
     * 
     * @throws ClassNotFoundException
     *             Thrown if a class is not configured.
     */
    public <TEntity> IRepository<TEntity> getRepository(Class<TEntity> cls, Object args);
}
