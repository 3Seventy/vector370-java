import java.io.IOException;
import java.util.*;

import com.t70.vector.client.model.Account;
import com.t70.vector.client.rest.T70Context;
import com.t70.vector.client.interfaces.*;

public class AccountExample
{
    private static final int ACCOUNT_ID = 0; // TODO: Fill in with your account ID
    private static final int PARENT_ID = 0;

    /**
     * Main entry point
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // The user name and password are pulled from your application's config.properties file by default.
        IContext context = new T70Context();

        detailsExample(context);
        getAllExample(context);

        // The following examples make changes to the accounts, use with care.
        // createAccountExample(context);
        // updateAccountExample(context);

        System.out.println();
        System.out.println("Done, press a key to quit.");

        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            // We just ignore the exception.
        }
    }

    public static void showAccountDetails(Account account)
    {
        if (account == null)
        {
            System.out.printf("Account was not found");
            return;
        }

        System.out.printf("ID: %d\r\n", account.getId());
        System.out.printf("Parent: %d\r\n", account.getParentId());
        System.out.printf("Name: %s\r\n", account.getName());
        System.out.printf("Status: %s\r\n", account.getStatus());
        System.out.printf("Created: %s\r\n", account.getCreated());
    }

    public static void createAccountExample(IContext context)
    {
        // You can create a subaccount of the highest level account you have access to and its subaccounts.

        IRepository<Account> repo = context.getRepository(Account.class);

        Account a = new Account();
        a.setParentId(PARENT_ID);
        a.setName("TestSubAccount");

        repo.add(a);

        System.out.printf("Added account %s: %s", a.getId(), a.getName());
    }

    public static void detailsExample(IContext context)
    {
        System.out.println("Testting get single account:");

        IRepository<Account> repo = context.getRepository(Account.class);

        Account account = repo.get(ACCOUNT_ID);

        showAccountDetails(account);
        System.out.println();
    }

    public static void getAllExample(IContext context)
    {
        IRepository<Account> repo = context.getRepository(Account.class);

        Collection<Account> accounts = repo.getAll();

        for (Account account : accounts)
        {
            showAccountDetails(account);
            System.out.println();
        }
    }

    public static void updateAccountExample(IContext context)
    {
        IRepository<Account> repo = context.getRepository(Account.class);

        Account account = new Account();
        account.setId(ACCOUNT_ID);
        account.setName("UpdatedAccountTest");

        repo.update(account);

        Account updatedAccount = repo.get(ACCOUNT_ID);

        showAccountDetails(updatedAccount);
        System.out.println();

    }
}
