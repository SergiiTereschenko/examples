package st.examples.XP;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserRepository {

//    private ConcurrentMap store = new ConcurrentSkipListMap();
    private ConcurrentMap<String, User> store = new ConcurrentHashMap<>();

    public User get(String userId) {
        User user = store.get(userId);
        if (user == null) {
            throw new IllegalStateException(String.format("User with id: %s is not found", userId));
        }
        return user;
    }

    public void register(User user) {
          store.put(user.id, user);
    }


}
