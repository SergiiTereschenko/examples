package st.examples.testserv;

import java.util.ArrayList;
import java.util.List;

public class User {
    String id;
    int xp;
    List<SubscriberListener> subscribers = new ArrayList<>();

    public User(String id, int xp) {
        this.id = id;
        this.xp = xp;
    }
}
