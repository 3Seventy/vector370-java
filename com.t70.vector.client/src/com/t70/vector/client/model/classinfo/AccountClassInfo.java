package com.t70.vector.client.model.classinfo;

import java.util.*;

import javax.ws.rs.core.GenericType;

import com.t70.vector.client.model.Account;

/**
 * Details for the {@code Account} model class.
 * 
 * @see Account
 * @see IModelClassInfo
 * 
 * @author Bryce Simonds
 */
public class AccountClassInfo implements IModelClassInfo<Account>
{
    @Override
    public Account create()
    {
        return new Account();
    }

    @Override
    public Class<Account> getModelClass()
    {
        return Account.class;
    }

    @Override
    public GenericType<Collection<Account>> getCollectionClass()
    {
        return new GenericType<Collection<Account>>()
        {
        };
    }
}
