package st.examples.testserv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserRepository {

    //    private ConcurrentMap store = new ConcurrentSkipListMap();
//    private Map<String, User> store = new HashMap<>();
    private ConcurrentMap<String, User> store = new ConcurrentHashMap<>();

    public User get(String userId) {
        User user = store.get(userId);
        if (user == null) {
            throw new IllegalStateException(String.format("User with id: %s is not found", userId));
        }
        return user;
    }

    public void save(User user) {
        store.put(user.id, user);
    }
}
