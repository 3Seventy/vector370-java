package com.t70.vector.client.model.classinfo;

import java.util.*;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Channel;

/**
 * Details for the {@code Channel} model class.
 * 
 * @see Channel
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class ChannelClassInfo implements IModelClassInfo<Channel>
{
    @Override
    public Channel create()
    {
        return new Channel();
    }

    @Override
    public Class<Channel> getModelClass()
    {
        return Channel.class;
    }

    @Override
    public GenericType<Collection<Channel>> getCollectionClass()
    {
        return new GenericType<Collection<Channel>>()
        {
        };
    }
}
