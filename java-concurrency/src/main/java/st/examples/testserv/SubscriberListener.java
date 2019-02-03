package st.examples.testserv;

import java.util.ArrayList;
import java.util.List;

public class SubscriberListener {
    String id;
    List<String> levelsFired = new ArrayList<>();

    public SubscriberListener(String id) {
        this.id = id;
    }
}
