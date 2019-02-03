package st.examples.testserv;

import java.util.concurrent.TimeUnit;

public class StpServiceImpl implements StpService {

    private UserRepository userRepo = new UserRepository();
    private EventSender sender = new EventSender();

    @Override
    public void storeXp(String userId, int xp) {
        synchronized (userId) {
            User user = userRepo.get(userId);
            int prevXp = user.xp;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.xp = prevXp + xp;
            System.out.println("Get user: " + Thread.currentThread().getName());
            System.out.println("User old: " + prevXp + " User new: " + user.xp);
//          userRepo.save(user);
            sender.sendEvent(user, prevXp, user.xp);
        }
    }

    @Override
    public int getLevel(String userId) {
        User user = userRepo.get(userId);
        return StpToLevelUtils.getLevel(user.xp);
    }

    @Override
    public int getXp(String userId) {
        User user = userRepo.get(userId);
        return user.xp;
    }

    @Override
    public void subscribe(String userId, String listenerId) {
        synchronized (userId) {
            User user = userRepo.get(userId);
            user.subscribers.add(new SubscriberListener(listenerId));
//          userRepo.save(user);
        }
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }
}
