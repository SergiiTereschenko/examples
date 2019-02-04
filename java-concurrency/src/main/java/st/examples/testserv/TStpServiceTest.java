package st.examples.testserv;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TStpServiceTest {

    StpServiceImpl stpService = new StpServiceImpl();

    @Test
    public void testStoreXp() {
        String id = "123";
        stpService.getUserRepo().save(new User(id, 0));
        int xp  = 99;
        stpService.storeXp(id, xp);
        User user = stpService.getUserRepo().get(id);
        Assert.assertEquals(xp, user.xp);
    }

    @Test
    public void testConcurrentAdd() throws InterruptedException {
        String id = "123";
        int MAX_SIZE = 20;
        stpService.getUserRepo().save(new User(id, 0));
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIZE);
        List<Integer> consistentStpValues = new ArrayList<>();

        for (int k = 0; k < MAX_SIZE; k++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < MAX_SIZE; i++) {
                    stpService.storeXp(id, 1);
                    consistentStpValues.add(stpService.getXp(id));
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        User user = stpService.getUserRepo().get(id);
        Assert.assertEquals(MAX_SIZE * MAX_SIZE, consistentStpValues.size());
        for (int i = 1; i <= MAX_SIZE * MAX_SIZE; i++) {
            assertEquals(i, consistentStpValues.get(i - 1).intValue());
        }

    }

}
