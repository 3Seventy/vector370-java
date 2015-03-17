import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.t70.vector.client.constant.ScheduleType;
import com.t70.vector.client.interfaces.IContext;
import com.t70.vector.client.interfaces.IRepository;
import com.t70.vector.client.model.EventPushCampaign;
import com.t70.vector.client.rest.T70Context;

public class PushExample
{
    /*
     * NOTE: Please review the various constants before you run this code and fill in the needed items with appropriate
     * values.
     */

    /**
     * TODO: Fill in with your account ID.
     * 
     * <p>
     * You should have received your account ID when you first signed up with Vector.
     * </p>
     */
    private static final int ACCOUNT_ID = 0;

    /**
     * TODO: Fill in with the basic campaign you have already setup.
     * 
     * <p>
     * See the SetupCampaign project for more details.
     * </p>
     * <p>
     * NOTE: You may also setup a campaign using the Portal.
     * </p>
     */
    private static final int CAMPAIGN_ID = 0;

    /**
     * TODO: Fill in with the phone number you wish to send to.
     * 
     * <p>
     * NOTE: Phone numbers must have the international dialing prefix. (For the U.S. and Canada that is "+1")
     * </p>
     */
    private static final String SEND_TO = "+1<your_phone_number_with_area_code_here>";

    /**
     * This is the short code you wish to send from.
     * 
     * <p>
     * Channel ID 11 is the 370370 short code.
     * </p>
     */
    private static final int CHANNEL_ID = 11;

    public static void main(String[] args)
    {
        // The user name and password are pulled from your application's config.properties file by default.
        IContext context = new T70Context();

        IRepository<EventPushCampaign> repo = context.getRepository(EventPushCampaign.class, new Object()
        {
            public int AccountId = ACCOUNT_ID;
        });

        int eventId = pushExample(repo);
        getDetailsExample(repo, eventId);

    }

    private static String JoinList(String seperator, Collection<?> c)
    {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for (Object item : c)
        {
            if (item == null)
                continue;

            if (!isFirst)
                sb.append(seperator);
            else
                isFirst = false;

            sb.append(item.toString());
        }

        return sb.toString();

    }

    private static void displayEvent(EventPushCampaign event)
    {
        System.out.printf("Event ID: %d\r\n", event.getId());
        System.out.printf("Channels: %s\r\n", JoinList(", ", event.getChannelIds()));
        System.out.printf("Targets: %s\r\n", JoinList(", ", event.getTargets()));
        System.out.println();
    }

    private static void getDetailsExample(IRepository<EventPushCampaign> eventRepo, int eventId)
    {
        EventPushCampaign e = eventRepo.get(eventId);

        displayEvent(e);
    }

    private static int pushExample(IRepository<EventPushCampaign> eventRepo)
    {
        List<Integer> channelIds = new ArrayList<Integer>();
        List<String> targets = new ArrayList<String>();

        channelIds.add(CHANNEL_ID);
        targets.add(SEND_TO);

        EventPushCampaign event = new EventPushCampaign();
        event.setCampaignId(CAMPAIGN_ID);
        event.setChannelIds(channelIds);
        event.setTargets(targets);
        event.setScheduleType(ScheduleType.NOW);

        eventRepo.add(event);

        System.out.printf("Sent campaign %s to channel(s) %s\r\n",
            CAMPAIGN_ID,
            JoinList(", ", channelIds));

        return event.getId();

    }
}
