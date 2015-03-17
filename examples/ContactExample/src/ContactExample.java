import com.t70.vector.client.interfaces.IContext;
import com.t70.vector.client.interfaces.IRepository;
import com.t70.vector.client.model.Contact;
import com.t70.vector.client.model.ContactAttribute;
import com.t70.vector.client.model.ContactSubscription;
import com.t70.vector.client.rest.T70Context;

public class ContactExample
{
    private static final int ACCOUNT_ID = 0; // TODO: Fill in with your account ID
    private static final int CONTACT_ID = 0; // TODO: Fill in with an existing contact ID

    private static final String PHONE_NUMBER = "+1"; // TODO: Fill in with a valid phone number
    private static final String EMAIL_ADDRESS = "";

    private static final String SEARCH_PATTERN = "+1512*";

    public static void main(String[] args)
    {
        // The user name and password are pulled from your application's config.properties file by default.
        IContext context = new T70Context();

        // createContactExample(context);
        contactDetailsExample(context);
    }

    private static void displayContact(Contact contact)
    {
        if (contact == null)
        {
            System.out.println("Contact not found.");
            return;
        }

        System.out.printf("Id: %d\r\n", contact.getId());
        System.out.printf("AccountId: %d\r\n", contact.getAccountId());
        System.out.printf("PhoneNumber: %s\r\n", contact.getPhoneNumber());
        System.out.printf("Email: %s\r\n", contact.getEmail());
        System.out.println("Attributes:");

        for (ContactAttribute attr : contact.getAttributes())
            System.out.printf("  %s: %s\r\n", attr.getName(), attr.getValue());

        System.out.println("Subscriptions:");

        for (ContactSubscription sub : contact.getSubscriptions())
        {
            System.out.printf(
                "  %d: SMS: %s  EMAIL: %s\r\n",
                sub.getSubscriptionId(),
                sub.isSmsEnabled(),
                sub.isEmailEnabled());
        }

        System.out.printf("Created: %s\r\n", contact.getCreated());
        System.out.printf("Modified: %s\r\n", contact.getModified());
        System.out.println();
    }

    private static void createContactExample(IContext context)
    {
        IRepository<Contact> repo = context.getRepository(Contact.class, new Object()
        {
            public int AccountId = ACCOUNT_ID;
        });

        Contact contact = new Contact();
        contact.setPhoneNumber(PHONE_NUMBER);
        contact.setEmail(EMAIL_ADDRESS);

        repo.add(contact);

        System.out.println("Added contact:");
        displayContact(contact);
    }

    private static void contactDetailsExample(IContext context)
    {
        IRepository<Contact> repo = context.getRepository(Contact.class, new Object()
        {
            public int AccountId = ACCOUNT_ID;
        });

        Contact contact = repo.get(CONTACT_ID);

        System.out.println("Got contact:");
        displayContact(contact);
    }

    private static void updateContactExample(IContext context)
    {
        IRepository<Contact> repo = context.getRepository(Contact.class, new Object()
        {
            public int AccountId = ACCOUNT_ID;
        });

        Contact contact = new Contact();
        contact.setId(CONTACT_ID);
        contact.setPhoneNumber(PHONE_NUMBER);
        contact.setEmail(EMAIL_ADDRESS);

        repo.update(contact);

        Contact updatedContact = repo.get(CONTACT_ID);

        System.out.println("Updated contact:");
        displayContact(updatedContact);

    }

    private static void deleteContactExample(IContext context)
    {
        IRepository<Contact> repo = context.getRepository(Contact.class, new Object()
        {
            public int AccountId = ACCOUNT_ID;
        });

        Contact contact = new Contact();
        contact.setId(CONTACT_ID);

        repo.delete(contact);

        // Note that contact's are a SoftDelete item, so they are marked as deleted instead of actually removed.
        Contact deletedContact = repo.get(CONTACT_ID);

        System.out.println("Deleted contact:");
        displayContact(deletedContact);
    }
}
