package st.examples.testserv;

public interface StpService {

    void storeXp(String userId, int xp);
    int getLevel(String userId);
    int getXp(String userId);
    void subscribe(String forUserId, String listenerId);

}
