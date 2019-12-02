package com.st.interviewtasks.xpservice.playtika;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TXpServiceTest {

    XpServiceImpl stpService = new XpServiceImpl();
    ConcurrentLinkedQueue<SubscriberListener> linkedQueue = new ConcurrentLinkedQueue<>();

//
//    @Test
//    public void testStoreXp() {
//        int MAX_SIZE = 123;
//        String id1 = "123";
//        String id2 = "123";
//        String id3 = MAX_SIZE + "";
//        String id4 = "123123";
//        String id5 = id1 + id2;
//        System.out.println("12: " + (id1 == id2));
//        System.out.println("23: " + (id2 == id3));
//        id3 = id3.intern();
//        System.out.println("23*: " + (id2 == id3));
//        System.out.println("13: " + (id1 == id3));
//        System.out.println("45: " + (id4 == id5));
//
//        long userId = 123;
//        String id = "123";
////        stpService.getUserRepo().save(new User(id, 0));
//        int xp  = 99;
//        stpService.storeXp(userId, xp);
//        User user = stpService.getUserRepo().get(id);
//        assertEquals(xp, user.xp);
//    }

    @Test
    public void testConcurrentAdd() throws InterruptedException {
        stpService.subscribe(linkedQueue::add);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Integer> consistentXpValues = new ArrayList<>();

        for (long k = 1; k <= 10; k++) {
            executorService.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
                for (long i = 1; i <= 10; i++) {
                    stpService.storeXp(i, 1);
                    consistentXpValues.add(stpService.getXp(i));
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, TimeUnit.SECONDS);

        assertEquals(100, linkedQueue.size());
        assertEquals(100, consistentXpValues.size());
//        for (int i = 1; i <= 100; i++) {
//            assertEquals(i, consistentXpValues.get(i - 1).intValue());
//        }
    }
}
