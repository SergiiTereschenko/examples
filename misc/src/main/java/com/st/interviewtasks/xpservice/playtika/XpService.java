package com.st.interviewtasks.xpservice.playtika;

import java.util.function.Consumer;

public interface XpService {

    void storeXp(long userId, int xp);
    int getLevel(String userId);
    int getXp(long userId);
    void subscribe(Consumer<SubscriberListener> consumer);

}
