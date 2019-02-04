package st.examples.testserv;

import java.util.function.Consumer;

public interface StpService {

    void storeXp(long userId, int xp);
    int getLevel(String userId);
    int getXp(long userId);
//    void subscribe(String forUserId, String listenerId);
    void subscribe(Consumer<SubscriberListener> consumer);

}
