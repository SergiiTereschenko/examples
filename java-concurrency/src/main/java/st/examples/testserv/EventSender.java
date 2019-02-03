package st.examples.testserv;

import java.util.List;

public class EventSender {

    public void sendEvent(User user, int prevXp, int newXp) {
        List<Integer> levels = StpToLevelUtils.getLevels(prevXp, newXp);
        levels.forEach(l -> {
            for (SubscriberListener subscriber : user.subscribers) {
                subscriber.levelsFired.add(l.toString());
            }
        });
    }
}
