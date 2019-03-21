package st.examples.xpservice;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

public class XpServiceImpl implements XpService {

    //task playtika

    private ConcurrentMap<String, User> store = new ConcurrentHashMap<>();
    Set<Consumer> listeners = Collections.synchronizedSet(new HashSet<Consumer>());
    Set<String> strings = Collections.synchronizedSet(new HashSet<String>());

    @Override
    public void storeXp(long userId, int xp) {
        String id = (userId + "").intern();
        synchronized (id) {
            User user = getOrCreateUser(id);
            int prevXp = user.xp;
            user.xp = prevXp + xp;
            System.out.println("Get user: " + Thread.currentThread().getName());
            System.out.println("UserId: " + id + "; prevXp: " + prevXp + "; newXp: " + user.xp);

            sendEvent(userId, prevXp, user.xp);
        }
    }

    private User getOrCreateUser(String id) {
        User user = store.get(id);
        if (user == null) {
            user = new User(id, 0);
            store.put(id, user);
        }
        return user;
    }

    @Override
    public int getLevel(String userId) {
        User user = getOrCreateUser(userId);
        return XpToLevelUtils.getLevel(user.xp);
    }

    @Override
    public int getXp(long userId) {
        String id = userId + "";
        User user = getOrCreateUser(id);
        return user.xp;
    }

//    @Override
//    public void subscribe(String userId, String listenerId) {
//        synchronized (userId) {
//            User user = getOrCreateUser(userId);
//            user.subscribers.add(new SubscriberListener(listenerId));
////          userRepo.save(user);
//        }
//    }

    @Override
    public void subscribe(Consumer<SubscriberListener> consumer) {
        listeners.add(consumer);
    }

    public Set<Consumer> getListeners() {
        return listeners;
    }

    public void sendEvent(long userId, int prevXp, int newXp) {
        List<Integer> levels = XpToLevelUtils.getLevels(prevXp, newXp);
        levels.forEach(l -> {
            for (Consumer consumer : listeners) {
                consumer.accept(SubscriberListener.builder().userId(userId).userXp(newXp).build());
//                consumer.accept(new SubscriberListener(userId, newXp));
            }
        });
    }

    public Map<String, User> getUserRepo() {
        return store;
    }
}
