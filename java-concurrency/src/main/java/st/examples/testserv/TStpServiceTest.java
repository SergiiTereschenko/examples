package st.examples.testserv;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TStpServiceTest {

    StpServiceImpl stpService = new StpServiceImpl();
    ConcurrentLinkedQueue<SubscriberListener> linkedQueue = new ConcurrentLinkedQueue<>();


    @Test
    public void testStoreXp() {
        int MAX_SIZE = 123;
        String id1 = "123";
        String id2 = "123";
        String id3 = MAX_SIZE + "";
        String id4 = "123123";
        String id5 = id1 + id2;
        System.out.println("12: " + (id1 == id2));
        System.out.println("23: " + (id2 == id3));
        id3 = id3.intern();
        System.out.println("23*: " + (id2 == id3));
        System.out.println("13: " + (id1 == id3));
        System.out.println("45: " + (id4 == id5));

        long userId = 123;
        String id = "123";
//        stpService.getUserRepo().save(new User(id, 0));
        int xp  = 99;
        stpService.storeXp(userId, xp);
        User user = stpService.getUserRepo().get(id);
        Assert.assertEquals(xp, user.xp);
    }

    @Test
    public void testConcurrentAdd() throws InterruptedException {
        int MAX_SIZE = 123;

        List<Long> users = getUsers(1);
//        stpService.getUserRepo().save(new User(id, 0));
        stpService.subscribe(linkedQueue::add);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Integer> consistentStpValues = new ArrayList<>();

        for (long k = 1; k <= 10; k++) {
            executorService.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
                for (long i = 1; i <= 1; i++) {
                    stpService.storeXp(i, 1);
                    consistentStpValues.add(stpService.getXp(i));
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, TimeUnit.SECONDS);

        Map<String, User> user = stpService.getUserRepo();
        Set<Consumer> listeners = stpService.getListeners();

        Assert.assertEquals(100, consistentStpValues.size());
//        for (int i = 1; i <= MAX_SIZE * MAX_SIZE; i++) {
//            assertEquals(i, consistentStpValues.get(i - 1).intValue());
//        }
    }

    private List<Long> getUsers(long number) {
        List<Long> users = new ArrayList<>();
        for (long i = 0; i < number; i++) {
            users.add(i);
        }
        return users;
    }

//    private void subscribeListeners(StpService service) {
//        service.subscribe(linkedQueue::add);
//
//    }

}
