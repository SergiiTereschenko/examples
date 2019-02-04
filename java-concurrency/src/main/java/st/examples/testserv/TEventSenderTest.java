package st.examples.testserv;

import org.junit.Test;

public class TEventSenderTest {

    EventSender sender = new EventSender();

    @Test
    public void testStoreXp() {
        String id = "1";
        User user = new User(id, 0);
//        SubscriberListener sub1 = new SubscriberListener("2");
//        SubscriberListener sub2 = new SubscriberListener("3");
//        user.subscribers.add(sub1);
//        user.subscribers.add(sub2);
//        sender.sendEvent(user, 15, 150);
//        Assert.assertEquals(1, sub1.levelsFired.size());
//        Assert.assertEquals("1", sub1.levelsFired.get(0));
//        Assert.assertArrayEquals(sub1.levelsFired.toArray(), sub2.levelsFired.toArray());
    }
}
