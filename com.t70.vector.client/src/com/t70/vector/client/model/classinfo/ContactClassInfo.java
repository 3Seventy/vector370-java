package com.t70.vector.client.model.classinfo;

import java.util.Collection;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Contact;

/**
 * Details for the {@code Contact} model class.
 * 
 * @see Contact
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class ContactClassInfo implements IModelClassInfo<Contact>
{
    @Override
    public Contact create()
    {
        return new Contact();
    }

    @Override
    public Class<Contact> getModelClass()
    {
        return Contact.class;
    }

    @Override
    public GenericType<Collection<Contact>> getCollectionClass()
    {
        return new GenericType<Collection<Contact>>()
        {
        };
    }
}
